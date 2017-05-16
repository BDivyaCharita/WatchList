package com.example.divya.watchlist.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.divya.watchlist.R;
import com.example.divya.watchlist.adapters.PeopleAdapter;
import com.example.divya.watchlist.extras.VolleyJSONRequests;
import com.example.divya.watchlist.model.People;
import com.example.divya.watchlist.network.NetworkUtils;
import com.example.divya.watchlist.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.divya.watchlist.extras.Keys.KeysPeople.KEY_ID;
import static com.example.divya.watchlist.extras.Keys.KeysPeople.KEY_NAME;
import static com.example.divya.watchlist.extras.Keys.KeysPeople.KEY_POPULARITY;
import static com.example.divya.watchlist.extras.Keys.KeysPeople.KEY_PROFILE_PATH;
import static com.example.divya.watchlist.extras.Keys.KeysPeople.KEY_RESULTS;

public class PeopleFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PAGE = "ARG_PAGE";

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    private RecyclerView listPeople;
    private  ArrayList<People> mListPeople = new ArrayList<>();


    private PeopleAdapter peopleAdapter;
    private RecyclerView mRecyclerView;
    private TextView mTextError;
    private RecyclerView.LayoutManager layoutManager;

    private int mPage;

    public PeopleFragment() {
        // Required empty public constructor
    }

    public static PeopleFragment newInstance(int page) {
        PeopleFragment fragment = new PeopleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
        }

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    private void sendJSONRequest(){
        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.GET, NetworkUtils.buildPeopleUrl().toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.e("Response", response.toString());
                //Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                mListPeople =parsePeopleJSONResponse(response);
                peopleAdapter.setPeopleList(mListPeople);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    private ArrayList<People> parsePeopleJSONResponse(JSONObject response) {
    //private void parsePeopleJSONResponse(JSONObject response) {

        ArrayList<People> peopleList = new ArrayList<>();
        if (response != null || response.length() > 0) {

            try {
                if (response.has(KEY_RESULTS)) {
                    JSONArray arrayPeople = response.getJSONArray(KEY_RESULTS);
                    for (int i = 0; i < arrayPeople.length(); i++) {
                        JSONObject currentPerson = arrayPeople.getJSONObject(i);

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
                    Log.e("PEOPLE: JSON FEED", peopleList.toString());

                }
            } catch (JSONException e) {

            }
        }
        return peopleList;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_people, container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.listPeople);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        peopleAdapter = new PeopleAdapter(getActivity());
        mRecyclerView.setAdapter(peopleAdapter);

        sendJSONRequest();
        peopleAdapter.setPeopleList(mListPeople);

        return view;
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
