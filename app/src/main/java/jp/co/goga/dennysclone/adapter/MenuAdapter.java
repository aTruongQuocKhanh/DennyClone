package jp.co.goga.dennysclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.item.DennyMenuItem;

/**
 * Created by khanhtq on 3/4/16.
 */
public class MenuAdapter extends BaseAdapter {
    private Context mContext;
    private List<Object> mDataList;
    private static final int ITEM_MENU_GROUP = 0;
    private static final int ITEM_MENU_ITEM = 1;

    public MenuAdapter(Context context, List<Object> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return (mDataList == null) ? 0 : mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return (mDataList == null || mDataList.size() <= position) ? null : mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        View mRootView = convertView;
        MenuHolder holder;
        if (mRootView == null) {
            holder = new MenuHolder();
            if (viewType == ITEM_MENU_GROUP) {
                LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                mRootView = vi.inflate(R.layout.menu_group_title_item, parent, false);
                holder.mGroupName = (TextView) mRootView.findViewById(R.id.menu_group_title_textview);
            } else {
                LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                mRootView = vi.inflate(R.layout.menu_item, parent, false);
                holder.mMenuIcon = (ImageView) mRootView.findViewById(R.id.menu_item_imageview);
                holder.mMenuTitle = (TextView) mRootView.findViewById(R.id.menu_item_title_textview);
            }
            mRootView.setTag(holder);
        } else {
            holder = (MenuHolder) mRootView.getTag();
        }
        Object object = getItem(position);
        if (object != null) {
            if (viewType == ITEM_MENU_GROUP) {
                holder.mGroupName.setText((String) object);
            } else {
                holder.mMenuTitle.setText(((DennyMenuItem)object).getTitle());
                Picasso.with(mContext).load(((DennyMenuItem)object).getImageUrl()).into(holder.mMenuIcon);
            }
        }
        return mRootView;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);
        if (item instanceof String) {
            return ITEM_MENU_GROUP;
        } else {
            return ITEM_MENU_ITEM;
        }
    }

    private class MenuHolder {
        ImageView mMenuIcon;
        TextView mMenuTitle;
        TextView mGroupName;
    }
}
