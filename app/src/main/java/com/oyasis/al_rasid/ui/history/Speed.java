package com.oyasis.al_rasid.ui.history;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "speed")
public class Speed {
    @PrimaryKey(autoGenerate = true)
    int id;

    float speed;
    String movingTo;
    String pathToPickure;
    String numberPlate;
    String movingFrom;
    String thumbNailPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getMovingTo() {
        return movingTo;
    }

    public void setMovingTo(String movingTo) {
        this.movingTo = movingTo;
    }

    public String getPathToPickure() {
        return pathToPickure;
    }

    public void setPathToPickure(String pathToPickure) {
        this.pathToPickure = pathToPickure;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getMovingFrom() {
        return movingFrom;
    }

    public void setMovingFrom(String movingFrom) {
        this.movingFrom = movingFrom;
    }

    public String getThumbNailPath() {
        return thumbNailPath;
    }

    public void setThumbNailPath(String thumbNailPath) {
        this.thumbNailPath = thumbNailPath;
    }
}
