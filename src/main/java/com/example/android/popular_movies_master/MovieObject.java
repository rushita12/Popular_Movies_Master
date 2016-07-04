package com.example.android.popular_movies_master;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chinky on 7/3/2016.
 */
public class MovieObject implements Parcelable {


    public MovieObject(String string, String cursorString, String s, String string1, double aDouble, String cursorString1) {


    }
    public static final Creator<MovieObject> CREATOR = new Creator<MovieObject>() {
        @Override
        public MovieObject createFromParcel(Parcel in) {
            return new MovieObject(in);
        }

        @Override
        public MovieObject[] newArray(int size) {
            return new MovieObject[size];
        }
    };


    private String MovieID;
    private String title;
    private String poster;
    private String overview;
    private int Rating;
    private String Release;





    protected MovieObject(Parcel in) {
        MovieID=in.readString();
        title = in.readString();
        poster = in.readString();
        overview = in.readString();
        Rating = in.readInt();
        Release = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(MovieID);
        dest.writeString(title);
        dest.writeString(poster);
        dest.writeString(overview);
        dest.writeInt(Rating);
        dest.writeString(Release);
    }

    public String getMovieID() {
        return MovieID;
    }

    public void setMovieID(String id) {
        this.MovieID = id;
    }


    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster_path) {
        this.poster = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String original_title) {
        this.title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int vote_average) {
        this.Rating = vote_average;
    }

    public String getRelease() {
        return Release;
    }

    public void setRelease(String release_date) {
        this.Release = release_date;
    }
}
