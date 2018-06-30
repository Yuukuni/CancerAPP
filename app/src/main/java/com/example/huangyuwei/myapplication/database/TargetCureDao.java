package com.example.huangyuwei.myapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by XiDream on 2018/6/1.
 */

@Dao
public interface TargetCureDao {
    @Query("SELECT * FROM TargetCure")
    List<TargetCure> getAllTargetCure();

    @Insert
    void insertTargetCure(TargetCure... TargetCures);

    @Update
    void updateTargetCure(TargetCure... TargetCures);

    @Delete
    void delete(TargetCure... TargetCures);
}
