package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class volunteer extends AppCompatActivity implements  View.OnClickListener {

    private Spinner spinner;
    private Button submit;
    private String[] dropdown ={"Yes","No"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(volunteer.this,android.R.layout.simple_spinner_item,dropdown);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        submit=(Button)findViewById(R.id.button);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.button:
                getselected();
                break;
        }
    }

    private void getselected() {
        User update=new User();
        int position =spinner.getSelectedItemPosition();
        String item=spinner.getSelectedItem().toString();

        if(position == 0 )
        {
            update.volunteer=item;
            FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("volunteer")
                    .setValue(update.volunteer);
        }
        else
        {
            update.volunteer=item;
            FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("volunteer")
                    .setValue(update.volunteer);
        }
    }
}