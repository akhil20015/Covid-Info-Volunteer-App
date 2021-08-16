package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class covidpos extends AppCompatActivity implements View.OnClickListener {

    private Button help , vaccine , profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidpos);

        help=(Button)findViewById(R.id.help);
        help.setOnClickListener(this);

        vaccine=(Button)findViewById(R.id.vaccine);
        vaccine.setOnClickListener(this);

        profile=(Button)findViewById(R.id.profile);
        profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {


            case R.id.help:
            {startActivity(new Intent(covidpos.this,help.class));
            break;}
            case R.id.vaccine:
            {startActivity(new Intent(covidpos.this,vaccine.class));break;}
            case R.id.profile:
            {startActivity(new Intent(covidpos.this ,profile.class));break;}
        }

    }
}