package com.example.tictactoegame;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.*;

import java.security.cert.CertificateParsingException;

public class Player extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        fetch button and add two listern
        Button singleP_btn = (Button) findViewById(R.id.single_btn);
        Button multiP_btn = (Button) findViewById(R.id.multi_btn);

//        redirect to single player interface
        singleP_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent single_intent = new Intent(Player.this, SinglePlayer.class);
                Toast.makeText(Player.this, "Single Player Mode", Toast.LENGTH_LONG).show();
                startActivity(single_intent);

            }
        });

//        redirect to multiplayer interface
        multiP_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent multi_intent = new Intent(Player.this, MultiPlayer.class);
                Toast.makeText(Player.this, "Multi Player Mode", Toast.LENGTH_LONG).show();
                startActivity(multi_intent);
            }
        });
    }
}
