package jp.co.goga.dennysclone.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.handler.FragmentHandler;

/**
 * Created by khanhtq on 3/7/16.
 */
public class ImageCouponFragment extends Fragment implements View.OnClickListener{
    public static final String TAG = ImageCouponFragment.class.getSimpleName();
    private static final String URL = "URL";

    private View mRootView;
    private ImageView mCouponImage;
    private Button mCautionButton, mBuyCouponButton;
    private TextView mAlertTextView;
    private FragmentHandler.OpenFragmentMethods mOpenFragmentListener;

    public void setOpenFragmentListener(FragmentHandler.OpenFragmentMethods listener) {
        mOpenFragmentListener = listener;
    }

    private String mCouponUrl;

    public void setCouponUrl(String couponUrl) {
        mCouponUrl = couponUrl;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        mRootView = inflater.inflate(R.layout.fragment_image_coupon, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        initView();
    }

    private void initView() {
        mCouponImage = (ImageView) mRootView.findViewById(R.id.coupon_image);
        mCautionButton = (Button) mRootView.findViewById(R.id.caution_button);
        mBuyCouponButton = (Button) mRootView.findViewById(R.id.buy_coupon_button);
        mAlertTextView = (TextView) mRootView.findViewById(R.id.coupon_alert_message);

        mAlertTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_alert_message));
        Log.d(TAG, "mCouponUrl = " + mCouponUrl);
        if (mCouponUrl != null) {
            Picasso.with(getContext()).load(mCouponUrl).into(mCouponImage);
        }
        mBuyCouponButton.setOnClickListener(this);
        mCautionButton.setOnClickListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_coupon_button:
                Log.d(TAG, "buy_coupon_button click()");
                break;
            case R.id.caution_button:
                Log.d(TAG, "caution_button click()");
                break;
        }
    }
}
