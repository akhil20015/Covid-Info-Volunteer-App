package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class forgotpassword extends AppCompatActivity {

    private EditText etemail;
    private Button resetpassword;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        etemail=(EditText)findViewById(R.id.email);
        resetpassword=(Button)findViewById(R.id.resetpasword);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        auth=FirebaseAuth.getInstance();

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetpassword();
            }
        });
    }

    private void resetpassword(){
        String email=etemail.getText().toString().trim();

        if(email.isEmpty())
        {
            etemail.setError("Email is required");
            etemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etemail.setError("Invalid email address");
            etemail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(forgotpassword.this,"Check your email to reset your password",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(forgotpassword.this,"Failed , try again later",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}