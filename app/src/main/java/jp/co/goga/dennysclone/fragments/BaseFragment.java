package jp.co.goga.dennysclone.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import jp.co.goga.dennysclone.activities.LoginActivity;
import jp.co.goga.dennysclone.util.DialogManager;
import jp.co.goga.dennysclone.R;

/**
 * Created by khanhtq on 3/8/16.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = BaseFragment.class.getSimpleName();
    private static final int LOGIN_REQUEST_CODE = 100;

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
                AlertDialog dialog = DialogManager.getInstance().createNoticeDialog(getActivity(), "確認", "この機能を利用するのはログインが必要です。ログインしますか？", "はい", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doLogin();
                    }
                }, "いいえ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
                break;
            default:
                onViewClick(id);
                break;
        }
    }

    private void doLogin() {
        Intent loginIntent = new Intent(getContext(), LoginActivity.class);
        startActivityForResult(loginIntent,LOGIN_REQUEST_CODE);
        getActivity().overridePendingTransition(R.anim.slide_in,R.anim.stay);
    }
}
