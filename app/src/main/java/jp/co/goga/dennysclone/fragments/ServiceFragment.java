package jp.co.goga.dennysclone.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.goga.dennysclone.DialogManager;
import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.adapter.ActionAdapter;
import jp.co.goga.dennysclone.handler.FragmentHandler;
import jp.co.goga.dennysclone.item.ActionItem;

/**
 * Created by khanhtq on 3/8/16.
 */
public class ServiceFragment extends BaseFragment {
    private ListView mListAction;

    private BaseAdapter mAdapter;
    private List<Object> mDataList;
    private FragmentHandler.OpenFragmentMethods mOpenFragmentListener;

    public void setOpenFragmentListener(FragmentHandler.OpenFragmentMethods listener) {
        mOpenFragmentListener = listener;
    }

    private final ActionItem[] ACTION_LIST = {
            new ActionItem("デニーズの宅配（出前)", new ActionItem.Action() {
                @Override
                public void doAction() {
                    AlertDialog dialog = DialogManager.getInstance().createNoticeDialog(getActivity(), "確認", "外部ブラウザを起動しますがよろしいですか？\n※この機能は一部の店舗のみご利用可能です。",
                            "はい", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dennys.omni7.jp/g/dennys/catering/index.php?utm_source=denimoba&utm_medium=app"));
                                    startActivity(browserIntent);
                                    dialog.dismiss();
                                }
                            }, "いいえ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    dialog.show();
                }
            }),
            new ActionItem("スマホで順番待ちを記入", null),
            new ActionItem("スマホで席を予約", null),
            new ActionItem("デニーズのネット販売", new ActionItem.Action() {
                @Override
                public void doAction() {
                    AlertDialog dialog = DialogManager.getInstance().createNoticeDialog(getActivity(), "確認", "外部ブラウザを起動しますがよろしいですか？\n※この機能は一部の店舗のみご利用可能です。",
                            "はい", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://iyec.omni7.jp/search/?keyword=デニーズ&searchKeywordFlg=1"));
                                    startActivity(browserIntent);
                                    dialog.dismiss();
                                }
                            }, "いいえ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    dialog.show();
                }
            })
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    protected boolean shouldShowBack() {
        return false;
    }

    @Override
    protected int getTitleId() {
        return R.string.net_service;
    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    protected void initView() {
        mListAction = (ListView) mRootView.findViewById(R.id.action_list);

        mDataList = new ArrayList<>();
        mDataList.addAll(Arrays.asList(ACTION_LIST));

        mAdapter = new ActionAdapter(getContext(), mDataList);
        mListAction.setAdapter(mAdapter);
        mListAction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof ActionItem) {
                    ((ActionItem) item).getAction().doAction();
                }
            }
        });
    }

    @Override
    protected void onViewClick(int id) {

    }
}
