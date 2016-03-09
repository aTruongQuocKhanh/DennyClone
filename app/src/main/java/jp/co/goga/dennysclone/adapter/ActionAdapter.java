package jp.co.goga.dennysclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.item.ActionItem;

/**
 * Created by khanhtq on 3/8/16.
 */
public class ActionAdapter extends BaseAdapter {
    private Context mContext;
    private List<Object> mDataList;
    private final int ITEM_ACTION = 0;
    private final int ITEM_SEPERATOR = 1;

    public ActionAdapter(Context context, List<Object> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return (mDataList == null) ? 0 : mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList == null ? null : mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);
        if (item instanceof ActionItem) {
            return ITEM_ACTION;
        } else {
            return ITEM_SEPERATOR;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        View mRootView = convertView;
        ActionHolder holder;
        if (mRootView == null) {
            holder = new ActionHolder();
            if (viewType == ITEM_ACTION) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                mRootView = inflater.inflate(R.layout.action_item, parent, false);
                holder.mActionName = (TextView) mRootView.findViewById(R.id.action_name_textview);
                mRootView.setTag(holder);
            } else {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                mRootView = inflater.inflate(R.layout.seperator_item, parent, false);
                mRootView.setTag(holder);
            }
        } else {
            holder = (ActionHolder) mRootView.getTag();
        }
        Object item = getItem(position);
        if (item != null) {
            if (viewType == ITEM_ACTION) {
                holder.mActionName.setText(((ActionItem) item).getActionName());
            }
        }
        return mRootView;
    }

    @Override
    public boolean isEnabled(int position) {
        Object item = getItem(position);
        return item != null && item instanceof ActionItem && ((ActionItem) item).getAction() != null;
    }

    private class ActionHolder {
        TextView mActionName;
    }
}
