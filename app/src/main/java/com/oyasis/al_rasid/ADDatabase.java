package com.oyasis.al_rasid;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.oyasis.al_rasid.ui.history.Speed;
import com.oyasis.al_rasid.ui.history.SpeedDao;

@Database(entities = {Speed.class}, version = 1, exportSchema = true)
public  abstract class ADDatabase extends  RoomDatabase{
    public abstract SpeedDao getSpeedDao();
    public static volatile ADDatabase INSTANCE;

    public static ADDatabase  getInstance(Context ctx) {
        if(INSTANCE == null) {
            synchronized (ADDatabase.class) {
                INSTANCE = Room.databaseBuilder(ctx,ADDatabase.class, "AlRasid")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }

}
