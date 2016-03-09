package jp.co.goga.dennysclone.item;

/**
 * Created by khanhtq on 3/9/16.
 */
public class MapSearchOptionItem {
    private String mIconUrl;
    private String mName;

    public MapSearchOptionItem(String name, String iconUrl) {
        mIconUrl = iconUrl;
        mName = name;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public String getName() {
        return mName;
    }
}
