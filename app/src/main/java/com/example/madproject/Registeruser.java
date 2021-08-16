package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Registeruser extends AppCompatActivity implements View.OnClickListener {

    private Button registeruser;
    private EditText etname, etphone, etemail, etpass;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);

        mAuth = FirebaseAuth.getInstance();

        registeruser = (Button) findViewById(R.id.registeruser);
        registeruser.setOnClickListener(this);

        etname = (EditText) findViewById(R.id.name);
        etphone = (EditText) findViewById(R.id.phoneno);
        etemail = (EditText) findViewById(R.id.email);
        etpass = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        etname.requestFocus();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.registeruser:
                registeruser();
                break;
        }


    }

    private void registeruser(){

        String email = etemail.getText().toString().trim();
        String name = etname.getText().toString().trim();
        String password = etpass.getText().toString().trim();
        String phoneno = etphone.getText().toString().trim();

        if (name.isEmpty()) {
            etname.setError("Name is required");
            etname.requestFocus();
            return;
        }

        if (phoneno.isEmpty()) {
            etphone.setError("Phone number is required");
            etphone.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            etemail.setError("Email is required");
            etemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etemail.setError("Provide a valid email id");
            etemail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            etpass.setError("Password is required");
            etpass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etpass.setError("Min password length should be 6 characters");
            etpass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(name, phoneno, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Registeruser.this, "User has been registerd successfully", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        //redirect to login
                                    } else {
                                        Toast.makeText(Registeruser.this, "Failed to register !", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                    EditText temp;
                                    final int[] txtId = new int[] {
                                            R.id.name,
                                            R.id.phoneno,
                                            R.id.email,
                                            R.id.password,
                                    };

                                    for (int i = 0; i < txtId.length; i++) {
                                        temp = (EditText) findViewById(txtId[i]);
                                        temp.setText(null);
                                    }

                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(Registeruser.this,"Failed to register",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}



