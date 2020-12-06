package com.example.monthspendingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textViewMainTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewMainTitle = findViewById(R.id.textViewMainTitle);
    }

    public void startCreateActivity(View view){
        Intent intent = new Intent(this,CreateActivity.class);
        startActivity(intent);
    }
    public void startPlacesActivity(View view){
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
    public void startSearchActivity(View view){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }
    public void startUpdateActivity(View view){
        Intent intent = new Intent(this,UpdateActivity.class);
        startActivity(intent);
    }
    public void startDeleteActivity(View view){
        Intent intent = new Intent(this,DeleteActivity.class);
        startActivity(intent);
    }
}