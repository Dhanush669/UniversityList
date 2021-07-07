package com.devgd.universitylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UniversityAdapter adapter;
    CardView stateColor;
    TextView stateText;
    UniversityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        recyclerView=findViewById(R.id.recyclerView);
        adapter=new UniversityAdapter(this);
        stateColor=findViewById(R.id.stateColor);
        stateText=findViewById(R.id.stateText);
        recyclerView.setAdapter(adapter);
        viewModel=new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.
                getInstance(this.getApplication())).get(UniversityViewModel.class);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if(netInfo!=null) {
            Online();
            getApiData();
        }
        else {
            Offline();
            getLocalData();
        }
    }

    private void getLocalData() {
        viewModel.GetOfflineData().observe(this, new Observer<List<UniversityModelClass>>() {
            @Override
            public void onChanged(List<UniversityModelClass> universityModelClasses) {
                adapter.setList(universityModelClasses);
                recyclerView.setAdapter(adapter);
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                if(universityModelClasses.size()==0){
                    Toast.makeText(MainActivity.this, "No Data Available!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getApiData() {
        viewModel.GetOnlineData().observe(this, new Observer<List<UniversityModelClass>>() {
            @Override
            public void onChanged(List<UniversityModelClass> universityModelClasses) {
                adapter.setList(universityModelClasses);
                recyclerView.setAdapter(adapter);
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(staggeredGridLayoutManager);
            }
        });
    }

    public void Online(){
        stateText.setText("Online");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            stateText.setTextColor(this.getColor(R.color.online));
            stateColor.setCardBackgroundColor(this.getColor(R.color.online));
        }
    }

    public void Offline(){
        stateText.setText("Offline");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            stateText.setTextColor(this.getColor(R.color.offline));
            stateColor.setCardBackgroundColor(this.getColor(R.color.offline));
        }
    }
}