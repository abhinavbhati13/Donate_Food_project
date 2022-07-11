package com.example.food_donate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NGOPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_g_o_page);
        Button searchfood = findViewById(R.id.searchfood);
        searchfood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("searchfood Button Clicked");
                Intent activity2Intent = new Intent(getApplicationContext(), SearchFood.class);
                startActivity(activity2Intent);
                }

        });
        Button  trackfood = findViewById(R.id.trackfood);
        trackfood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("searchfood Button Clicked");
                Intent activity2Intent = new Intent(getApplicationContext(), TrackFood.class);
                startActivity(activity2Intent);
            }

        });

    }
}