package jp.co.goga.dennysclone.handler;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;

import java.util.List;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.adapter.HomeAdapter;
import jp.co.goga.dennysclone.item.HomeRecyclerViewDevider;

/**
 * Created by khanhtq on 3/2/16.
 */
public class HomeHandler {
    private Context mContext;
    private View mRootView;
    private RecyclerView mContentListView;
    private HomeAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private HomeRecyclerViewDevider mDecorator;
    private SwipeRefreshLayout mRefreshLayout;
    private Handler mHandler = new Handler();

    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Toast.makeText(mContext, "Refresh content", Toast.LENGTH_LONG).show();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRefreshLayout.setRefreshing(false);
                }
            }, 5000);
        }
    };

    public HomeHandler(Context context, View rootView) {
        mContext = context;
        mRootView = rootView;
    }

    public void initializeView(List<Object> data, FragmentHandler.OpenFragmentMethods listener) {
        mContentListView = (RecyclerView) mRootView.findViewById(R.id.home_content);
        mRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_home_layout);
        mAdapter = new HomeAdapter(mContext, data, listener);
        mDecorator = new HomeRecyclerViewDevider(20);
        mLayoutManager = new LinearLayoutManager(mContext);
        mContentListView.setLayoutManager(mLayoutManager);
        mContentListView.addItemDecoration(mDecorator);
        mContentListView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(mRefreshListener);
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }
}
