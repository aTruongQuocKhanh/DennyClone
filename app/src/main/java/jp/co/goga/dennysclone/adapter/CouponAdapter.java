package jp.co.goga.dennysclone.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import jp.co.goga.dennysclone.fragments.ImageCouponFragment;

/**
 * Created by khanhtq on 3/7/16.
 */
public class CouponAdapter extends FragmentStatePagerAdapter {
    private List<String> mImageUrls;
    private List<Fragment> mFragments;

    public CouponAdapter(FragmentManager fm, List<String> imageUrls, List<Fragment> fragments) {
        super(fm);
        mImageUrls = (imageUrls != null) ? imageUrls : new ArrayList<String>();
        mFragments = (fragments != null) ? fragments : new ArrayList<Fragment>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return (mImageUrls == null) ? 0 : mImageUrls.size();
    }
}
