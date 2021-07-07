package com.devgd.universitylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class UniversityDetails extends AppCompatActivity {
    TextView name,country,state;
    CardView cardView;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_details);
        name=findViewById(R.id.CollegeName);
        country=findViewById(R.id.country);
        state=findViewById(R.id.state);
        cardView=findViewById(R.id.detCardView);
        Intent intent=getIntent();
        name.setText(intent.getStringExtra("name"));
        country.setText(intent.getStringExtra("country"));
        state.setText(intent.getStringExtra("state"));
        url=intent.getStringExtra("url");
    }

    public void open(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}