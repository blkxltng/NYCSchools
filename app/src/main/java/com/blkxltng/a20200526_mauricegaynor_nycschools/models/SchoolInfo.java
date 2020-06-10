package com.blkxltng.a20200526_mauricegaynor_nycschools.models;

import android.os.Parcel;
import android.os.Parcelable;

// Model we'll use for the school info
public class SchoolInfo implements Parcelable {

    private String dbn;
    private String school_name;
    private String overview_paragraph;
    private String location;
    private String phone_number;
    private String school_email;
    private String website;
    private String total_students;
    private String neighborhood;
    private String borough;

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getOverview_paragraph() {
        return overview_paragraph;
    }

    public void setOverview_paragraph(String overview_paragraph) {
        this.overview_paragraph = overview_paragraph;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSchool_email() {
        return school_email;
    }

    public void setSchool_email(String school_email) {
        this.school_email = school_email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTotal_students() {
        return total_students;
    }

    public void setTotal_students(String total_students) {
        this.total_students = total_students;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    protected SchoolInfo(Parcel in) {
        dbn = in.readString();
        school_name = in.readString();
        overview_paragraph = in.readString();
        location = in.readString();
        phone_number = in.readString();
        school_email = in.readString();
        website = in.readString();
        total_students = in.readString();
        neighborhood = in.readString();
        borough = in.readString();
    }

    public SchoolInfo() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dbn);
        dest.writeString(school_name);
        dest.writeString(overview_paragraph);
        dest.writeString(location);
        dest.writeString(phone_number);
        dest.writeString(school_email);
        dest.writeString(website);
        dest.writeString(total_students);
        dest.writeString(neighborhood);
        dest.writeString(borough);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SchoolInfo> CREATOR = new Parcelable.Creator<SchoolInfo>() {
        @Override
        public SchoolInfo createFromParcel(Parcel in) {
            return new SchoolInfo(in);
        }

        @Override
        public SchoolInfo[] newArray(int size) {
            return new SchoolInfo[size];
        }
    };
}