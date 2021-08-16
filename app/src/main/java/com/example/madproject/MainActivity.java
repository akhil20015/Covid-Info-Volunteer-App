package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register,forgotpassword;
    private EditText etemail , etpassword;
    private Button signin;

    private FirebaseAuth mauth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView)findViewById(R.id.register);
        register.setOnClickListener(this);

        signin=(Button)findViewById(R.id.signin);
        signin.setOnClickListener(this);

        etemail=(EditText)findViewById(R.id.email);
        etpassword=(EditText)findViewById(R.id.password);

        forgotpassword=(TextView)findViewById(R.id.forgotpassword);
        forgotpassword.setOnClickListener(this);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        mauth= FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.register:
                startActivity(new Intent(this , Registeruser.class));
                break;
            case R.id.signin:
                userlogin();
                break;
            case R.id.forgotpassword:
                startActivity(new Intent(MainActivity.this,forgotpassword.class));
                break;
        }

    }

    private void userlogin() {

        String email=etemail.getText().toString().trim();
        String password=etpassword.getText().toString().trim();

        if(email.isEmpty())
        {
            etemail.setError("Email is required");
            etemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etemail.setError("Please enter a valid email");
            etemail.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            etpassword.setError("Password is required");
            etpassword.requestFocus();
            return;
        }

        if(password.length() <6)
        {
            etpassword.setError("Min password length should be 6 characters");
            etpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mauth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(MainActivity.this,covidstatus.class));
                            progressBar.setVisibility(View.GONE);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Failed to login",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}