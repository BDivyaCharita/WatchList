package com.example.divya.watchlist.adapters;

import android.content.Context;
import android.provider.SyncStateContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.divya.watchlist.R;
import com.example.divya.watchlist.fragments.PeopleFragment;
import com.example.divya.watchlist.model.People;
import com.example.divya.watchlist.network.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divya on 12-05-2017.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolderPeople> {

    private Context mContext;
    private ArrayList<People> peopleList= new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private VolleySingleton mVolleySingleton;
    private ImageLoader mImageLoader;
    private LayoutInflater mInflater;



    @Override
    public PeopleAdapter.ViewHolderPeople onCreateViewHolder(ViewGroup parent, int viewType) {
  /*        View itemView = mInflater.inflate(R.layout.custom_card_people,parent,false);
        ViewHolderPeople viewHolder = new ViewHolderPeople(itemView);
        return viewHolder;
        */
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_people, parent, false);
        ViewHolderPeople vh = new ViewHolderPeople(mView);
        return vh;
    }

    public void setPeopleList(ArrayList<People> peopleList){
        this.peopleList=peopleList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolderPeople holder, int position) {
        People person = peopleList.get(position);
        holder.name.setText(person.getName());


        String url = "https://image.tmdb.org/t/p/w185/"+ person.getProfile_path();
       loadImages(url,holder);
        /* Glide.with(getActivity())
        .load(url)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.profile);
        holder.name.setText(person.getName());
        */
    }
    private void loadImages(String urlThumbnail, final ViewHolderPeople holder) {
        if (!urlThumbnail.equals("NA")) {
            mImageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.profile.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public class ViewHolderPeople extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView profile;
        public ViewHolderPeople(View itemView) {
            super(itemView);
            name =(TextView) itemView.findViewById(R.id.name);
            profile = (ImageView) itemView.findViewById(R.id.profile);
        }
    }
    public PeopleAdapter(Context mContext){
        //this.mContext= mContext;
        //this.peopleList = peopleList;
        mLayoutInflater =LayoutInflater.from(mContext);
        mVolleySingleton = VolleySingleton.getInstance();
        mImageLoader = mVolleySingleton.getImageLoader();
    }
}
