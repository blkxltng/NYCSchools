package com.blkxltng.a20200526_mauricegaynor_nycschools.utils;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SatScores;

public class BindingUtils {

    @BindingAdapter("schoolLocation")
    public static void schoolLocation(TextView textView, String location) {
        int startIndex = location.indexOf("(");
        int endIndex = location.indexOf(")");
        String toReplace = location.substring(startIndex - 1, endIndex + 1);
        textView.setText(location.replace(toReplace, ""));
    }

    @BindingAdapter("schoolEmail")
    public static void schoolEmail(TextView textView, String email) {
        if (email == null || email.isEmpty()) {
            textView.setText("-- No email found --");
            textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
        } else {
            textView.setText(email);
        }
    }

    @BindingAdapter("schoolPhone")
    public static void schoolPhone(TextView textView, String phone) {
        if (phone == null || phone.isEmpty()) {
            textView.setText("-- No phone number found --");
            textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
        } else {
            textView.setText(phone);
        }
    }

    @BindingAdapter("schoolWebsite")
    public static void schoolWebsite(TextView textView, String url) {
        if (url == null || url.isEmpty()) {
            textView.setText("-- No website found --");
            textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
        } else {
            textView.setText(url);
        }
    }

    @BindingAdapter("schoolOverviewVisibility")
    public static void schoolOverviewVisibility(View textView, String overview) {
        if(overview == null || overview.isEmpty()) {
            textView.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("scoreVisibility")
    public static void scoreVisibility(View view, SatScores scores) {
        if(scores == null) {
            view.setVisibility(View.GONE);
        }
    }
}
