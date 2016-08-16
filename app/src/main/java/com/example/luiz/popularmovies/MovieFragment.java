package com.example.luiz.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.luiz.popularmovies.Adapter.ImageAdapter;
import com.example.luiz.popularmovies.AsyncTask.FetchMoviesTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by luiz on 11/08/16.
 */
public class MovieFragment extends Fragment {

    private ArrayList<Movie> movies;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        try {
            updateMovies();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        View rootView = inflater.inflate(R.layout.movie_fragment,container,false);
        GridView gridPoster = (GridView) rootView.findViewById(R.id.gridPoster);
        gridPoster.setAdapter(new ImageAdapter(getContext(), movies));

        gridPoster.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getContext(),"Filme:" + movies.get(position).getTitle() , Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
