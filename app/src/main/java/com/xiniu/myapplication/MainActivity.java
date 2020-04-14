package com.xiniu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiniu.myapplication.Views.DanmuSurfaceView;

public class MainActivity extends AppCompatActivity {
    DanmuSurfaceView mdanmuSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdanmuSurfaceView = (DanmuSurfaceView) findViewById(R.id.danmu);
        mdanmuSurfaceView.addLayoutListener(new DanmuSurfaceView.LayoutListener() {
            @Override
            public void onCreateView() {
                //todo 插入数据
                mdanmuSurfaceView.addText("纯电动工作模式介绍");
                mdanmuSurfaceView.addText("安全气囊触发条件");
                mdanmuSurfaceView.addText("安全带使用");
                mdanmuSurfaceView.addText("安全气囊的作用");
                mdanmuSurfaceView.addText("座椅安全带怎么使用");
            }
        });

    }
}
