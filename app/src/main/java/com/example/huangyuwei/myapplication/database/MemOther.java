package com.example.huangyuwei.myapplication.database;

import android.arch.persistence.room.Entity;

@Entity(tableName = "memother", primaryKeys = {"date_id"})
public class MemOther {
    public Integer date_id;
    public String text;
    public String otherText;
}