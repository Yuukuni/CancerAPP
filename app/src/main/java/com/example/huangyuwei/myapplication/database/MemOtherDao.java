package com.example.huangyuwei.myapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MemOtherDao {
    @Query("SELECT * FROM memother")
    List<MemOther> getAllMemOther();

    @Insert
    void insertMemOther(MemOther... memOthers);

    @Update
    void updateMemOther(MemOther... memOthers);

    @Delete
    void delete(MemOther... memOthers);
}
