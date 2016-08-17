package com.example.luiz.popularmovies.Adapter;

import android.content.Context;
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
    private ArrayList<String> moviesPoster = new ArrayList<>();

    public ImageAdapter(Context context, ArrayList<Movie> movies){
        this.context = context;
        for (Movie movie : movies){
            this.moviesPoster.add(movie.getPoster());
        }
    }

    @Override
    public int getCount() {
        return this.moviesPoster.size();
    }

    @Override
    public String getItem(int i) {
        return moviesPoster.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView image;
        if(!(moviesPoster.get(position) == null)){
            image = new ImageView(this.context);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(this.context)
                    .load(moviesPoster.get(position))
                    .override(200,500)
                    .fitCenter()
                    .into(image);
        }else {
            image = (ImageView) view;
        }

        return image;
    }
}