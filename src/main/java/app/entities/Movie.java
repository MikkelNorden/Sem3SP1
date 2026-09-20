package app.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String originalTitle;
    //private List<Integer> genreIds = new ArrayList<>();
    private String releaseDate;
    private double popularity;
    private double voteAverage;
    private int voteCount;

    public Movie(String title, String originalTitle, List<Integer> genreIds, String releaseDate, double popularity, double voteAverage, int voteCount) {
        this.title = title;
        this.originalTitle = originalTitle;
        //this.genreIds = genreIds;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }
}