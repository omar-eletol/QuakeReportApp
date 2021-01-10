package com.example.android.quakereport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterEarthquake extends ArrayAdapter<information> {


    private static final String LOCATION_SEPARATOR = " of ";

    public AdapterEarthquake(Activity context, ArrayList<information> androidFlavors) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, androidFlavors);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.information_view, parent, false);
        }


        // Get the {@link information} object located at this position in the list
        information currentInformation = getItem(position);


        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentInformation.getmMagnitude());
        // Format the magnitude to show 1 decimal place
        String mag = formatMag(currentInformation.getmMagnitude());
        magnitudeTextView.setText(mag);
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        // separate the location into two strings offset for  (“74km NW of “) and primary for ("city")
        String originalLocation = currentInformation.getmCity();
        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR))
            {
                String[] parts = originalLocation.split(LOCATION_SEPARATOR);
                locationOffset = parts[0] + LOCATION_SEPARATOR;
                primaryLocation = parts[1];
            }
        else
            {
                locationOffset=getContext().getString(R.string.near_the);
                primaryLocation=originalLocation;
            }


        // Find the TextView in thethe information_view.xml layout with the ID version_name
        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.offset);
        offsetTextView.setText(locationOffset);

        // Find the TextView in thethe information_view.xml layout with the ID version_name
        TextView cityTextView = (TextView) listItemView.findViewById(R.id.primary);
        cityTextView.setText(primaryLocation);

        // Create a new Date object from the time in milliseconds of the earthquake
         Date dateObject = new Date(currentInformation.getTimeInMilliseconds());


         // Find the TextView in the information_view.xml layout with the ID version_name
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        //Display the formatted date
        dateTextView.setText(formatDate(dateObject));



        // Find the TextView in  information_view.xml layout layout with the ID version_name
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        // Display the formatted time
        timeTextView.setText(formatTime(dateObject));



        return listItemView ;

    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMag (double mag)
    {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(mag);

    }

    private int getMagnitudeColor(double mag){

        int magnitudeFloor = (int) Math.floor(mag);
        int magnitude1Color;
        switch(magnitudeFloor) {
            case 0:
            case 1:
                magnitude1Color = R.color.magnitude1 ;
                break;
            case 2 :
                magnitude1Color = R.color.magnitude2 ;
                break;
            case 3:
                magnitude1Color = R.color.magnitude3 ;
                break;
            case 4 :
                magnitude1Color = R.color.magnitude4 ;
                break;
            case 5 :
                magnitude1Color = R.color.magnitude5 ;
                break;
            case 6 :
                magnitude1Color = R.color.magnitude6 ;
                break;
            case 7 :
                magnitude1Color = R.color.magnitude7 ;
                break;
            case 8 :
                magnitude1Color = R.color.magnitude8 ;
                break;
            case 9 :
                magnitude1Color = R.color.magnitude9 ;
                break;
            default:
                magnitude1Color = R.color.magnitude10plus ;
                break;
        }
        return  ContextCompat.getColor(getContext(), magnitude1Color) ;
    }


}
