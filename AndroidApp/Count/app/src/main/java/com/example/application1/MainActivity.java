package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.textView2);
        Button ctn = findViewById(R.id.button2);
        Button toast = findViewById(R.id.button);
        Button random = findViewById(R.id.button3);
//        Button next = findViewById(R.id.next_btn);

        ctn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num += 1;
                text.setText(String.valueOf(num));
            }
        });
        toast.setOnClickListener(new View.OnClickListener() {
            @Override
//            String.valueOf() or toString()
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "This is the toast with number: "+String.valueOf(num), Toast.LENGTH_SHORT).show();
            }
        });

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rand = (int) Math.ceil(Math.random() * 1000);
                text.setText(String.valueOf(rand));
            }
        });

    }

}