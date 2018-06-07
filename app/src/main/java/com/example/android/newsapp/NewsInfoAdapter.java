package com.example.android.newsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class NewsInfoAdapter extends ArrayAdapter<Technology> {
    private static final String LOG_TAG = NewsInfoAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     * @param context  The current context. Used to inflate the layout file.
     * @param articles A List of AndroidFlavor objects to display in a list
     */
    public NewsInfoAdapter(Activity context, ArrayList<Technology> articles) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, articles);
    }

    /**
     * Provides a view for an AdapterView
     * @param position The position in the list of data that should be displayed in the
     *            list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_list_items, parent, false);
        }
        // Find the article news at the given position in the list of newsApps.
        Technology currentArticles = getItem(position);
        TextView sectionTextView =  convertView.findViewById(R.id.section);
        sectionTextView.setText(currentArticles.getSection());
        TextView webTitleTextView = convertView.findViewById(R.id.web_title);
        webTitleTextView.setText(currentArticles.getTitle());
        TextView dateTextView = convertView.findViewById(R.id.date);
        dateTextView.setText(currentArticles.getDate());
        TextView authorTextView = convertView.findViewById(R.id.author);
        authorTextView.setText(currentArticles.getAuthor());
        // Return the list item view that is now showing the appropriate data
        return convertView;

    }
}
