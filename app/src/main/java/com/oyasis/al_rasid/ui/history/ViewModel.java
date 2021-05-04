package com.oyasis.al_rasid.ui.history;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.oyasis.al_rasid.ADDatabase;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Speed>> speedData;

    public ViewModel(@NonNull Application application) {
        super(application);
        speedData = new MutableLiveData<>();
    }

    MutableLiveData<List<Speed>> getSpeedData(Context ctx){
        ADDatabase database = ADDatabase.getInstance(ctx);
        SpeedDao dao = database.getSpeedDao();
        speedData.postValue(dao.getSpeed());
        return speedData;
    }



}
