package com.example.luiz.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.luiz.popularmovies.Adapter.ImageAdapter;
import com.example.luiz.popularmovies.AsyncTask.FetchMoviesTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by luiz on 11/08/16.
 */
public class MovieFragment extends Fragment {

    private ArrayList<Movie> movies;
    private GridView gridPoster;
    private View rootView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            updateMovies();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateMovies() throws ExecutionException, InterruptedException {
        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
        fetchMoviesTask.execute("popular");
        this.movies= fetchMoviesTask.get();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        try {
            updateMovies();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                rootView= inflater.inflate(R.layout.movie_fragment,container,false);
                gridPoster = (GridView) rootView.findViewById(R.id.gridPoster);
                gridPoster.setAdapter(new ImageAdapter(getContext(), movies));
            }
        };
        runnable.run();

        gridPoster.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getContext(), DetailMovieActivity.class);
                intent.putExtra("movie", movies.get(position));
                startActivity(intent);
            }
        });

        return rootView;
    }
}
