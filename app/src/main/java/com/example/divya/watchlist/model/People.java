package com.example.divya.watchlist.model;

/**
 * Created by Divya on 12-05-2017.
 */

public class People {

    private String profile_path;
    private String id;
    private String name;
    private String popularity;

    public People(){}

    public People(String profile_path, String id, String name, String popularity){

        this.profile_path = profile_path;
        this.id = id;
        this.name = name;
        this.popularity = popularity;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "PROFILE PATH: "+profile_path+
                "ID: " +id+
                "NAME: " +name+
                "POPULARITY: " +popularity;
    }

}
