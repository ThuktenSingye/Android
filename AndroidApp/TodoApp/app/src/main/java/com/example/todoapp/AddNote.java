package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNote extends AppCompatActivity {
    TextView title;
    TextView description;
    Button add;
    Button reset;
    public String note_title;
    public String note_des;
    ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        title =  findViewById(R.id.title);
        description = findViewById(R.id.description);
        add = findViewById(R.id.add_btn);
        reset = findViewById(R.id.reset_btn);
        notes = new ArrayList<Note>();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//          fetch the given data in here
                note_title = title.getText().toString();
                note_des =  description.getText().toString();
                String updatedDescription;
                if (note_des.length() > 20) {
                    updatedDescription = note_des.substring(0, 20) + "...";
                } else {
                    updatedDescription = note_des;
                }
                Note note = new Note(note_title,updatedDescription);
                MainActivity.notes.add(note);
                MainActivity.noteAdapter.notifyDataSetChanged();
                view.clearFocus();
                clearText();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             clear the data
                clearText();
            }
        });

    }
    public void clearText(){
        title.setText("");
        description.setText("");
    }
}