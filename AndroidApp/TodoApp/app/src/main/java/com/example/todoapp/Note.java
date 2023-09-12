package com.example.todoapp;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

public class Note {
    private String title;
    private String description;
    public Note(String title, String description,Context context){
        this.title = title;
        this.description = description;
        saveToSharedPreferences(context);
    }
    public String getTitle(){
        return this.title;
    }
    public String getDescription(){
        return this.description;
    }
    public void setTitle(String title){
        this.title= title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    private void saveToSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("note_title", title);
        editor.putString("note_description", description);
        editor.apply();
    }
}