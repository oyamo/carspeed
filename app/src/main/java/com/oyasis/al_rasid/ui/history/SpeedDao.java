package com.oyasis.al_rasid.ui.history;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface SpeedDao {
    @Query("SELECT * FROM speed")
    public List<Speed> getSpeed();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addNew(Speed speed);
}
