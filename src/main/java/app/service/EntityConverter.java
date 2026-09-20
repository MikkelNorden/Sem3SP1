package app.service;

import app.dto.GenreDTO;
import app.dto.MovieDTO;
import app.entities.Genre;
import app.entities.Movie;

import java.util.ArrayList;
import java.util.List;

public class EntityConverter {

    public static List<Integer> convertArrayToList(Integer[] intArray) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < intArray.length; i++) {
            integerList.add(intArray[i]);
        }
        return integerList;
    }

    public static Movie convertFromMovieDTO(MovieDTO movieDTO) {
        String title = movieDTO.getTitle();
        String originalTitle = movieDTO.getOriginalTitle();
        List<Integer> genreIds = convertArrayToList(movieDTO.getGenreIds());
        String releaseDate = movieDTO.getReleaseDate();
        double popularity = movieDTO.getPopularity();
        double voteAverage = movieDTO.getVoteAverage();
        int voteCount = movieDTO.getVoteCount();
        return new Movie(title, originalTitle, genreIds, releaseDate, popularity, voteAverage, voteCount);
    }

    public static Genre convertFromGenreDTO(GenreDTO genreDTO) {
        String name = genreDTO.getName();
        return new Genre(name);
    }
}