package com.example.mymovies.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

@Entity(tableName = "favourite_movies")
public class FavouriteMovie extends Movie {
    public FavouriteMovie(int uniqueId, int id, int voteCount, String title, String originalTitle,
                          String overview, String posterPath, String bigPosterPath,
                          String backDropPath, double voteAverage, String releaseDate) {
        super( uniqueId, id, voteCount, title, originalTitle, overview, posterPath, bigPosterPath, backDropPath, voteAverage, releaseDate);
    }

    @Ignore
    public FavouriteMovie(Movie movie){
        super( movie.getUniqueId(),  movie.getId(), movie.getVoteCount(), movie.getTitle(), movie.getOriginalTitle(),
                movie.getOverview(), movie.getPosterPath(), movie.getBigPosterPath(), movie.getBackDropPath(),
                movie.getVoteAverage(), movie.getReleaseDate());
    }
}
