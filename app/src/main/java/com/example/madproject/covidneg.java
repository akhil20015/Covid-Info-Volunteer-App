package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class covidneg extends AppCompatActivity implements View.OnClickListener  {
    private Button volu , vacc , prof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidneg);

        volu=(Button)findViewById(R.id.vol);
        volu.setOnClickListener(this);

        vacc=(Button)findViewById(R.id.vac);
        vacc.setOnClickListener(this);

        prof=(Button)findViewById(R.id.pro);
        prof.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {

            case R.id.vol:
            {startActivity(new Intent(covidneg.this ,volunteer.class));break;}
            case R.id.vac:
            {startActivity(new Intent(covidneg.this ,vaccine.class));break;}
            case R.id.pro:
            {startActivity(new Intent(covidneg.this ,profile.class));break;}

        }

    }
}