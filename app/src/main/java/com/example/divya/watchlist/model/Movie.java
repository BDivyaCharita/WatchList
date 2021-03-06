package com.example.divya.watchlist.model;

/**
 * Created by Divya on 08-05-2017.
 */

public class Movie {

    private String poster_path;
    private String overview;
    private String release_date;
    private String id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private String vote_average;

    public Movie(){}

    public Movie(String title){
        this.title = title;
    }

    public Movie(String id,String overview,String poster_path,String backdrop_path){
        this.id = id;
        this.overview=overview;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;

    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }


    @Override
    public String toString() {
        return "ID: "+id+
                "OVERVIEW: " +overview+
                "POSTER: " +poster_path+
                "BACKDROP: " +backdrop_path;
    }

}
