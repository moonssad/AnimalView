package com.xiniu.myapplication;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.xiniu.myapplication.base.BaseViewModel;
import com.xiniu.myapplication.mvvm.room.UserData;

import java.util.List;

/**
 * 创建者：wyz
 * 创建时间：2020/4/16
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class MainView extends BaseViewModel {

    public ObservableList<UserData> userDatas;
    public ObservableField <UserData> userData;

    @Override
    public void onCreate() {
        userDatas = new ObservableArrayList<UserData>();
        userData= new ObservableField<UserData>(new UserData("1","1"));
    }

    @Override
    public void onDestroy() {

    }

    public void setUserDatas(List<UserData> userDatas) {
        this.userDatas.addAll(userDatas);
        this.userData.set(userDatas.get(0));
    }

}
