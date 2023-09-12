package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image2);
        imageView = findViewById(R.id.image);

        // Get the selected image resource ID from the intent
        int imageResource = getIntent().getIntExtra("imageResource", 0);

        if (imageResource != 0) {
            imageView.setImageResource(imageResource);
        }
    }
}