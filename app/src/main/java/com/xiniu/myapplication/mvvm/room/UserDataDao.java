package com.xiniu.myapplication.mvvm.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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
@Dao
public interface UserDataDao {

    @Query("select * from user_data")
    LiveData<List<UserData>> getAll();

    @Update()
    void updata(UserData data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserData data);

}
