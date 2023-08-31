package com.example.recycleview;

import java.util.ArrayList;
//this class will be used to create data item to display in recycle view

public class Contact {
    private String name;
    private boolean mOnline;
    private static int lastContactId = 0;
    public Contact(String name, boolean mOnline){
        this.name = name;
        this.mOnline = mOnline;
    }
    public String getName(){
        return this.name;
    }
    public boolean getOnline(){
        return this.mOnline;
    }
    public static ArrayList<Contact> createContactList(int contactNum){ // for creating the data item in arraylist
        ArrayList<Contact> contacts =  new ArrayList<Contact>();

        for(int i=0; i<=contactNum; i++){
            contacts.add(new Contact("Person "+ ++lastContactId, i <= contactNum /2));
        }
        return contacts;
    }
}
