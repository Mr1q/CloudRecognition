package com.example.qjh.cloudrecognition.Control;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.qjh.cloudrecognition.Activity.MainActivity;
import com.example.qjh.cloudrecognition.Adapter.InputTask;
import com.example.qjh.cloudrecognition.Adapter.Items;
import com.example.qjh.cloudrecognition.Adapter.Search_Adapter;
import com.example.qjh.cloudrecognition.R;

import java.util.ArrayList;
import java.util.List;

public class SearchPOI extends BaseActivity implements TextWatcher {
    private EditText search_msg; //搜索框
    private List<Items> itemslist = new ArrayList<Items>();
 private    Search_Adapter search_adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_poi);
        search_msg = (EditText) findViewById(R.id.Search_MSG);
        search_msg.requestFocus(); //自动获取焦点
        Window window = this.getWindow();
        View decorView = window.getDecorView();
        int option = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        decorView.setSystemUiVisibility(option);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        for (int i = 0; i < 10; i++) {
            Items items = new Items("测试", R.mipmap.diwei,1,1);
            itemslist.add(items);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.Data_List);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
         search_adapter = new Search_Adapter(itemslist);
        recyclerView.setAdapter(search_adapter);
        search_adapter.setOnItemClick(new Search_Adapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Items items) {
                Intent intent=new Intent(SearchPOI.this, MainActivity.class);
                intent.putExtra("Lon",items.getLon());
                intent.putExtra("Lat",items.getLat());
                finish();
                startActivity(intent);
            }
        });
        search_msg.addTextChangedListener(this);


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        InputTask.getInstance(this, search_adapter).onSearch(s.toString(), "");
        Log.e("searchTGA",s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
