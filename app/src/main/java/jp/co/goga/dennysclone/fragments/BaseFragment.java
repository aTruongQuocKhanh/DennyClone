package jp.co.goga.dennysclone.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import jp.co.goga.dennysclone.R;

/**
 * Created by khanhtq on 3/8/16.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = BaseFragment.class.getSimpleName();

    protected View mRootView;
    private Button mAccountButton;
    private TextView mTitleView;
    private ImageButton mBackButton;

    protected abstract int getLayoutId();
    protected abstract boolean shouldShowBack();
    protected abstract int getTitleId();
    protected abstract String getTitle();
    protected abstract void initView();
    protected abstract void onViewClick(int id);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        mBackButton = (ImageButton) mRootView.findViewById(R.id.back_button);
        mTitleView = (TextView) mRootView.findViewById(R.id.title_textview);
        mAccountButton = (Button) mRootView.findViewById(R.id.acc_button);
        if (getTitle() != null) {
            mTitleView.setText(getTitle());
        } else {
            mTitleView.setText(getResources().getString(getTitleId()));
        }
        if (shouldShowBack()) {
            mBackButton.setVisibility(View.VISIBLE);
        } else {
            mBackButton.setVisibility(View.GONE);
        }
        mBackButton.setOnClickListener(this);
        mAccountButton.setOnClickListener(this);
        initView();
        return mRootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.back_button:
                Log.d(TAG, "Back button press");
                getActivity().onBackPressed();
                break;
            case R.id.acc_button:
                Toast.makeText(getContext(), "Account ", Toast.LENGTH_SHORT).show();
                break;
            default:
                onViewClick(id);
                break;
        }
    }
}
