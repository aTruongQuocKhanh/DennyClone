package jp.co.goga.dennysclone.handler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.flyco.tablayout.CommonTabLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.WeakHashMap;

/**
 * Created by khanhtq on 3/3/16.
 */
public class FragmentHandler {
    private static FragmentHandler sInstance;
    private Map<Integer, Stack<Fragment>> mFragmentMap;


    public static FragmentHandler getInstance() {
        if (sInstance == null) {
            sInstance = new FragmentHandler();
        }
        return sInstance;
    }

    public void init(CommonTabLayout tabLayout, List<Fragment> fragments) {
        if (tabLayout == null || tabLayout.getTabCount() == 0) {
            return;
        }
        int tabCount = tabLayout.getTabCount();
        mFragmentMap = new HashMap<>();
        for (int i = 0; i < tabCount; i++) {
            Stack<Fragment> fragmentStack = new Stack<Fragment>();
            fragmentStack.push(fragments.get(i));
            mFragmentMap.put(i, fragmentStack);
        }
    }

    public void addToStack(int tabIndex, Fragment fragment) {
        Stack<Fragment> fragmentStack = mFragmentMap.get(tabIndex);
        if (fragmentStack == null) {
            return;
        }

        fragmentStack.push(fragment);
    }

    public boolean popFromStack(OpenFragmentMethods listener, int tabIndex) {
        Stack<Fragment> fragmentStack = mFragmentMap.get(tabIndex);
        if (fragmentStack == null || fragmentStack.isEmpty()) {
            return true;
        }
        fragmentStack.pop();
        if (fragmentStack.isEmpty()) {
            return true;
        }
        Fragment fragment = fragmentStack.peek();
        if (fragment == null) {
            return true;
        }
        listener.openFragment(fragment, true);
        return false;
    }

    public void reloadFragmentState(OpenFragmentMethods listener, int tabIndex) {
        Stack<Fragment> fragmentStack = mFragmentMap.get(tabIndex);
        if (fragmentStack == null || fragmentStack.isEmpty()) {
            return;
        }
        Fragment fragment = fragmentStack.peek();
        if (fragment == null) {
            return;
        }
        listener.openFragment(fragment, true);
    }

    public void recycle() {
        if (mFragmentMap != null) {
            mFragmentMap.clear();
        }
        mFragmentMap = null;
    }

    public interface OpenFragmentMethods {
        void openFragment(Fragment fragment, boolean isBackPress);
    }
}
