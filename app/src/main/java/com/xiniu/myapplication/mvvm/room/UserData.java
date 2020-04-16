package com.xiniu.myapplication.mvvm.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * 创建者：wyz
 * 创建时间：2020/4/16
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
@Entity(tableName = "user_data")
public class UserData {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private  String age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public UserData(){}

    public UserData(String name,String age){
        this.name=name;
        this.age=age;
    }
    }

