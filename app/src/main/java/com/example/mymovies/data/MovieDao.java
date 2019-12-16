package com.example.mymovies.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getAllMovie();

    @Query("SELECT *FROM movies WHERE id == :movieId")
    Movie getMovieById(int movieId);

    @Query("DELETE FROM movies")
    void deleteAllMovie();

    @Insert
    void insertMovies(Movie movie);

    @Delete
    void deleteMovies(Movie movie);

}
