package com.example.divya.watchlist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.divya.watchlist.R;
import com.example.divya.watchlist.model.Movie;

import java.util.List;

/**
 * Created by Divya on 08-05-2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context mContext;
    private List<Movie> movieList;


    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.title.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            title =(TextView) itemView.findViewById(R.id.title);
        }
    }
    public MovieAdapter(Context mContext, List<Movie> movieList){
        this.mContext= mContext;
        this.movieList = movieList;
    }
}
