package com.example.farminghealthassistant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TakingCropPicsActivity extends AppCompatActivity {

    private static final int picid = 1;

    ImageButton camera_btn;
    ImageView taken_photo;
    Button logout_btn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takingcrop_pics);

        camera_btn = findViewById(R.id.imageButton);
        taken_photo = findViewById(R.id.imageView);
        logout_btn = findViewById(R.id.logout_btn);
        mAuth = FirebaseAuth.getInstance();

        camera_btn.setOnClickListener(v -> {
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera, picid);
        });

        logout_btn.setOnClickListener(view -> logoutUser());

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == picid) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            taken_photo.setImageBitmap(photo);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            startActivity(new Intent(TakingCropPicsActivity.this, LogInActivity.class));
        }

    }

    private void logoutUser(){
        mAuth.signOut();
        Toast.makeText(TakingCropPicsActivity.this, "You have successfully logged out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(TakingCropPicsActivity.this, LogInActivity.class));

    }
}
