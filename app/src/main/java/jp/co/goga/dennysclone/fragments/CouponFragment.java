package jp.co.goga.dennysclone.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.adapter.CouponAdapter;
import jp.co.goga.dennysclone.handler.FragmentHandler;
import jp.co.goga.dennysclone.util.Constant;

/**
 * Created by khanhtq on 3/7/16.
 */
public class CouponFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    public static final String TAG = CouponFragment.class.getSimpleName();
    private final int THUMBNAIL_SIZE = 200;

    private ViewPager mViewPager;
    private HorizontalScrollView mScrollView;
    private LinearLayout mScrollLayout;
    private ImageView mSwipeLeftButton, mSwipeRightButton;

    private int mCurrentPosition;
    private Point mDisplayProperties;
    private List<String> mListOfMedia;
    private List<Fragment> mFragments;
    private CouponAdapter mAdapter;
    private final View.OnClickListener thumbnailOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scroll(v);
            mViewPager.setCurrentItem((int) v.getTag(), true);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected boolean shouldShowBack() {
        return false;
    }

    @Override
    protected int getTitleId() {
        return R.string.coupon;
    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    protected void initView() {
        Log.d(TAG, "initView() --- ");
        mViewPager = (ViewPager) mRootView.findViewById(R.id.coupon_viewpager);
        mScrollView = (HorizontalScrollView) mRootView.findViewById(R.id.coupon_scrollview);
        mScrollLayout = (LinearLayout) mRootView.findViewById(R.id.coupon_scroll_layout);
        mSwipeLeftButton = (ImageView) mRootView.findViewById(R.id.swipe_left_button);
        mSwipeRightButton = (ImageView) mRootView.findViewById(R.id.swipe_right_button);

        mListOfMedia = Arrays.asList(Constant.COUPON_IMAGE_URL);
        loadFragments();
        mAdapter = new CouponAdapter(getFragmentManager(), mListOfMedia, mFragments);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mCurrentPosition = 0;
        updateSwipeButtonState();

        mDisplayProperties = getDisplaySize();
        loadScrollViewContent();

        mSwipeRightButton.setOnClickListener(this);
        mSwipeLeftButton.setOnClickListener(this);
    }

    @Override
    protected void onViewClick(int id) {

        switch (id) {
            case R.id.swipe_left_button:
                if (mCurrentPosition > 0) {
                    mCurrentPosition--;
                }
                scroll(mScrollLayout.getChildAt(mCurrentPosition));
                mViewPager.setCurrentItem(mCurrentPosition);
                break;
            case R.id.swipe_right_button:
                if (mCurrentPosition < mListOfMedia.size() - 1) {
                    mCurrentPosition++;
                }
                mViewPager.setCurrentItem(mCurrentPosition);
                scroll(mScrollLayout.getChildAt(mCurrentPosition));
                break;
        }
    }

    public void loadFragments() {
        mFragments = new ArrayList<>();
        for (String url : mListOfMedia) {
            ImageCouponFragment fragment = new ImageCouponFragment();
            fragment.setCouponUrl(url);
            mFragments.add(fragment);
        }
    }

    private void loadScrollViewContent() {
        for (int position = 0; position < mListOfMedia.size(); position++) {
            addThumbnail(position);
        }
        View view = mScrollLayout.getChildAt(mCurrentPosition).findViewById(R.id.cover_button);
        view.requestFocus();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        scroll(mScrollLayout.getChildAt(position));
        mCurrentPosition = position;
        updateSwipeButtonState();
        Log.d(TAG, "onPageSelected --- current position " + mCurrentPosition);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void updateSwipeButtonState() {
        mSwipeLeftButton.setVisibility((mCurrentPosition != 0) ? View.VISIBLE : View.GONE);
        mSwipeRightButton.setVisibility((mCurrentPosition != mListOfMedia.size() - 1) ? View.VISIBLE : View.GONE);
    }

    private void addThumbnail(int position) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(THUMBNAIL_SIZE, THUMBNAIL_SIZE);
        lp.setMargins(10, 10, 10, 10);

        View thumbnailView = createThumbnailView(lp, position);
        mScrollLayout.addView(thumbnailView);
    }

    private View createThumbnailView(LinearLayout.LayoutParams lp, int position) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.coupon_slider_item, null);
        ImageView thumbnailView = (ImageView) view.findViewById(R.id.coupon_slider_image);//new ImageView(getContext());
        ImageButton coverButton = (ImageButton) view.findViewById(R.id.cover_button);
        view.setLayoutParams(lp);//thumbnailView.setLayoutParams(lp);
        //thumbnailView.setBackgroundResource(R.drawable.thumbnail_background);
        coverButton.setTag(position);//thumbnailView.setTag(position);
        coverButton.setOnClickListener(thumbnailOnClickListener);
        coverButton.setClickable(true);
        coverButton.setFocusable(true);
        coverButton.setFocusableInTouchMode(true);
        thumbnailView.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.with(getContext()).load(mListOfMedia.get(position)).into(thumbnailView);
        return view;
    }

    private void scroll(View thumbnail) {
        thumbnail.requestFocus();
        int thumbnailCoords[] = new int[2];
        thumbnail.getLocationOnScreen(thumbnailCoords);

        int thumbnailCenterX = thumbnailCoords[0] + THUMBNAIL_SIZE + 20;
        int thumbnailDelta = mDisplayProperties.x - thumbnailCenterX;

        mScrollView.smoothScrollBy(-thumbnailDelta, 0);
    }

    private Point getDisplaySize() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(point);
        } else {
            point.set(display.getWidth(), display.getHeight());
        }
        return point;
    }
}
