package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class covidstatus extends AppCompatActivity implements View.OnClickListener {

    private Button yes ,no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidstatus);

        yes =(Button)findViewById(R.id.yes);
        yes.setOnClickListener(this);

        no=(Button)findViewById(R.id.no);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

            User update = new User();

        switch (view.getId())
        {
            case R.id.yes:
                update.covid="yes";
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("covid")
                        .setValue(update.covid);
                startActivity(new Intent(covidstatus.this,covidpos.class));
                break;

            case R.id.no:
                update.covid="no";
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("covid")
                        .setValue(update.covid);
                startActivity(new Intent(covidstatus.this,covidneg.class));
                break;
        }
    }
}