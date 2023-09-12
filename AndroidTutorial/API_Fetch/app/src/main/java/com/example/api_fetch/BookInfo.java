package com.example.api_fetch;

public class BookInfo {
    private String title;
    private String authors;
    private String description;
    private String error;
    public BookInfo(String error){
        this.error = error;
    }

    public BookInfo(String title, String authors, String description) {
        this.title = title;
        this.authors = authors;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }
    public String getError() {
        return error;
    }
    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor(s): " + authors + "\nDescription: " + description;
    }
}