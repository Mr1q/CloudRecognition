package com.example.qjh.cloudrecognition.user_msg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.qjh.cloudrecognition.R;

import com.example.qjh.cloudrecognition.Control.BaseActivity;

public class Me_Cot extends BaseActivity {
    private Toolbar toolbar_base;
    private CollapsingToolbarLayout CTL;
    private AppBarLayout appBar;
    private FloatingActionButton User_Enter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_msg);
        toolbar_base=(Toolbar)findViewById(R.id.toolbar_base);
        setSupportActionBar(toolbar_base);
        BaseActivity.Set_Window(this);
        CTL=(CollapsingToolbarLayout)findViewById(R.id.CTL);
        appBar=(AppBarLayout)findViewById(R.id.appbar);


        User_Enter=(FloatingActionButton)findViewById(R.id.User_Enter);
        User_Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Me_Cot.this,User_Msg.class);
                startActivity(intent);
            }
        });
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -200) {
                    CTL.setTitle("个人中心");//设置标题
                    //使用下面两个CollapsingToolbarLayout的方法设置展开透明->折叠时你想要的颜色
                    CTL.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
                    CTL.setCollapsedTitleTextColor(getResources().getColor(R.color.Black));
                } else {
                    CTL.setTitle("");
                }
            }
        });
    }
}
