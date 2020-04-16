package com.xiniu.myapplication.mvvm.room;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

/**
 * 创建者：wyz
 * 创建时间：2020/4/16
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */

@Database(entities = {UserData.class}, version = 1,exportSchema = false)

public abstract class  DataHandBase extends RoomDatabase{

    public abstract UserDataDao userDataDao();

    private static DataHandBase instance =null;
    public static DataHandBase instance(Application application) {
        if (instance==null){
            synchronized(DataHandBase.class){
                if (instance==null){
                    instance = Room.databaseBuilder(application, DataHandBase.class, "HandBook").build();
                }
            }
        }
        return instance;

    }
}
