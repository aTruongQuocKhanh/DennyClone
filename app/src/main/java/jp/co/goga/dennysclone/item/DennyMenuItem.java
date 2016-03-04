package jp.co.goga.dennysclone.item;

/**
 * Created by khanhtq on 3/4/16.
 */
public class DennyMenuItem {
    private String mImageUrl;
    private String mTitle;

    public DennyMenuItem(String imageUrl, String title) {
        mImageUrl = imageUrl;
        mTitle = title;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getTitle() {
        return mTitle;
    }
}
