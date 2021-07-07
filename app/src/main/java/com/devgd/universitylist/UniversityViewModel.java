package com.devgd.universitylist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UniversityViewModel extends AndroidViewModel {
    LiveData<List<UniversityModelClass>> getOfflineData;
    LiveData<List<UniversityModelClass>> getOnlineData;
    UniversityRepo repo;
    public UniversityViewModel(@NonNull Application application) {
        super(application);
        repo=new UniversityRepo(application);
        getOfflineData=repo.getOfflineData();
        getOnlineData=repo.getOnlineData();
    }

    public LiveData<List<UniversityModelClass>> GetOfflineData(){
        return getOfflineData;
    }

    public LiveData<List<UniversityModelClass>> GetOnlineData(){
        return getOnlineData;
    }
}
