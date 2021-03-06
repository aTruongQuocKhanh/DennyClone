package jp.co.goga.dennysclone.activities;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import jp.co.goga.dennysclone.util.DialogManager;
import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.entity.TabEntity;
import jp.co.goga.dennysclone.fragments.CouponFragment;
import jp.co.goga.dennysclone.fragments.HomeFragment;
import jp.co.goga.dennysclone.fragments.DennysMenuFragment;

import jp.co.goga.dennysclone.fragments.MenuFragment;
import jp.co.goga.dennysclone.fragments.ServiceFragment;
import jp.co.goga.dennysclone.handler.FragmentHandler;
import jp.co.goga.dennysclone.util.Constant;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener, FragmentHandler.OpenFragmentMethods {
    public static final String TAG = MainActivity.class.getSimpleName();

    private CommonTabLayout mTabLayout;
    private ArrayList<CustomTabEntity> mTabEntitys = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private int mCurrentTabId;

    private FragmentHandler mFragmentHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = (CommonTabLayout) findViewById(R.id.tab_layout);
        initializeFragments();
        for (int i = 0; i < Constant.TAB_TITLES.length; i++) {
            mTabEntitys.add(new TabEntity(getResources().getString(Constant.TAB_TITLES[i]), Constant.TAB_ICON_SELECTED[i], Constant.TAB_ICON_NORMAL[i]));
        }
        mTabLayout.setTabData(mTabEntitys, this, R.id.main_frame, mFragments);
        mTabLayout.setCurrentTab(0);
        mTabLayout.setOnTabSelectListener(this);
        mFragmentHandler = FragmentHandler.getInstance();
        mFragmentHandler.init(mTabLayout, mFragments);
    }

    private void initializeFragments() {
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setOpenFragmentListener(this);
        mFragments.add(homeFragment);
        DennysMenuFragment dennysMenuFragment = new DennysMenuFragment();
        dennysMenuFragment.setOpenFragmentListener(this);
        mFragments.add(dennysMenuFragment);
        CouponFragment couponFragment = new CouponFragment();
        mFragments.add(couponFragment);
        ServiceFragment serviceFragment = new ServiceFragment();
        serviceFragment.setOpenFragmentListener(this);
        mFragments.add(serviceFragment);
        MenuFragment menuFragment = new MenuFragment();
        menuFragment.setOpenFragmentListener(this);
        mFragments.add(menuFragment);
    }

    @Override
    public void onTabSelect(int position) {
        mCurrentTabId = position;
        mFragmentHandler.reloadFragmentState(this, mCurrentTabId);
    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void openFragment(Fragment fragment, boolean isBackPress) {
        if (!isBackPress) {
            mFragmentHandler.addToStack(mCurrentTabId, fragment);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        boolean isFinish = mFragmentHandler.popFromStack(this, mCurrentTabId);
        if (isFinish) {
            DialogManager.getInstance().createNoticeDialog(this, "確認", "アプリを終了してよろしいですか?", "はい", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }, "いいえ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragmentHandler.recycle();
    }
}
