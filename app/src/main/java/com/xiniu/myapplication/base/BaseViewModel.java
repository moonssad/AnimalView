package com.xiniu.myapplication.base;

import android.app.Application;
import android.arch.lifecycle.ViewModel;

import com.xiniu.myapplication.MyApplication;
import com.xiniu.myapplication.mvvm.room.DataHandBase;

/**
 * 创建者：wyz
 * 创建时间：2020/4/16
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public abstract class BaseViewModel  extends ViewModel {
    public BaseViewModel() {
        super();
        onCreate();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        onDestroy();

    }

    public abstract void  onCreate();
    public abstract void onDestroy();

}
