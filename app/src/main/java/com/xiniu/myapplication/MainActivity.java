package com.xiniu.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import com.xiniu.myapplication.Views.DanmuSurfaceView;
import com.xiniu.myapplication.base.BaseActivity;
import com.xiniu.myapplication.databinding.ActivityMainBinding;
import com.xiniu.myapplication.mvvm.room.DataHandBase;

import com.xiniu.myapplication.mvvm.room.UserData;
import com.xiniu.myapplication.mvvm.room.UserDataDao;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainView> {


    @Override
    protected Class<MainView> getViewModelClazz() {
        return MainView.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        dataBinding.danmu.addLayoutListener(new DanmuSurfaceView.LayoutListener() {
            @Override
            public void onCreateView() {
                //todo 插入数据
                dataBinding.danmu.addText("纯电动工作模式介绍");
               dataBinding.danmu.addText("安全气囊触发条件");
               dataBinding.danmu.addText("安全带使用");
               dataBinding.danmu.addText("安全气囊的作用");
               dataBinding.danmu.addText("座椅安全带怎么使用");
            }
        });
        UserDataDao dao =DataHandBase.instance(getApplication()).userDataDao();
        dao.getAll().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(@Nullable List<UserData> userData) {
                //todo
                viewModel.setUserDatas(userData);
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
