package com.example.divya.watchlist.extras;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.divya.watchlist.model.People;
import com.example.divya.watchlist.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.example.divya.watchlist.extras.Keys.KeysPeople.KEY_ID;
import static com.example.divya.watchlist.extras.Keys.KeysPeople.KEY_NAME;
import static com.example.divya.watchlist.extras.Keys.KeysPeople.KEY_POPULARITY;
import static com.example.divya.watchlist.extras.Keys.KeysPeople.KEY_PROFILE_PATH;
import static com.example.divya.watchlist.extras.Keys.KeysPeople.KEY_RESULTS;


/**
 * Created by Divya on 12-05-2017.
 */

public class VolleyJSONRequests {


    private static RequestQueue requestQueue;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;


    public static void sendPeopleJSONRequest(String URL, final List<People> peopleList) {
        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parsePeopleJSONResponse(response, peopleList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);

    }

    private static void parsePeopleJSONResponse(JSONObject response, List<People> peopleList) {
        if (response == null || response.length() == 0) {
            return;
        }
        try {
            if (response.has(KEY_RESULTS)) {
                JSONArray arrayMovies = response.getJSONArray(KEY_RESULTS);
                for (int i = 0; i < arrayMovies.length(); i++) {
                    JSONObject currentPerson = arrayMovies.getJSONObject(i);

                    String profile_path = currentPerson.getString(KEY_PROFILE_PATH);
                    String id = currentPerson.getString(KEY_ID);
                    String name = currentPerson.getString(KEY_NAME);
                    String popularity = currentPerson.getString(KEY_POPULARITY);

                    People person = new People();

                    person.setProfile_path(profile_path);
                    person.setId(id);
                    person.setName(name);
                    person.setPopularity(popularity);
                    Log.e("Person Name", name);


                    peopleList.add(person);

                }

            }
        } catch (JSONException e) {

        }
    }

}
