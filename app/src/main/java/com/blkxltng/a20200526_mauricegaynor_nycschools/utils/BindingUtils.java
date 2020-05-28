package com.blkxltng.a20200526_mauricegaynor_nycschools.utils;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class BindingUtils {

    @BindingAdapter("schoolLocation")
    public static void schoolLocation(TextView textView, String location) {
        int startIndex = location.indexOf("(");
        int endIndex = location.indexOf(")");
        String toReplace = location.substring(startIndex - 1, endIndex + 1);
        textView.setText(location.replace(toReplace, ""));
    }
}
