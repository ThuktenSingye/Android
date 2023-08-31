package com.example.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{ // provide direct reference to the view in the data item / represent individual item
        public TextView nameTextView;
        public Button messageButton;

        public ViewHolder(View itemview){ // itemview is tha layout of the single item in th list

            super(itemview);
            nameTextView = (TextView) itemview.findViewById(R.id.contact_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);

        }

    }
    // Store a member variable for the contacts
    private List<Contact> mContacts;

    // Pass in the contact array into the constructor
    public ContactsAdapter(List<Contact> contacts) {
        mContacts = contacts;
    }
    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // try to inflate the layout and pass to view holder by creating instances of view holder
        Context context = parent.getContext(); // get the pa
        LayoutInflater inflater = LayoutInflater.from(context);
//        inflate custom view
        View contactView =  inflater.inflate(R.layout.item_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = mContacts.get(position);

        TextView textview = holder.nameTextView;
        textview.setText(contact.getName());
        Button button = holder.messageButton;
        button.setText(contact.getOnline() ? "Message":"Offline");
        button.setEnabled(contact.getOnline());

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
