package com.example.divya.watchlist.network;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Divya on 11-05-2017.
 */

public class NetworkUtils {


    private static final String  TMDB_BASE_URL = "https://api.themoviedb.org/3";
    private static final String API_KEY = "api_key";
    private static final  String API_KEY_VALUE = "6b7085c6deee4086616c8dae1c1ada12";
    private static final String MOVIE = "movie";
    private static final String TV_SHOWS = "tv";
    private static final String PEOPLE = "person";
    private static final String POPULAR ="popular";
    private static final String TOP_RATED = "top_rated";
    private static final String NOW_PLAYING = "now_playing";
    private static final String UPCOMING = "upcoming";

    public static URL buildPeopleUrl() {
        // TODO (1) Fill in this method to build the proper Github query URL
        Uri buildUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendPath(PEOPLE)
                .appendPath(POPULAR)
                .appendQueryParameter(API_KEY,API_KEY_VALUE)
                .build();

        URL url = null;
        try{
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildPopularMovieUrl() {
        // TODO (1) Fill in this method to build the proper Github query URL
        Uri buildUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendPath(MOVIE)
                .appendPath(POPULAR)
                .appendQueryParameter(API_KEY,API_KEY_VALUE)
                .build();

        URL url = null;
        try{
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildNowPlayingUrl() {
        // TODO (1) Fill in this method to build the proper Github query URL
        Uri buildUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendPath(MOVIE)
                .appendPath(NOW_PLAYING)
                .appendQueryParameter(API_KEY,API_KEY_VALUE)
                .build();

        URL url = null;
        try{
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
