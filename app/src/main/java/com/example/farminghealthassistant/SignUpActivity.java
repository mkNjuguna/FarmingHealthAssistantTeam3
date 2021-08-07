package com.example.farminghealthassistant;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    //Initializing the button
    Button login_btn, signup_btn;
    EditText signup_email, signup_password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        login_btn = findViewById(R.id.login_link_btn);
        signup_btn = findViewById(R.id.signup_button);
        signup_email = findViewById(R.id.signup_email_input);
        signup_password = findViewById(R.id.signup_password_input);

        signup_btn.setOnClickListener(v -> signupUser());

        login_btn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
            startActivity(intent);
        });
    }

    public void signupUser(){
        String user_email = signup_email.getText().toString();
        String user_password = signup_password.getText().toString();

        if (TextUtils.isEmpty(user_email)){
            signup_email.setError("Email cannot be empty. Kindly key in your email address.");
            signup_email.requestFocus();
        } else if (TextUtils.isEmpty(user_password)){
            signup_password.setError("Password cannot be empty. Kindly set a password.");
            signup_password.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(user_email, user_password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "You have successfully signed up!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Signup failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}