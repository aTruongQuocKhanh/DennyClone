package jp.co.goga.dennysclone.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import java.util.Objects;

import jp.co.goga.dennysclone.DialogManager;
import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.adapter.ActionAdapter;
import jp.co.goga.dennysclone.handler.FragmentHandler;
import jp.co.goga.dennysclone.item.ActionItem;

/**
 * Created by khanhtq on 3/8/16.
 */
public class MenuFragment extends BaseFragment {
    private ListView mMenuList;

    private List<Object> mDataList;
    private BaseAdapter mAdapter;
    private FragmentHandler.OpenFragmentMethods mOpenFragmentListener;

    public void setOpenFragmentListener(FragmentHandler.OpenFragmentMethods listener) {
        mOpenFragmentListener = listener;
    }

    private final Object[] MENU_ITEM = {
            new Object(),
            new ActionItem("デニーズ店舗検索", new ActionItem.Action() {
                @Override
                public void doAction() {
                    MapFragment mapFragment = new MapFragment();
                    mOpenFragmentListener.openFragment(mapFragment, false);
                }
            }),
            new ActionItem("デニーズ公式サイト", null),
            new ActionItem("デニモバクラブお問い合わせ", null),
            new ActionItem("PUSH通知設定", new ActionItem.Action() {
                @Override
                public void doAction() {
                    Toast.makeText(getContext(), "PUSH通知設定", Toast.LENGTH_LONG).show();
                }
            }),
            new ActionItem("アプリを友達に教える", new ActionItem.Action() {
                @Override
                public void doAction() {
                    Toast.makeText(getContext(), "アプリを友達に教える", Toast.LENGTH_LONG).show();
                }
            }),
            new ActionItem("レビューを書く", new ActionItem.Action() {
                @Override
                public void doAction() {
                    Toast.makeText(getContext(), "レビューを書く", Toast.LENGTH_LONG).show();
                }
            }),
            new Object(),
            new ActionItem("セブン＆アイアプリ一覧", new ActionItem.Action() {
                @Override
                public void doAction() {
                    Toast.makeText(getContext(), "レビューを書く", Toast.LENGTH_LONG).show();
                }
            }),
            new Object(),
            new ActionItem("プライバシーポリシー", null),
            new ActionItem("運営会社について", null),
            new ActionItem("バージョン情報", new ActionItem.Action() {
                @Override
                public void doAction() {
                    Toast.makeText(getContext(), "バージョン情報", Toast.LENGTH_LONG).show();
                }
            })
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu;
    }

    @Override
    protected boolean shouldShowBack() {
        return false;
    }

    @Override
    protected int getTitleId() {
        return R.string.navi;
    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    protected void initView() {
        mMenuList = (ListView) mRootView.findViewById(R.id.menu_listview);

        loadData();
        mAdapter = new ActionAdapter(getContext(), mDataList);
        mMenuList.setAdapter(mAdapter);
        mMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = mDataList.get(position);
                if (item != null && item instanceof ActionItem) {
                    ((ActionItem) item).getAction().doAction();
                }
            }
        });
    }

    @Override
    protected void onViewClick(int id) {

    }

    private void loadData() {
        mDataList = new ArrayList<>();
        mDataList.addAll(Arrays.asList(MENU_ITEM));
    }
}
