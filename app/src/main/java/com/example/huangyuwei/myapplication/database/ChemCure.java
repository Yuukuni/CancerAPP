package com.example.huangyuwei.myapplication.database;

import android.arch.persistence.room.Entity;

/**
 * Created by user-pc on 2017/12/17.
 */
@Entity(tableName = "ChemCure", primaryKeys = {"createDate", "createTime"})
public class ChemCure {
    public int createDate;
    public int createTime;
    public String date;
    public String cure;
    public int checkAmount;
}
