package com.example.android.popular_movies_master;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chinky on 7/3/2016.
 */
public class MovieTrailers implements Parcelable {

    public MovieTrailers(String movieID, String video_key, String video_name) {
    }

    public static final Parcelable.Creator<MovieTrailers> CREATOR = new Parcelable.Creator<MovieTrailers>() {
        @Override
        public MovieTrailers createFromParcel(Parcel in) {
            return new MovieTrailers(in);
        }

        @Override
        public MovieTrailers[] newArray(int size) {
            return new MovieTrailers[size];
        }
    };

    private String VideoKey;
    private String VideoName;


    protected MovieTrailers(Parcel in) {
        VideoKey=in.readString();
        VideoName = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(VideoKey);
        dest.writeString(VideoName);

    }

    public String getVideoKey() {
        return VideoKey;
    }

    public void setVideoKey(String videoKey) {
        VideoKey = videoKey;
    }

    public String getVideoName() {
        return VideoName;
    }

    public void setVideoName(String videoName) {
        VideoName = videoName;
    }




}
