package jp.co.goga.dennysclone.item;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by khanhtq on 3/4/16.
 */
public class HomeRecyclerViewDevider extends RecyclerView.ItemDecoration {
    private int space;

    public HomeRecyclerViewDevider(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = (parent.getChildPosition(view) == 0) ? 0 : space;

        // Add top margin only for the first item to avoid double space between items
        if(parent.getChildPosition(view) == 0) {
            outRect.top = space * 2;
        }
    }
}
