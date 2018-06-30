package com.example.huangyuwei.myapplication.database;

import android.arch.persistence.room.Entity;

/**
 * Created by goodweather on 2018/1/14.
 */
@Entity(tableName = "PutCure", primaryKeys = {"createDate", "createTime"})
public class PutCure {
    public int createDate;
    public int createTime;
    public String start;
    public String end;
    public String place;
    public int checkAmount;
}
