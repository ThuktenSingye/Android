package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add_btn;
    RecyclerView rvNote;
    static ArrayList<Note> notes = new ArrayList<Note>();
    static NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_btn = findViewById(R.id.add_btn);
        rvNote = (RecyclerView) findViewById(R.id.rvList);
        noteAdapter = new NoteAdapter(notes);
        rvNote.setAdapter(noteAdapter);
        rvNote.setLayoutManager(new LinearLayoutManager(this));
        loadNotesFromSharedPreferences();

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent note_intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(note_intent);
            }
        });

    }
    private void loadNotesFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        // You can use a for loop or other logic to retrieve notes and add them to the list
        int noteCount = sharedPreferences.getInt("MyPrefs",0);
        for(int i = 0; i<=noteCount; i++){
            String title = sharedPreferences.getString("note_title", "");
            String description = sharedPreferences.getString("note_description", "");
            Note note = new Note(title, description, getApplicationContext());
            notes.add(note);
            noteAdapter.notifyDataSetChanged();
            
        }
        // Example:
       
        }
    

}