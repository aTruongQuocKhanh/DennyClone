package jp.co.goga.dennysclone.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.adapter.MenuAdapter;
import jp.co.goga.dennysclone.handler.FragmentHandler;
import jp.co.goga.dennysclone.util.Constant;

/**
 * Created by khanhtq on 3/2/16.
 */
public class MenuFragment extends Fragment implements PullToRefreshBase.OnRefreshListener{
    private View mRootView;
    private PullToRefreshListView mRefreshListView;
    private ListView mListMenuView;

    private Handler mHandler = new Handler();
    private List<Object> mDataList;
    private MenuAdapter mAdapter;
    private FragmentHandler.OpenFragmentMethods mOpenFragmentListener;

    public void setOpenFragmentListener(FragmentHandler.OpenFragmentMethods listener) {
        mOpenFragmentListener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mRootView = inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mRefreshListView = (PullToRefreshListView) mRootView.findViewById(R.id.pull_to_refresh_listview);
        mRefreshListView.setOnRefreshListener(this);
        mListMenuView = mRefreshListView.getRefreshableView();
        mAdapter = new MenuAdapter(getContext(), mDataList);
        mListMenuView.setAdapter(mAdapter);
    }

    private void loadData() {
        mDataList = new ArrayList<>();
        mDataList.add("FAIR MENU");
        mDataList.addAll(Arrays.asList(Constant.FAIR_MENU_ITEMS));
        mDataList.add("GRAND MENU");
        mDataList.addAll(Arrays.asList(Constant.GRAND_MENU_ITEMS));
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshListView.onRefreshComplete();
            }
        }, 5000);
    }
}
