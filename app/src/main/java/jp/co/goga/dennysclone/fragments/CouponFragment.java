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

import java.util.Arrays;
import java.util.List;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.adapter.CouponAdapter;
import jp.co.goga.dennysclone.util.Constant;

/**
 * Created by khanhtq on 3/7/16.
 */
public class CouponFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    public static final String TAG = CouponFragment.class.getSimpleName();
    private final int THUMBNAIL_SIZE = 200;

    private View mRootView;
    private ViewPager mViewPager;
    private HorizontalScrollView mScrollView;
    private LinearLayout mScrollLayout;
    private ImageView mSwipeLeftButton, mSwipeRightButton;

    private int mCurrentPosition;
    private Point mDisplayProperties;
    private List<String> mListOfMedia;
    private CouponAdapter mAdapter;
    private final View.OnClickListener thumbnailOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scroll(v);
            mViewPager.setCurrentItem((int) v.getTag(), true);
            v.setSelected(true);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mRootView = inflater.inflate(R.layout.fragment_coupon, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) mRootView.findViewById(R.id.coupon_viewpager);
        mScrollView = (HorizontalScrollView) mRootView.findViewById(R.id.coupon_scrollview);
        mScrollLayout = (LinearLayout) mRootView.findViewById(R.id.coupon_scroll_layout);
        mSwipeLeftButton = (ImageView) mRootView.findViewById(R.id.swipe_left_button);
        mSwipeRightButton = (ImageView) mRootView.findViewById(R.id.swipe_right_button);

        mListOfMedia = Arrays.asList(Constant.COUPON_IMAGE_URL);
        mAdapter = new CouponAdapter(getFragmentManager(), mListOfMedia);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);

        mDisplayProperties = getDisplaySize();
        loadScrollViewContent();

        mSwipeRightButton.setOnClickListener(this);
        mSwipeLeftButton.setOnClickListener(this);
    }

    private void loadScrollViewContent() {
        for (int position = 0; position < mListOfMedia.size(); position++) {
            addThumbnail(position);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        scroll(mScrollLayout.getChildAt(position));
        mCurrentPosition = position;
        mSwipeLeftButton.setEnabled(mCurrentPosition != 0);
        mSwipeRightButton.setEnabled(mCurrentPosition != mListOfMedia.size() - 1);
        Log.d(TAG, "onPageSelected --- current position" + mCurrentPosition);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
        thumbnailView.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.with(getContext()).load(mListOfMedia.get(position)).into(thumbnailView);
        return view;
    }

    private void scroll(View thumbnail) {
        int thumbnailCoords[] = new int[2];
        thumbnail.getLocationOnScreen(thumbnailCoords);

        int thumbnailCenterX = thumbnailCoords[0] + THUMBNAIL_SIZE / 2;
        int thumbnailDelta = mDisplayProperties.x / 2 - thumbnailCenterX;

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.swipe_left_button:
                if (mCurrentPosition > 0) {
                    mCurrentPosition--;
                }
                scroll(mScrollLayout.getChildAt(mCurrentPosition));
                mViewPager.setCurrentItem(mCurrentPosition);
                break;
            case R.id.swipe_right_button:
                if (mCurrentPosition < mListOfMedia.size()-1) {
                    mCurrentPosition++;
                }
                mViewPager.setCurrentItem(mCurrentPosition);
                scroll(mScrollLayout.getChildAt(mCurrentPosition));
                break;
        }
    }
}
