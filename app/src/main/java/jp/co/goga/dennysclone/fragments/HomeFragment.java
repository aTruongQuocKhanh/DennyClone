package jp.co.goga.dennysclone.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.handler.FragmentHandler;
import jp.co.goga.dennysclone.handler.HomeHandler;
import jp.co.goga.dennysclone.item.HomeSliderItem;
import jp.co.goga.dennysclone.util.Constant;

/**
 * Created by khanhtq on 3/2/16.
 */
public class HomeFragment extends Fragment {
    private View mRootView;
    private HomeHandler mHomeHandler;
    private FragmentHandler.OpenFragmentMethods mOpenFragmentListener;

    public void setOpenFragmentListener(FragmentHandler.OpenFragmentMethods listener) {
        mOpenFragmentListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mRootView = inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHomeHandler = new HomeHandler(getContext(), mRootView);
        List<Object> data = new ArrayList<>();
        data.add(new HomeSliderItem(Arrays.asList(Constant.HOME_SLIDER_ITEMS)));
        data.add(new Object());
        data.addAll(Arrays.asList(Constant.HOME_NEWS_ITEMS));
        mHomeHandler.initializeView(data, mOpenFragmentListener);
    }
}
