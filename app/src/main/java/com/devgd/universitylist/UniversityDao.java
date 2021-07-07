package com.devgd.universitylist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UniversityDao {

    @Insert
    void insert(UniversityModelClass modelClass);

    @Query("SELECT * FROM UniversityModelClass")
    LiveData<List<UniversityModelClass>> getOfflineData();
}
