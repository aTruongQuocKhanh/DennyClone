package jp.co.goga.dennysclone.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.fragments.DennysMenuFragment;
import jp.co.goga.dennysclone.handler.FragmentHandler;
import jp.co.goga.dennysclone.item.HomeNewsItem;
import jp.co.goga.dennysclone.item.HomeSliderItem;
import jp.co.goga.dennysclone.item.SlideItem;
import jp.co.goga.dennysclone.view.HomeSliderView;

/**
 * Created by khanhtq on 3/2/16.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> mDataList;
    private Context mContext;
    private static final int TYPE_SLIDER = 0;
    private static final int TYPE_TEXT_NOTICE = 1;
    private static final int TYPE_NEWS = 2;
    private boolean isReset;
    private FragmentHandler.OpenFragmentMethods mOpenFragmentListener;

    public HomeAdapter(Context context, List<Object> dataList, FragmentHandler.OpenFragmentMethods listener) {
        mContext = context;
        mDataList = dataList;
        mOpenFragmentListener = listener;
    }

    private Object getItem(int position) {
        return (mDataList == null || mDataList.size() <= position) ? null : mDataList.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        Object data = mDataList.get(position);
        if (data instanceof HomeSliderItem) {
            return TYPE_SLIDER;
        } else if (data instanceof HomeNewsItem) {
            return TYPE_NEWS;
        } else {
            return TYPE_TEXT_NOTICE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_SLIDER:
                View v1 = inflater.inflate(R.layout.home_slider_item, parent, false);
                viewHolder = new SliderHolder(v1);
                break;
            case TYPE_NEWS:
                View v2 = inflater.inflate(R.layout.home_news_item, parent, false);
                viewHolder = new NewsHolder(v2);
                break;
            default:
                View v = inflater.inflate(R.layout.news_text_item, parent, false);
                viewHolder = new SimpleHolder(v);
                break;
        }
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_SLIDER:
                SliderHolder sliderHolder = (SliderHolder) holder;
                initSliderView(sliderHolder, position);
                break;
            case TYPE_NEWS:
                NewsHolder newsHolder = (NewsHolder) holder;
                loadNews(newsHolder, position);
                break;
            case TYPE_TEXT_NOTICE:
                break;
        }
    }


    public void initSliderView(SliderHolder holder, int position) {
        HomeSliderItem sliderItem = (HomeSliderItem) getItem(position);
        if (sliderItem != null) {
            holder.getSliderLayout().removeAllSliders();
            for (SlideItem slideItem : sliderItem.getSlideData()) {
                HomeSliderView textSliderView = new HomeSliderView(mContext);
                // initialize a SliderLayout
                textSliderView
                        .image(slideItem.mImageUrl)
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
                                if (mOpenFragmentListener != null) {
                                    mOpenFragmentListener.openFragment(new DennysMenuFragment(), false);
                                }
                            }
                        });
                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra", slideItem.mName);
                holder.getSliderLayout().addSlider(textSliderView);
            }

            holder.getSliderLayout().setPresetTransformer(SliderLayout.Transformer.Default);
            holder.getSliderLayout().setCustomIndicator(holder.getIndicator());
//            holder.getSliderLayout().setCustomAnimation(new DescriptionAnimation());
            holder.getSliderLayout().setDuration(5000);
        }
    }

    public void loadNews(NewsHolder holder, int position) {
        HomeNewsItem news = (HomeNewsItem) mDataList.get(position);
        if (news != null) {
            Picasso.with(mContext).load(news.getImageUrl()).into(holder.newsImage);
            holder.newsTime.setText(news.getDate());
            holder.newsTitle.setText(news.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return (mDataList == null) ? 0 : mDataList.size();
    }

    private class SliderHolder extends RecyclerView.ViewHolder{

        private SliderLayout mSliderLayout;
        private PagerIndicator mIndicator;

        public SliderHolder(View itemView) {
            super(itemView);
            mSliderLayout = (SliderLayout) itemView.findViewById(R.id.slider);
            mIndicator = (PagerIndicator) itemView.findViewById(R.id.custom_indicator);
        }

        public PagerIndicator getIndicator() {
            return mIndicator;
        }

        public SliderLayout getSliderLayout() {
            return mSliderLayout;
        }
    }

    private class NewsHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTime;
        TextView newsTitle;

        public NewsHolder(View itemView) {
            super(itemView);
            newsImage = (ImageView) itemView.findViewById(R.id.home_news_imageview);
            newsTime = (TextView) itemView.findViewById(R.id.home_news_day_textview);
            newsTitle = (TextView) itemView.findViewById(R.id.home_news_title_textview);
        }
    }

    public class SimpleHolder extends RecyclerView.ViewHolder {

        public SimpleHolder(View itemView) {
            super(itemView);
        }
    }
}
