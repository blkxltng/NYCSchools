package com.blkxltng.a20200526_mauricegaynor_nycschools.models;

import android.os.Parcel;
import android.os.Parcelable;

// Model we'll use for the SAT scores
public class SatScores implements Parcelable {

    private String dbn;
    private String school_name;
    private String num_of_sat_test_takers;
    private String sat_critical_reading_avg_score;
    private String sat_math_avg_score;
    private String sat_writing_avg_score;

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

    public String getNum_of_sat_test_takers() {
        return num_of_sat_test_takers;
    }

    public void setNum_of_sat_test_takers(String num_of_sat_test_takers) {
        this.num_of_sat_test_takers = num_of_sat_test_takers;
    }

    public String getSat_critical_reading_avg_score() {
        return sat_critical_reading_avg_score;
    }

    public void setSat_critical_reading_avg_score(String sat_critical_reading_avg_score) {
        this.sat_critical_reading_avg_score = sat_critical_reading_avg_score;
    }

    public String getSat_math_avg_score() {
        return sat_math_avg_score;
    }

    public void setSat_math_avg_score(String sat_math_avg_score) {
        this.sat_math_avg_score = sat_math_avg_score;
    }

    public String getSat_writing_avg_score() {
        return sat_writing_avg_score;
    }

    public void setSat_writing_avg_score(String sat_writing_avg_score) {
        this.sat_writing_avg_score = sat_writing_avg_score;
    }

    protected SatScores(Parcel in) {
        dbn = in.readString();
        school_name = in.readString();
        num_of_sat_test_takers = in.readString();
        sat_critical_reading_avg_score = in.readString();
        sat_math_avg_score = in.readString();
        sat_writing_avg_score = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dbn);
        dest.writeString(school_name);
        dest.writeString(num_of_sat_test_takers);
        dest.writeString(sat_critical_reading_avg_score);
        dest.writeString(sat_math_avg_score);
        dest.writeString(sat_writing_avg_score);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SatScores> CREATOR = new Parcelable.Creator<SatScores>() {
        @Override
        public SatScores createFromParcel(Parcel in) {
            return new SatScores(in);
        }

        @Override
        public SatScores[] newArray(int size) {
            return new SatScores[size];
        }
    };
}