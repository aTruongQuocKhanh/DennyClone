package jp.co.goga.dennysclone.adapter;

import android.content.Context;
import android.util.Log;
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
import jp.co.goga.dennysclone.item.FoodItem;

/**
 * Created by khanhtq on 3/10/16.
 */
public class FoodDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<Object> mDataList;
    private static final int ITEM_FATHER_FOOD = 0;
    private static final int ITEM_FOOD = 1;

    public FoodDetailAdapter(Context context, List<Object> dataList) {
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
        Log.d("MenuAdapter", "getView position" + position);
        int viewType = getItemViewType(position);
        View mRootView = convertView;
        MenuHolder holder;
        if (mRootView == null) {
            holder = new MenuHolder();
            if (viewType == ITEM_FOOD) {
                LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                mRootView = vi.inflate(R.layout.food_item, parent, false);
                holder.mFoodImage = (ImageView) mRootView.findViewById(R.id.food_image);
            } else {
                LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                mRootView = vi.inflate(R.layout.father_food_item, parent, false);
                holder.mFatherFoodImageView = (ImageView) mRootView.findViewById(R.id.father_food_imageview);
                holder.mFatherFoodTitle = (TextView) mRootView.findViewById(R.id.father_food_textview);
            }
            mRootView.setTag(holder);
        } else {
            Log.d("MenuAdapter", "getView() tag id");
            holder = (MenuHolder) mRootView.getTag();
        }
        Object object = getItem(position);
        if (object != null) {
            if (viewType == ITEM_FOOD) {
                Picasso.with(mContext).load(((FoodItem) object).getImageUrl()).into(holder.mFoodImage);
            } else {
                holder.mFatherFoodTitle.setText(((DennyMenuItem) object).getTitle());
                Picasso.with(mContext).load(((DennyMenuItem) object).getImageUrl()).into(holder.mFatherFoodImageView);
            }
        }
        return mRootView;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);
        if (item instanceof DennyMenuItem) {
            return ITEM_FATHER_FOOD;
        } else {
            return ITEM_FOOD;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    private class MenuHolder {
        ImageView mFatherFoodImageView;
        TextView mFatherFoodTitle;
        ImageView mFoodImage;
    }
}