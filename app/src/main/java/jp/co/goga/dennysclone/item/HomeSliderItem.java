package jp.co.goga.dennysclone.item;

import java.util.HashMap;
import java.util.List;

/**
 * Created by khanhtq on 3/4/16.
 */
public class HomeSliderItem {
    private List<SlideItem> mSlideData;

    public HomeSliderItem(List<SlideItem> slideItems) {
        mSlideData = slideItems;
    }

    public List<SlideItem> getSlideData() {
        return mSlideData;
    }
}
