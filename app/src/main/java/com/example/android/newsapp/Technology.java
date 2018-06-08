package com.example.android.newsapp;

public class Technology {
    private String mUrl;
    private String mTitle;
    private String mSection;
    private String mDate;
    private String mAuthor;

    Technology(String url, String title, String section, String date, String author) {
        mUrl = url;
        mTitle = title;
        mSection = section;
        mDate = date;
        mAuthor = author;

    }

    public String getUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getDate() {
        return mDate;
    }

    public String getAuthor() {
        return mAuthor;
    }
}

