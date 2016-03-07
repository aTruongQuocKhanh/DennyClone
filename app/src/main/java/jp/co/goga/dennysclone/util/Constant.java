package jp.co.goga.dennysclone.util;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.item.DennyMenuItem;
import jp.co.goga.dennysclone.item.HomeNewsItem;
import jp.co.goga.dennysclone.item.HomeSliderItem;
import jp.co.goga.dennysclone.item.SlideItem;

/**
 * Created by khanhtq on 3/2/16.
 */
public class Constant {

    public static final int[] TAB_ICON_NORMAL = {
            R.drawable.home,
            R.drawable.menu,
            R.drawable.coupon,
            R.drawable.home,
            R.drawable.home,
    };

    public static final int[] TAB_ICON_SELECTED = {
            R.drawable.home_selected,
            R.drawable.menu_selected,
            R.drawable.coupon_selected,
            R.drawable.home_selected,
            R.drawable.home_selected,
    };

    public static final int[] TAB_TITLES = {
            R.string.home,
            R.string.menu,
            R.string.coupon,
            R.string.net_service,
            R.string.navi
    };

    public static final HomeNewsItem[] HOME_NEWS_ITEMS = {
            new HomeNewsItem("http://www.dennys.jp/images/menu/menu01.jpg", "2016/02/23", "【店舗限定\n２/２３（火）〜春のハンバーグフェア先行販売"),
            new HomeNewsItem("http://www.dennys.jp/images/menu/saladfair-main-pc.jpg", "2016/02/19", "【店舗限定】江戸川区産小松菜を使った特別メニュー"),
            new HomeNewsItem("http://www.dennys.jp/images/menu/menu02.jpg", "2016/01/25", "期間限定！【店舗限定】朝活⭐トースト＆ゆでたまご＆ヨーグルトが無料！"),
            new HomeNewsItem("http://www.dennys.jp/images/index/menu40.jpg", "2016/01/14", "【２/１８更新！】苺にまつわるおいしい話"),
            new HomeNewsItem("http://www.dennys.jp/images/index/menu40.jpg", "2016/01/12", "あったかメニュー\n１/１４（木）スタート！"),
            new HomeNewsItem("http://www.dennys.jp/images/menu/menu29.jpg", "2016/12/01", "『緑のデニーズ』佐野に１号店がオープン！"),
    };

    public static final SlideItem[] HOME_SLIDER_ITEMS = {
            new SlideItem("1", "http://www.dennys.jp/images/index/menu43.jpg"),
            new SlideItem("2", "http://www.dennys.jp/images/index/slide01-pc.jpg"),
            new SlideItem("3", "http://www.dennys.jp/images/index/menu41.jpg"),
            new SlideItem("4", "http://www.dennys.jp/images/index/menu41.jpg"),
            new SlideItem("5", "http://www.dennys.jp/images/index/menu36.jpg"),
    };

    public static final DennyMenuItem[] FAIR_MENU_ITEMS = {
            new DennyMenuItem("http://www.dennys.jp/images/menu/hotfair_img1.jpg","あったかメニュー"),
            new DennyMenuItem("http://www.dennys.jp/images/menu/strawberryfair-garrett-c.jpg","季節のデザート")
    };

    public static final DennyMenuItem[] GRAND_MENU_ITEMS = {
            new DennyMenuItem("http://www.dennys.jp/images/menu/menu02.jpg","ステーキ・肉料理"),
            new DennyMenuItem("http://www.dennys.jp/images/menu/menu03.jpg","パスタ・ドリア"),
            new DennyMenuItem("http://www.dennys.jp/images/menu/beefstew-omeletrice-c.jpg","ライスプレート"),
            new DennyMenuItem("http://www.dennys.jp/images/menu/friedoyster-japanesestylemeals-c.jpg","麺・丼・膳"),
            new DennyMenuItem("http://www.dennys.jp/images/menu/pumpkin-salad-c.jpg","サラダ。スープ"),
            new DennyMenuItem("http://www.dennys.jp/images/menu/menu01.jpg","ハンバーグ"),
            new DennyMenuItem("http://www.dennys.jp/images/menu/menu15.jpg","パン・サイド"),
            new DennyMenuItem("http://www.dennys.jp/images/menu/menu06.jpg","デザート"),
            new DennyMenuItem("http://www.dennys.jp/images/menu/pourover-coffee-c.jpg","ドリンク"),
            new DennyMenuItem("http://www.dennys.jp/images/menu/orange-juice-c.jpg","アルコール"),
            new DennyMenuItem("http://www.dennys.jp/images/menu/hamburger-and-crab-cream-croquette-c.jpg","平日ランチ限定メニュー"),
            new DennyMenuItem("http://www.dennys.jp/images/menu/chicken-and-croquette-c.jpg","日替わりランチ（平日限定)")
    };

    public static final String[] COUPON_IMAGE_URL = {
            "http://www.dennys.jp/images/denimoba-club/denimoba-coupon/coupon_100off.png",
            "http://www.dennys.jp/images/denimoba-club/denimoba-coupon/coupon_menu23.png",
            "http://www.dennys.jp/images/denimoba-club/denimoba-coupon/coupon_menu20.png",
            "http://www.dennys.jp/images/denimoba-club/denimoba-coupon/coupon_menu11.png",
            "http://www.dennys.jp/images/denimoba-club/denimoba-coupon/coupon_menu12.png",
            "http://www.dennys.jp/images/denimoba-club/denimoba-coupon/coupon_menu1.png",
            "http://www.dennys.jp/images/denimoba-club/denimoba-coupon/coupon_100off.png",
            "http://www.dennys.jp/images/denimoba-club/denimoba-coupon/coupon_menu3.png",
            "http://www.dennys.jp/images/denimoba-club/denimoba-coupon/coupon_menu24.png",
            "http://www.dennys.jp/images/denimoba-club/denimoba-coupon/coupon_menu25.png",
    };
}
