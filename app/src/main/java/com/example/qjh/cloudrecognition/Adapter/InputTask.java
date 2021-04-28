package com.example.qjh.cloudrecognition.Adapter;

import android.content.Context;
import android.util.Log;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.qjh.cloudrecognition.R;

import java.util.ArrayList;

public class InputTask implements PoiSearch.OnPoiSearchListener {
    private static InputTask mInstance;
    private Search_Adapter mAdapter;
    private PoiSearch mSearch;
    private Context mContext;

    private InputTask(Context context, Search_Adapter adapter) {
        this.mContext = context;
        this.mAdapter = adapter;
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {

        if( poiResult != null){
            ArrayList<Items> data = new ArrayList<Items>();
            ArrayList<PoiItem> items = poiResult.getPois();
            for(PoiItem item : items){
                //获取经纬度对象
                LatLonPoint llp = item.getLatLonPoint();
                double lon = llp.getLongitude();
                double lat = llp.getLatitude();
                //获取标题
                String title = item.getTitle();
                //获取内容
                String text = item.getSnippet();
                Log.e("searchTGA",text);
                data.add(new Items(title, R.mipmap.diwei,lon,lat));

            }
            mAdapter.setData(data);
            mAdapter.notifyDataSetChanged();  //适配器回调



        }

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
    /**
     * 设置数据适配器
     * @param adapter
     */
    public void setAdapter(Search_Adapter adapter){
        this.mAdapter = adapter;
    }
    /**
     * POI搜索
     * @param key 关键字
     * @param city 城市
     */
    public void onSearch(String key, String city){
        //POI搜索条件
        PoiSearch.Query query = new PoiSearch.Query(key, "", "桂林");  //指定在桂林城市搜索
        mSearch = new PoiSearch(mContext, query);
        //设置异步监听
        mSearch.setOnPoiSearchListener(this);
        //查询POI异步接口
        mSearch.searchPOIAsyn();
    }
    /**
     * 获取实例
     * @param context 上下文
     * @param adapter 数据适配器
     * @return
     */
    public static InputTask getInstance(Context context, Search_Adapter adapter){
        if(mInstance == null){
            synchronized (InputTask.class) {
                if(mInstance == null){
                    mInstance = new InputTask(context, adapter);
                }
            }
        }
        return mInstance;
    }

}
