package app;

import app.dto.GenreCollectionDTO;
import app.dto.MovieDTO;
import app.dto.MoviePageDTO;
import app.utility.Calculator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIReader {
    @Getter
    private final String MOVIE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + System.getenv("api_key");
    @Getter
    private final String GENRE_URL = "https://api.themoviedb.org/3/genre/movie/list?language=en&api_key=" + System.getenv("api_key");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String readAPI(String url) {
        try {
            // Create an HttpClient instance
            HttpClient client = HttpClient.newHttpClient();

            // Create a request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the status code and print the response
            if (response.statusCode() != 200) {
                throw new RuntimeException("GET request failed. Status code: " + response.statusCode());
            }
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public MoviePageDTO convertFromJson(String json) {
        try {
            MoviePageDTO moviePageDTOs = objectMapper.readValue(json, MoviePageDTO.class);
            return moviePageDTOs;
        } catch(JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public GenreCollectionDTO convertFromGenreJson(String json) {
        try {
            GenreCollectionDTO genreCollectionDTOs = objectMapper.readValue(json, GenreCollectionDTO.class);
            return genreCollectionDTOs;
        } catch(JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMovieURLWithSpecificPage(int page) {
        return "https://api.themoviedb.org/3/movie/popular?page=" + page + "&api_key=" + System.getenv("api_key");
    }

    public String getAppropriateMovieURLWithSpecificPage(int page) {
        return "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=" + page + "&release_date.gte=2021-09-17&sort_by=popularity.desc&with_origin_country=DK&with_original_language=da&api_key=" + System.getenv("api_key");
    }

    public String getMovieURLWithSpecificPageAndTime(int page, int year, String firstMonth, String lastMonth) {
        String lastDay = Calculator.findLastDayOfMonth(lastMonth, year);
        String firstDate = year + "-" + firstMonth + "-01";
        String lastDate = year + "-" + lastMonth + "-" + lastDay;
        return "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=" + page + "&primary_release_date.gte=" + firstDate + "&primary_release_date.lte=" + lastDate + "&sort_by=popularity.desc&api_key=" + System.getenv("api_key");
    }

    public int countPages(int year, String firstMonth, String lastMonth) {
        String firstJson = readAPI(getMovieURLWithSpecificPageAndTime(1, year, firstMonth, lastMonth));
        MoviePageDTO firstMoviePageDTO = convertFromJson(firstJson);
        return firstMoviePageDTO.total_pages();
    }

    public int countPagesRelevant() {
        String firstJson = readAPI(getAppropriateMovieURLWithSpecificPage(1));
        MoviePageDTO firstMoviePageDTO = convertFromJson(firstJson);
        return firstMoviePageDTO.total_pages();
    }
}