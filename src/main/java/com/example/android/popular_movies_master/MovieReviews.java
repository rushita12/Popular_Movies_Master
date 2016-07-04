package com.example.android.popular_movies_master;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chinky on 7/3/2016.
 */
public class MovieReviews implements Parcelable {

    public MovieReviews(String movieID, String review_AUTHOR, String review_CONTENT)
    {

    }

    public static final Parcelable.Creator<MovieReviews> CREATOR = new Parcelable.Creator<MovieReviews>() {
        @Override
        public MovieReviews createFromParcel(Parcel in) {
            return new MovieReviews(in);
        }

        @Override
        public MovieReviews[] newArray(int size) {
            return new MovieReviews[size];
        }
    };




    private String review_AUTHOR;
    private String review_CONTENT;

    protected MovieReviews(Parcel in) {
        review_AUTHOR=in.readString();
        review_CONTENT = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(review_AUTHOR);
        dest.writeString(review_CONTENT);

    }

    public String getReviewAuthor() {
        return review_AUTHOR;
    }

    public void setReviewAuthor(String review_AUTHOR) {
        this.review_AUTHOR = review_AUTHOR;
    }

    public String getReviewContents() {
        return review_CONTENT;
    }

    public void setReviewContents(String review_CONTENT) {
        this.review_CONTENT = review_CONTENT;
    }


}


