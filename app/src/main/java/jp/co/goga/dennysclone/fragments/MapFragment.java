package jp.co.goga.dennysclone.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.goga.dennysclone.R;
import jp.co.goga.dennysclone.adapter.SearchOptionAdapter;
import jp.co.goga.dennysclone.util.Constant;

/**
 * Created by khanhtq on 3/8/16.
 */
public class MapFragment extends BaseFragment implements OnMapReadyCallback,
        GoogleMap.OnCameraChangeListener,
        GoogleMap.OnInfoWindowClickListener,
        LocationListener, View.OnTouchListener {
    public static final String TAG = MapFragment.class.getSimpleName();
    private static final String CURRENT_TAB = "current_tab";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private final int TAB_LOCATION = -1;
    private final int TAB_STORE = -2;


    private RelativeLayout mSearchTabLayout;
    private CheckedTextView mTabLocation, mTabStore;
    private ImageButton mSearchButton, mExpandButton;
    private EditText mSearchBox;
    private ExpandableListView mListOptionView;

    private ExpandableListAdapter mAdapter;

    private List<String> mListGroup;
    private Map<String, List<Object>> mListChild;
    private int mCurrentTabSelect;
    private boolean shouldCollapse;

    private GoogleMap mMap;
    private LocationManager mLocationManager;

    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map;
    }

    @Override
    protected boolean shouldShowBack() {
        return true;
    }

    @Override
    protected int getTitleId() {
        return R.string.map;
    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {
        mSearchTabLayout = (RelativeLayout) mRootView.findViewById(R.id.search_tab);
        mTabStore = (CheckedTextView) mSearchTabLayout.findViewById(R.id.store_tab);
        mTabLocation = (CheckedTextView) mSearchTabLayout.findViewById(R.id.location_tab);

        mSearchBox = (EditText) mRootView.findViewById(R.id.map_search_edittext);
        mSearchButton = (ImageButton) mRootView.findViewById(R.id.map_search_button);
        mExpandButton = (ImageButton) mRootView.findViewById(R.id.map_search_expand_button);

        mListOptionView = (ExpandableListView) mRootView.findViewById(R.id.map_search_option_listview);

        prepareOptionData();

        mAdapter = new SearchOptionAdapter(getContext(), mListGroup, mListChild);
        mListOptionView.setAdapter(mAdapter);

        mSearchBox.setOnFocusChangeListener(focusChangeListener);
        mExpandButton.setOnClickListener(this);
        mSearchButton.setOnClickListener(this);

        mTabLocation.setOnTouchListener(this);
        mTabStore.setOnTouchListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void prepareOptionData() {
        mListGroup = new ArrayList<>();
        mListGroup.add("絞り込み検索");
        mListGroup.add("店舗が見つかりません");

        mListChild = new HashMap<>();
        List<Object> firstChildList = new ArrayList<>();
        firstChildList.addAll(Arrays.asList(Constant.MAP_SEARCH_OPTION_ITEMS));
        mListChild.put("絞り込み検索", firstChildList);
        mListChild.put("店舗が見つかりません", firstChildList);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mCurrentTabSelect = savedInstanceState.getInt(CURRENT_TAB);
        } else {
            mCurrentTabSelect = TAB_LOCATION;
        }
        shouldCollapse = true;
        collapseAndExpandView();
        selectTab();
    }

    private void selectTab() {
        if (mCurrentTabSelect == TAB_LOCATION) {
            mSearchBox.setHint("地名・駅名で探す");
            mTabLocation.setChecked(true);
            mTabStore.setChecked(false);
        } else {
            mSearchBox.setHint("店名で探す");
            mTabLocation.setChecked(false);
            mTabStore.setChecked(true);
        }
    }

    private void collapseAndExpandView() {
        if (shouldCollapse) {
            mExpandButton.setImageResource(R.drawable.ic_expand_more_black_24dp);
            mListOptionView.setVisibility(View.GONE);
            mSearchTabLayout.setVisibility(View.GONE);
        } else {
            mExpandButton.setImageResource(R.drawable.ic_expand_less_black_24dp);
            mListOptionView.setVisibility(View.VISIBLE);
            mSearchTabLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onViewClick(int id) {
        View v = mRootView.findViewById(id);
        v.requestFocus();
        switch (id) {
            case R.id.map_search_expand_button:
                shouldCollapse = (!shouldCollapse);
                collapseAndExpandView();
                break;
            case R.id.map_search_button:
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng newPos = new LatLng(latitude, longitude);
        if (mMap != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(newPos));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(Constant.DEFAULT_ZOOM_LEVEL));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setPadding(0,120,0,0);
        mMap.setOnCameraChangeListener(this);
        mMap.setOnInfoWindowClickListener(this);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        enableMyLocation();
    }

    /**
     * Enable current location
     */
    private void enableMyLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Not grant permission");
            if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else if (mMap != null) {
            Log.d(TAG, "Map != null");
            mMap.setMyLocationEnabled(true);
            mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            String provider = mLocationManager.getBestProvider(new Criteria(), true);
            Location location = mLocationManager.getLastKnownLocation(provider);
            if (location != null) {
                onLocationChanged(location);
            }
            mLocationManager.requestLocationUpdates(provider, Constant.UPDATE_LOCATION_TIME, 0, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation();
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.requestFocus();
        switch (v.getId()) {
            case R.id.location_tab:
                mCurrentTabSelect = TAB_LOCATION;
                selectTab();
                return true;
            case R.id.store_tab:
                mCurrentTabSelect = TAB_STORE;
                selectTab();
                return true;
            default:
                return false;
        }
    }
}
