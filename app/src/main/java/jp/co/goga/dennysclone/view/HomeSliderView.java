package jp.co.goga.dennysclone.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;

import jp.co.goga.dennysclone.R;

/**
 * Created by khanhtq on 3/2/16.
 */
public class HomeSliderView extends BaseSliderView {
    public HomeSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.home_slider_view,null);
        ImageView target = (ImageView)v.findViewById(R.id.home_slider_image);
        bindEventAndShow(v, target);
        return v;
    }
}
