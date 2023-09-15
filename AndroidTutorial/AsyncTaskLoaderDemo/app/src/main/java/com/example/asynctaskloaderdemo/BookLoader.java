package com.example.asynctaskloaderdemo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader {
    private String mQueryString;
    public BookLoader(@NonNull Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    // below method is automatically called loader is instantiated
    public Object loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }
}
