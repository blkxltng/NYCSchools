package com.blkxltng.a20200526_mauricegaynor_nycschools.utils;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.blkxltng.a20200526_mauricegaynor_nycschools.R;
import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SatScores;

public class BindingUtils {

    // Used to remove the latitude and longitude from the location string
    @BindingAdapter("schoolLocation")
    public static void schoolLocation(TextView textView, String location) {
        int startIndex = location.indexOf("(");
        int endIndex = location.indexOf(")");
        String toReplace = location.substring(startIndex - 1, endIndex + 1);
        textView.setText(location.replace(toReplace, ""));
    }

    // If no email is detected, say so
    @BindingAdapter("schoolEmail")
    public static void schoolEmail(TextView textView, String email) {
        if (email == null || email.isEmpty()) {
            textView.setText(R.string.no_email_found);
            textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
        } else {
            textView.setText(email);
        }
    }

    // If no phone number is detected, say so
    @BindingAdapter("schoolPhone")
    public static void schoolPhone(TextView textView, String phone) {
        if (phone == null || phone.isEmpty()) {
            textView.setText(R.string.no_phone_number_found);
            textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
        } else {
            textView.setText(phone);
        }
    }

    // If no website is detected, say so
    @BindingAdapter("schoolWebsite")
    public static void schoolWebsite(TextView textView, String url) {
        if (url == null || url.isEmpty()) {
            textView.setText(R.string.no_website_found);
            textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
        } else {
            textView.setText(url);
        }
    }

    // Hide the school overview section if one does not exist
    @BindingAdapter("schoolOverviewVisibility")
    public static void schoolOverviewVisibility(View textView, String overview) {
        if(overview == null || overview.isEmpty()) {
            textView.setVisibility(View.GONE);
        }
    }

    // Hide the SAT score section if no scores are found
    @BindingAdapter("scoreVisibility")
    public static void scoreVisibility(View view, SatScores scores) {
        if(scores == null) {
            view.setVisibility(View.GONE);
        }
    }
}
