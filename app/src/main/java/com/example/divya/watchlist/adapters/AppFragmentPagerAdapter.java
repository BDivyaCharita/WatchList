package com.example.divya.watchlist.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.divya.watchlist.fragments.MovieFragment;
import com.example.divya.watchlist.fragments.PeopleFragment;
import com.example.divya.watchlist.fragments.SearchFragment;
import com.example.divya.watchlist.fragments.TvShowFragment;

/**
 * Created by Divya on 07-05-2017.
 */

public class AppFragmentPagerAdapter extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Movies","TV Shows","People"};
    private Context context;

    public static final int MOVIES = 0;
  //  public static final int SEARCH = 0;
    public static final int TV_SHOWS = 1;
    public static final int PEOPLE = 2;

    public AppFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

       Fragment fragment = null;

       switch (position){
            case MOVIES:
                fragment = MovieFragment.newInstance(MOVIES);
                break;
            case TV_SHOWS:
                fragment = TvShowFragment.newInstance(TV_SHOWS);
                break;
            case PEOPLE:
                fragment = PeopleFragment.newInstance(PEOPLE);
                break;
        }

        //fragment = MovieFragment.newInstance(position+1);

        return fragment;

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
