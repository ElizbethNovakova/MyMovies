package com.example.mymovies.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {


    private  static MovieDatabase database;
    private LiveData<List<Movie>> movies;
    private LiveData<List<FavouriteMovie>> favouriteMovies;


    public MainViewModel(@NonNull Application application) {
        super(application);
        database = MovieDatabase.getInstance(getApplication());
        movies = database.movieDao().getAllMovie();
        favouriteMovies = database.movieDao().getAllFavouriteMovie();
    }

    public Movie getMovieById(int id){
        try {
            return new GetMovieTask().execute(id).get();
        }catch (ExecutionException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;

    }

    public FavouriteMovie getFavouriteMovieById(int id){
        try {
            return new GetFavouriteMovieTask().execute(id).get();
        }catch (ExecutionException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;

    }

    public LiveData<List<FavouriteMovie>> getFavouriteMovies() {
        return favouriteMovies;
    }

    public void deleteAllMovies(){
        new DeleteMovieTask().execute();
    }


    public void insertMovies(Movie movie){
        new InsertTask().execute(movie);
    }

    public void deleteMovies(Movie movie){
        new DeleteTask().execute(movie);
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void insertFavouriteMovies(FavouriteMovie movie){
        new InsertFavouriteTask().execute(movie);
    }

    public void deleteFavouriteMovies(FavouriteMovie movie){
        new DeleteFavouriteTask().execute(movie);
    }

    private static class DeleteTask extends AsyncTask<Movie, Void, Void>{
        @Override
        protected Void doInBackground(Movie... movies) {
            if (movies != null && movies.length > 0){
                database.movieDao().deleteMovies(movies[0]);
            }
            return null;
        }
    }

    private static class InsertTask extends AsyncTask<Movie, Void, Void>{
        @Override
        protected Void doInBackground(Movie... movies) {
            if (movies != null && movies.length > 0){
                database.movieDao().insertMovies(movies[0]);
            }
            return null;
        }
    }

    private static class DeleteFavouriteTask extends AsyncTask<FavouriteMovie, Void, Void>{
        @Override
        protected Void doInBackground(FavouriteMovie... movies) {
            if (movies != null && movies.length > 0){
                database.movieDao().deleteFavouriteMovies(movies[0]);
            }
            return null;
        }
    }

    private static class InsertFavouriteTask extends AsyncTask<FavouriteMovie, Void, Void>{
        @Override
        protected Void doInBackground(FavouriteMovie... movies) {
            if (movies != null && movies.length > 0){
                database.movieDao().insertFavouriteMovies(movies[0]);
            }
            return null;
        }
    }

    private static class DeleteMovieTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... integers) {
            database.movieDao().deleteAllMovie();
            return null;
        }
    }

    private static class GetMovieTask extends AsyncTask<Integer, Void, Movie>{
        @Override
        protected Movie doInBackground(Integer... integers) {
            if (integers != null && integers.length > 0){
                return database.movieDao().getMovieById(integers[0]);
            }
            return null;
        }
    }

    private static class GetFavouriteMovieTask extends AsyncTask<Integer, Void, FavouriteMovie>{
        @Override
        protected FavouriteMovie doInBackground(Integer... integers) {
            if (integers != null && integers.length > 0){
                return database.movieDao().getFavouriteMovieById(integers[0]);
            }
            return null;
        }
    }
}
