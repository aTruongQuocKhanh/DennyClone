package jp.co.goga.dennysclone.item;

/**
 * Created by khanhtq on 3/3/16.
 */
public class HomeNewsItem {
    private String mImageUrl;
    private String mDate;
    private String mTitle;

    public HomeNewsItem(String imageUrl, String date, String title) {
        mImageUrl = imageUrl;
        mDate = date;
        mTitle = title;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }
}
