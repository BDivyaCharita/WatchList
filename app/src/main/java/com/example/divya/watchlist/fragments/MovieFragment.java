package com.example.divya.watchlist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.example.divya.watchlist.R;
import com.example.divya.watchlist.adapters.MovieAdapter;
import com.example.divya.watchlist.extras.Keys;
import com.example.divya.watchlist.model.Movie;
import com.example.divya.watchlist.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.divya.watchlist.extras.Keys.KeysMovies.*;

import static com.example.divya.watchlist.extras.Keys.KeysMovies.KEY_BACKDROP_PATH;
import static com.example.divya.watchlist.extras.Keys.KeysMovies.KEY_ID;
import static com.example.divya.watchlist.extras.Keys.KeysMovies.KEY_OVERVIEW;
import static com.example.divya.watchlist.extras.Keys.KeysMovies.KEY_POSTER_PATH;
import static com.example.divya.watchlist.extras.Keys.KeysMovies.KEY_RESULTS;

/**
 * Created by Divya on 07-05-2017.
 */

public class MovieFragment extends Fragment {
    private static final String ARG_PAGE = "ARG_PAGE";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String URL = "https://api.themoviedb.org/3/movie/popular?api_key=6b7085c6deee4086616c8dae1c1ada12";
    private  VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private  RequestQueue requestQueue;
    private ImageView pic1, pic2, pic3, pic4;

    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Movie> movieList = new ArrayList<>();
    private List<Movie> list;


    private int mPage;

    public static MovieFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);

       // args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
        MovieFragment fragment = new MovieFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
       // mParam1 = getArguments().getString(ARG_PARAM1);
       // mParam2 = getArguments().getString(ARG_PARAM2);

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        sendJSONRequest();
        }

    private void sendJSONRequest() {
        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseSONResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);

    }

    private void parseSONResponse(JSONObject response) {
        if (response == null || response.length() == 0) {
            return;
        }
        try {
            //StringBuilder data = new StringBuilder();
            if(response.has(KEY_RESULTS)) {
                JSONArray arrayMovies = response.getJSONArray(KEY_RESULTS);
                for (int i = 0; i < arrayMovies.length() ; i++) {
                    JSONObject currentMovie = arrayMovies.getJSONObject(i);
                    String id = currentMovie.getString(KEY_ID);

                    String overview = currentMovie.getString(KEY_OVERVIEW);

                    String poster_path = currentMovie.getString(KEY_POSTER_PATH);
                    String backdrop_path = currentMovie.getString(KEY_BACKDROP_PATH);
                   // data.append(id+"\n");

                   Movie movie = new Movie();
                    //(id, overview,poster_path,backdrop_path);
                    movie.setId(id);
                    movie.setOverview(overview);
                    movie.setPoster_path(poster_path);
                    movie.setBackdrop_path(backdrop_path);
                    Log.e("Overview", overview);


                    movieList.add(movie);

                }
                Log.e("JSON FEED", movieList.toString());

                Toast.makeText(getActivity(), movieList.toString(), Toast.LENGTH_SHORT).show();

                // Log.e("TEST_JSON", data.toString());
            }
        }catch (JSONException e){

        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies,container,false);
        pic1 = (ImageView) view.findViewById(R.id.pic);
       // pic2 = (ImageView) view.findViewById(R.id.pic);
       // pic3 = (ImageView) view.findViewById(R.id.pic3);
       // pic4 = (ImageView) view.findViewById(R.id.pic4);

        Glide.with(getActivity()).load("https://image.tmdb.org/t/p/w185//aJn9XeesqsrSLKcHfHP4u5985hn.jpg")
                .into(pic1);
      /*  Glide.with(getActivity()).load("https://image.tmdb.org/t/p/w185//aJn9XeesqsrSLKcHfHP4u5985hn.jpg")
                .into(pic2);

        Glide.with(getActivity()).load("https://image.tmdb.org/t/p/w185//aJn9XeesqsrSLKcHfHP4u5985hn.jpg")
                .into(pic3);

        Glide.with(getActivity()).load("https://image.tmdb.org/t/p/w185//aJn9XeesqsrSLKcHfHP4u5985hn.jpg")
                .into(pic4);
                */




     /*   mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        movieList = new ArrayList<>();
        mAdapter = new MovieAdapter(this.getActivity(), movieList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        prepareAlbums();
    */
        return view;
    }


}
