package com.example.huangyuwei.myapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by XiDream on 2018/6/2.
 */
@Dao
public interface HormoneDao {
    @Query("SELECT * FROM Hormone")
    List<Hormone> getAllHormone();

    @Insert
    void insertHormone(Hormone... Hormones);

    @Update
    void updateHormone(Hormone... Hormones);

    @Delete
    void delete(Hormone... Hormones);
}