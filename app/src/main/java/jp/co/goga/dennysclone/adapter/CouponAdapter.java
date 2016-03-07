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

    public CouponAdapter(FragmentManager fm, List<String> imageUrls) {
        super(fm);
        mImageUrls = (imageUrls != null) ? imageUrls : new ArrayList<String>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (mImageUrls != null && position < mImageUrls.size()) {
            fragment = loadImageFragment(mImageUrls.get(position));
        }
        return fragment;
    }

    private Fragment loadImageFragment(String url) {
        ImageCouponFragment fragment = new ImageCouponFragment();
        fragment.setCouponUrl(url);
        return fragment;
    }

    @Override
    public int getCount() {
        return (mImageUrls == null) ? 0 : mImageUrls.size();
    }
}
