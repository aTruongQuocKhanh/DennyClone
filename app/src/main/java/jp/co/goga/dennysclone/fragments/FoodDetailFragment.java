package jp.co.goga.dennysclone.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.adapter.FoodDetailAdapter;
import jp.co.goga.dennysclone.item.DennyMenuItem;
import jp.co.goga.dennysclone.util.Constant;

/**
 * Created by khanhtq on 3/10/16.
 */
public class FoodDetailFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener {
    public static final String FATHER_FOOD_URL = "FATHER_FOOD_URL";
    public static final String FATHER_FOOD_NAME = "FATHER_FOOD_NAME";

    private PullToRefreshListView mRefreshListView;
    private ListView mListNewsView;

    private Handler mHandler = new Handler();
    private List<Object> mDataList;
    private FoodDetailAdapter mAdapter;
    private String fatherFoodName, fatherFoodImageUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            fatherFoodImageUrl = args.getString(FATHER_FOOD_URL);
            fatherFoodName = args.getString(FATHER_FOOD_NAME);
        }
        loadData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_food_detail;
    }

    @Override
    protected boolean shouldShowBack() {
        return true;
    }

    @Override
    protected int getTitleId() {
        return R.string.news;
    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    protected void initView() {
        mRefreshListView = (PullToRefreshListView) mRootView.findViewById(R.id.food_detail_listview);
        mRefreshListView.setOnRefreshListener(this);
        mListNewsView = mRefreshListView.getRefreshableView();
        mListNewsView.setDivider(null);
        mListNewsView.setDividerHeight(0);
        mAdapter = new FoodDetailAdapter(getContext(), mDataList);
        mListNewsView.setAdapter(mAdapter);
    }

    private void loadData() {
        mDataList = new ArrayList<>();
        mDataList.add(new DennyMenuItem(fatherFoodImageUrl, fatherFoodName));
        mDataList.addAll(Arrays.asList(Constant.FOOD_ITEMS));
    }

    @Override
    protected void onViewClick(int id) {

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
