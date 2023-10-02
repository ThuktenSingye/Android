package com.example.apifetchdemo;

import java.util.List;

public class University {
    private String id;
    private String price;

    public University(String id, String price) {
        this.id = id;
        this.price = price;

    }
    // Create getters for the fields
    public String getID() {
        return this.id;
    }

    public String getPrice() {
        return this.price;
    }

}
