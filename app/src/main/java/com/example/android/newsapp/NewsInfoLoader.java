package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import java.util.List;

public class NewsInfoLoader  extends AsyncTaskLoader<List<Technology>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = NewsInfoLoader.class.getName();

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link NewsInfoLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public NewsInfoLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "Loader java class onStartLoading is called.");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Technology> loadInBackground() {
        Log.i(LOG_TAG, "Loader java class loadinBackground is called.");
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of technology.
        List<Technology> articles = QueryUtils.fetchArticleData(mUrl);

        return articles;
    }
}




