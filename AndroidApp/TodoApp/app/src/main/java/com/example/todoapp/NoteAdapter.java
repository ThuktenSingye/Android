package com.example.todoapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public CheckBox delete;
        public TextView note;
        public TextView description;
        public ImageView edit;

        public ViewHolder(View itemView){
            super(itemView);

            delete = (CheckBox) itemView.findViewById(R.id.delete_btn);
            note = (TextView) itemView.findViewById(R.id.note_title);
            edit = (ImageView) itemView.findViewById(R.id.edit_icon);
            description = (TextView) itemView.findViewById(R.id.note_description);

            // for todo deletion
            delete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Context context = delete.getContext();
                    AlertDialog.Builder delete_builder =  new AlertDialog.Builder(context);
                    delete_builder.setTitle("Confirm Delete?");
                    delete_builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Retrieve the updated title and description from EditTexts
                            if (b){
                                int position = getAdapterPosition();
                                if(position != RecyclerView.NO_POSITION){
                                    MainActivity.notes.remove(position);
                                    notifyItemRemoved(position);
                                }
                            }

                            // Notify the adapter that the data has changed
                            notifyDataSetChanged();

                            // Dismiss the dialog
                            dialogInterface.dismiss();
                        }
                    });
                    delete_builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    delete_builder.create().show();


                }
            });

//            for task edition
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = edit.getContext();
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        Note update_note = MainActivity.notes.get(position);
                        AlertDialog.Builder update_builder =  new AlertDialog.Builder(context);
                        update_builder.setTitle("Update Note");

                        View dialogView = LayoutInflater.from(context).inflate(R.layout.edit_note, null);
                        EditText titleEditText = dialogView.findViewById(R.id.edit_title);
                        EditText descriptionEditText = dialogView.findViewById(R.id.edit_description);

                        // populate the edit text with existing data
                        titleEditText.setText(update_note.getTitle());
                        descriptionEditText.setText(update_note.getDescription());

                        update_builder.setView(dialogView);

//                        adding button for saving and canceling the edit
                        update_builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Retrieve the updated title and description from EditTexts
                                String updatedTitle = titleEditText.getText().toString();
                                String originalDescription = descriptionEditText.getText().toString();
                                String updatedDescription;
                                if (originalDescription.length() > 20) {
                                    updatedDescription = originalDescription.substring(0, 20) + "...";
                                } else {
                                    updatedDescription = originalDescription;
                                }

                                // Update the item in the ArrayList with the new data
                                update_note.setTitle(updatedTitle);
                                update_note.setDescription(updatedDescription);

                                // Notify the adapter that the data has changed
                                notifyDataSetChanged();

                                // Dismiss the dialog
                                dialogInterface.dismiss();
                            }
                        });
                        update_builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        update_builder.create().show();


                    }
                }
            });

        }
    }
    ArrayList<Note> noteL;
    // Pass in the contact array into the constructor
    public NoteAdapter(ArrayList<Note> note) {
        noteL = note;
    }
    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // try to inflate the layout and pass to view holder by creating instances of view holder
        Context context = parent.getContext(); // get the pa
        LayoutInflater inflater = LayoutInflater.from(context);
//        inflate custom view
        View contactView =  inflater.inflate(R.layout.note_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note noted = noteL.get(position);
        TextView textview = holder.note;
        TextView des_text = holder.description;
        textview.setText(noted.getTitle());
        des_text.setText(noted.getDescription());
    }
    @Override
    public int getItemCount() {
        return noteL.size();
    }
}
