package com.example.luiz.popularmovies.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.luiz.popularmovies.Movie;

import java.util.ArrayList;

/**
 * Created by luiz on 15/08/16.
 */
public   class ImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> movies = new ArrayList<>();

    public ImageAdapter(Context context, ArrayList<Movie> movies){
        this.context = context;
        for (Movie movie : movies){
            this.movies.add(movie.getPoster());
        }
        Log.v("TESTE IMAGE","ADPTER" + this.movies.get(1));
    }

    @Override
    public int getCount() {
        return this.movies.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView image;
        if(!(movies.get(position) == null)){
            image = new ImageView(this.context);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(this.context)
                    .load(movies.get(position))
                    .override(200,500)
                    .fitCenter()
                    .into(image);
        }else {
            image = (ImageView) view;
        }

        return image;
    }
}