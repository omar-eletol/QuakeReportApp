package com.example.android.quakereport;

public class information {

    private double mMagnitude;
    private String mCity;
    private long mTimeInMilliseconds;
    private String mUrl ;

    public information(double mMagnitude, String mCity, long mTimeInMilliseconds, String url ) {
        this.mMagnitude = mMagnitude;
        this.mCity = mCity;
        this.mTimeInMilliseconds = mTimeInMilliseconds;
        this.mUrl = url;
    }


    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmCity() {
        return mCity;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
    public String getUrl() {
        return mUrl;
    }
}