package jp.co.goga.dennysclone.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.item.MapSearchOptionItem;

/**
 * Created by khanhtq on 3/9/16.
 */
public class SearchOptionAdapter implements ExpandableListAdapter {
    private Context mContext;
    private List<String> mGroupList;
    private Map<String, List<Object>> mChildList;

    public SearchOptionAdapter(Context context, List<String> groupList, Map<String, List<Object>> childList) {
        mContext = context;
        mGroupList = groupList;
        mChildList = childList;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildList.get(mGroupList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildList.get(mGroupList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition << childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View mRootView = convertView;
        if (mRootView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mRootView = inflater.inflate(R.layout.map_list_option_group_item, parent, false);
        }
        TextView groupTitleView = (TextView) mRootView.findViewById(R.id.group_title);
        String groupTitle = mGroupList.get(groupPosition);
        groupTitleView.setText(groupTitle);
        return mRootView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View mRootView = convertView;
        if (mRootView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mRootView = inflater.inflate(R.layout.map_list_option_child_item, parent, false);
        }

        MapSearchOptionItem item = (MapSearchOptionItem) mChildList.get(mGroupList.get(groupPosition)).get(childPosition);
        ImageView iconView = (ImageView) mRootView.findViewById(R.id.option_icon_imageview);
        Switch switchView = (Switch) mRootView.findViewById(R.id.option_switch);
        if (item.getIconUrl() != null) {
            Picasso.with(mContext).load(item.getIconUrl()).into(iconView);
        }
        switchView.setText(item.getName());
        return mRootView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
