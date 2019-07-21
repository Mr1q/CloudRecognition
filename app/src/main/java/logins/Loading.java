package logins;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.qjh.cloudrecognition.R;
import com.google.gson.Gson;

import java.io.IOException;

import Control.BaseActivity;
import cn.bmob.v3.Bmob;
import okhttp3.Call;
import okhttp3.Response;

public class Loading extends BaseActivity {
    final String url = "http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";
    //    获取必应每 日一图的url地址
    ImageView imageView;
    Bing_Paper bing_paper;
    private static final String TAG = "Loadings";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_image);
        Bmob.initialize(this, "5f3159ccadb84248c8a152f043b30e3d");
        imageView = (ImageView) findViewById(R.id.begin_image);
        BaseActivity.Set_Window(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtill.sendOkHttpRequest(url, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String Content = response.body().string();
                        bing_paper = new Gson().fromJson(Content, Bing_Paper.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Glide.with(Loading.this).load("http://cn.bing.com" +
                                        bing_paper.images.get(0).partUrl).placeholder(R.mipmap.cloud).
                                        diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                            }
                        });
                    }
                });
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(Loading.this, Login.class);
                    startActivity(intent);
                    finish(); //结束当前活动

                }
            }
        }).start();


    }
}
