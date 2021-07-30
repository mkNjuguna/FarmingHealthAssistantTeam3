package com.example.farminghealthassistant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TakingCropPicsActivity extends AppCompatActivity {

    private static final int picid = 1;

    ImageButton camerabtn;
    ImageView takenphoto;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takingcrop_pics);

        camerabtn = findViewById(R.id.imageButton);
        takenphoto = findViewById(R.id.imageView);

        camerabtn.setOnClickListener(v -> {
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera, picid);
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == picid) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            takenphoto.setImageBitmap(photo);
        }

    }
}
