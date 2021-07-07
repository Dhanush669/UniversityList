package com.devgd.universitylist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = UniversityModelClass.class,version = 1)
public abstract class UniversityRoom extends RoomDatabase {
    private static UniversityRoom instance;

    public abstract UniversityDao universityDao();

    public static synchronized UniversityRoom getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),UniversityRoom.class,"UniversityRoom")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
