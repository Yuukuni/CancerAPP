package com.example.huangyuwei.myapplication.database;

import android.arch.persistence.room.Entity;

/**
 * Created by XiDream on 2018/6/1.
 */

@Entity(tableName = "TargetCure", primaryKeys = {"createDate", "createTime"})
public class TargetCure {
    public int createDate;
    public int createTime;
    public String startDate;
    public String endDate;
    public String cure;
    public int checkAmount;
}
