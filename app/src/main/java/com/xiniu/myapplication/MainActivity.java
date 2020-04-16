package com.xiniu.myapplication;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xiniu.myapplication.Views.DanmuSurfaceView;
import com.xiniu.myapplication.databinding.ActivityMainBinding;
import com.xiniu.myapplication.mvvm.room.DataHandBase;
import com.xiniu.myapplication.mvvm.room.Test;
import com.xiniu.myapplication.mvvm.room.UserData;
import com.xiniu.myapplication.mvvm.room.UserDataDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DanmuSurfaceView mdanmuSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

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
        UserDataDao dao =DataHandBase.instance(getApplication()).userDataDao();
        dao.getAll().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(@Nullable List<UserData> userData) {
                //todo
                Log.e("12","122  "+userData.size());
                binding.setUser(userData.get(userData.size()-1));
            }
        });

        AppExectors.getInstance().getmDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                UserDataDao dao =DataHandBase.instance(getApplication()).userDataDao();
                 dao.insert(new UserData("123","1232"));
                 for (int i = 0; i < 10; i++){
                     try {
                         Thread.sleep(1000);
                         dao.insert(new UserData(""+i,"12232"));
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }

            }
        });

    }
}
