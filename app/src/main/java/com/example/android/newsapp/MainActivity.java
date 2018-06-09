package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Technology>> {
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?" +
            "from-date=2018-06-06&q=technology%20&api-key=test&show-tags=contributor";
    private static final int GUARDIAN_LOADER_ID = 1;
    private NewsInfoAdapter mAdapter;
    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView technologyListView = findViewById(R.id.list);
        // Create a new adapter that takes an empty list of technology as input
        mAdapter = new NewsInfoAdapter(this, new ArrayList<Technology>());
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        technologyListView.setAdapter(mAdapter);
        mEmptyStateTextView = findViewById(R.id.empty_view);
        technologyListView.setEmptyView(mEmptyStateTextView);
        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected technology.
        technologyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current article that was clicked on
                Technology currentTechnology = mAdapter.getItem(position);


                // Create a new intent to view the article URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentTechnology.getUrl()));

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            Log.i(LOG_TAG, "Test:calling initLoader()");
            loaderManager.initLoader(GUARDIAN_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.load_indicator);
            loadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            mEmptyStateTextView.setTextSize(24);
            mEmptyStateTextView.setPadding(16, 16, 16, 16);
        }
    }

    @Override
    public Loader<List<Technology>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "Test: onCreateLoader()called");
        // Create a new loader for the given URL
        return new NewsInfoLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Technology>> loader, List<Technology> technologies) {
        Log.i(LOG_TAG, "Test: onLoadFinished()called");
        View loadingIndicator = findViewById(R.id.load_indicator);
        loadingIndicator.setVisibility(View.GONE);
        // Clear the adapter of previous data
        mAdapter.clear();
        // If there is a valid list of {@link Technology}s, then add them to the adapter's
        // data set. This will trigger the ListView to update
        if (technologies != null && !technologies.isEmpty()) {
            //add new
            mAdapter.addAll(technologies);
            mEmptyStateTextView.setText(R.string.no_technology);
            mEmptyStateTextView.setPadding(16, 16, 16, 16);
            mEmptyStateTextView.setTextSize(24);
        } else {
            mEmptyStateTextView.setText(getString(R.string.no_technology_news_found));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Technology>> loader) {
        Log.i(LOG_TAG, "Test: onLoaderReset()called");
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();

    }

}






