package com.example.mt.orderweed;

/**
 * Created by mt on 8/2/2018.
 */

public class review_item
{
    int rate;
    String user_name;
    int user_image;
    String review;

    public review_item(int rate, String user_name, int user_image, String review) {
        this.rate = rate;
        this.user_name = user_name;
        this.user_image = user_image;
        this.review = review;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_image() {
        return user_image;
    }

    public void setUser_image(int user_image) {
        this.user_image = user_image;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
