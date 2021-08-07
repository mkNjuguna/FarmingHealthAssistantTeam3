package com.example.farminghealthassistant;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LogInActivity extends AppCompatActivity {

    Button login_btn;
    EditText login_email, login_password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_btn = findViewById(R.id.login_button);
        login_email = findViewById(R.id.login_email_input);
        login_password = findViewById(R.id.login_password_input);
        mAuth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(view -> loginUser());
    }

    public void loginUser(){
        String user_email = login_email.getText().toString();
        String user_password = login_password.getText().toString();

        if (TextUtils.isEmpty(user_email)){
            login_email.setError("Email cannot be empty. Kindly key in your email address");
            login_email.requestFocus();
        } else if (TextUtils.isEmpty(user_password)){
            login_password.setError("Password cannot be empty. Kindly key in your password.");
            login_password.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(user_email, user_password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            Toast.makeText(LogInActivity.this, "You have successfully logged in!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LogInActivity.this, TakingCropPicsActivity.class));
                        } else {
                            Toast.makeText(LogInActivity.this, "Login failed:" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

}
