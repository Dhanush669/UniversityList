package com.devgd.universitylist;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UniversityRepo {
    UniversityRoom room;
    UniversityDao dao;
    StringRequest request;
    String url;
    Context context;
    ExecutorService service;
    List<UniversityModelClass> universityList;
    LiveData<List<UniversityModelClass>> offLineData;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    public static final String firstTime="FIRST_TIME";
    UniversityRepo(Application application){
        room=UniversityRoom.getInstance(application);
        dao=room.universityDao();
        offLineData=dao.getOfflineData();
        url="http://universities.hipolabs.com/search?country=India";
        service= Executors.newFixedThreadPool(1);
        universityList=new ArrayList<>();
        context=application;
        sharedPreferences=context.getSharedPreferences("preference",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    LiveData<List<UniversityModelClass>> getOnlineData(){
        MutableLiveData<List<UniversityModelClass>> mutableLiveData=new MutableLiveData<>();
        service.execute(new Runnable() {
            @Override
            public void run() {
        universityList=new ArrayList<>();
        request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //universityList=new ArrayList<>();
                //Log.i("outside try", String.valueOf(universityList.size()));
                try {
                    //Log.i("inside try", String.valueOf(universityList.size()));
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        UniversityModelClass modelClass=new UniversityModelClass(
                                jsonObject.getString("name"),
                                jsonObject.getString("country"),
                                jsonObject.getString("state-province"),
                                jsonObject.getJSONArray("web_pages").getString(0)
                        );
                        universityList.add(modelClass);
                    }

                    mutableLiveData.setValue(universityList);
                    if(sharedPreferences.getString(firstTime,null)==null) {
                        insert();
                        editor.putString(firstTime,"done");
                        editor.apply();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("inside catch", String.valueOf(universityList.size()));
                }
            }
        },error -> {
        });
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(request);
            }
        });
        return mutableLiveData;
    }

    public void insert(){

        service.execute(() -> {
            for(int i=0;i<20;i++) {
                UniversityModelClass modelClass=universityList.get(i);
                dao.insert(modelClass);
            }
        });
    }

    LiveData<List<UniversityModelClass>> getOfflineData(){
        return offLineData;
    }
}
