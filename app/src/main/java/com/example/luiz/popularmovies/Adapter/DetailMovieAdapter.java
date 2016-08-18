package com.example.luiz.popularmovies.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.luiz.popularmovies.Movie;

import java.util.ArrayList;

/**
 * Created by luiz on 18/08/16.
 */
public class DetailMovieAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movie> movies;

    public DetailMovieAdapter(Context context, ArrayList<Movie> movies){
        this.context = context;
        this.movies = movies;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        return null;
    }
}
