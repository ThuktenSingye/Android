package com.example.listviewdemo;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
//        Button button = findViewById(R.id.button);
        EditText text = findViewById(R.id.place_name);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Enter the location");

        text=new EditText(this);
        builder.setView(text);

        //POSITIVE BUTTON
        EditText finalText = text;
        builder.setPositiveButton("Search", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String input= finalText.getText().toString();
                Toast.makeText(getApplicationContext(),"Searching "+input,Toast.LENGTH_LONG).show();
                if (!input.isEmpty()) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + input);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");

                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    } else {
                        Uri webIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + input);
                        Intent webIntent = new Intent(Intent.ACTION_VIEW, webIntentUri);
                        startActivity(webIntent);
                    }
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        AlertDialog ad= builder.create();
        ad.show();

    }
}