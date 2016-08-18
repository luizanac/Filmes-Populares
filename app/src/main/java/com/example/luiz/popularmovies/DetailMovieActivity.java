package com.example.luiz.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by luiz on 17/08/16.
 */
public class DetailMovieActivity extends AppCompatActivity {

    private ImageView ivMovieDb;
    private ImageView ivPoster;
    private TextView tvTitle;
    private TextView tvOverview;
    private TextView tvVoteAverage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        ivMovieDb = (ImageView) findViewById(R.id.ivMovieDb);
        ivPoster = (ImageView) findViewById(R.id.ivPoster);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        tvVoteAverage = (TextView) findViewById(R.id.tvVoteAverage);

        //Pega um objeto da classe movie que implementa serializable.
        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra("movie");

        int resource = R.drawable.movie_logo;
        Glide.with(this)
                .load(resource)
                .override(100,100)
                .into(ivMovieDb);

        Glide.with(this)
                .load(movie.getPoster())
                .override(300,600)
                .fitCenter()
                .into(ivPoster);
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvVoteAverage.setText(movie.getVoteAverage());

    }
}
