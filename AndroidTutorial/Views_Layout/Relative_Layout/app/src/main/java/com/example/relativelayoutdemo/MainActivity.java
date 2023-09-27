package com.example.relativelayoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView child1 = findViewById(R.id.child1);
        ImageView child2 = findViewById(R.id.child2);
        child1.bringToFront();
        child1.invalidate();
    }
}