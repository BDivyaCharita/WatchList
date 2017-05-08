package com.example.divya.watchlist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.divya.watchlist.R;
import com.example.divya.watchlist.adapters.MovieAdapter;
import com.example.divya.watchlist.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divya on 07-05-2017.
 */

public class MovieFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Movie> movieList;


    private int mPage;

    public static MovieFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        movieList = new ArrayList<>();
        mAdapter = new MovieAdapter(this.getActivity(), movieList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        prepareAlbums();
        return view;
    }

    private void prepareAlbums() {
        Movie m = new Movie("1");
        movieList.add(m);
        m = new Movie("2");
        movieList.add(m);
        m = new Movie("3");
        movieList.add(m);
        m = new Movie("4");
        movieList.add(m);
        m = new Movie("5");
        movieList.add(m);
    }
}
