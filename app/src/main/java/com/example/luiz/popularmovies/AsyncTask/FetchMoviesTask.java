package com.example.luiz.popularmovies.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.luiz.popularmovies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by luiz on 15/08/16.
 */
public class FetchMoviesTask extends AsyncTask<String, Void ,ArrayList<Movie>> {

    private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    private ArrayList<Movie> getMoviesDataFromJson(String moviesJsonStr) throws JSONException {

        final String POSTER = "poster_path";
        final String OVERVIEW = "overview";
        final String VOTE_AVERAGE = "vote_average";
        final String TITLE = "original_title";
        final String ID = "id";

        final String PATCH_IMG="http://image.tmdb.org/t/p/w150";

        ArrayList<Movie> movies = new ArrayList<>();
        JSONObject moviesJson = new JSONObject(moviesJsonStr);
        JSONArray moviesArray = moviesJson.getJSONArray("results");

        for(int x=0;x<moviesArray.length();x++){
            Movie movie = new Movie();
            JSONObject m = moviesArray.getJSONObject(x);
            movie.setId(Integer.parseInt(m.getString(ID)));
            movie.setTitle(m.getString(TITLE));
            movie.setOverview(m.getString(OVERVIEW));
            movie.setVoteAverage(m.getString(VOTE_AVERAGE));
            movie.setPoster(PATCH_IMG + m.getString(POSTER));
            movies.add(movie);
        }
        return movies;
    }


    @Override
    protected ArrayList<Movie> doInBackground(String... param) {
        if(param.length == 0){
            return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        String moviesJsonStr = null;

        try{
            final String BASE_URL =
                    "http://api.themoviedb.org/3/movie";
            final String API_KEY =
                    "api_key=2850e9ceafa0bc476d7dc86e55c15a35";

            Uri buildUri = Uri.parse(BASE_URL).buildUpon()
                    .appendPath(param[0])
                    .encodedQuery(API_KEY)
                    .build();

            URL url = new URL(buildUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream == null){
                return null;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null){
                buffer.append(line + "\n");
            }

            if(buffer.length() == 0){
                return null;
            }

            moviesJsonStr = buffer.toString();


        }catch (IOException e){
            Log.e(LOG_TAG, "error", e);
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "error closing stream",e);
                }
            }
        }

        try{
            return getMoviesDataFromJson(moviesJsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG,e.getMessage(),e);
            e.printStackTrace();
        }
        return null;
    }
}