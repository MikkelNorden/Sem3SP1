package app;

import app.config.HibernateConfig;
import app.dto.GenreCollectionDTO;
import app.dto.GenreDTO;
import app.dto.MovieDTO;
import app.dto.MoviePageDTO;
import app.entities.Genre;
import app.entities.Movie;
import app.service.EntityConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        APIReader apiReader = new APIReader();

        //creates a list with a MoviePageDTO for each page
        List<MoviePageDTO> moviePageDTOList = new ArrayList<>();

        int page_amount = apiReader.countPagesRelevant();

        for(int i = 1 ; i <= page_amount ; i++) {
            String json = apiReader.readAPI(apiReader.getAppropriateMovieURLWithSpecificPage(i));
            MoviePageDTO moviePageDTO = apiReader.convertFromJson(json);
            moviePageDTOList.add(moviePageDTO);
        }

        List<MovieDTO> movieDTOList = new ArrayList<>();

        int i = 0;
        for(MoviePageDTO moviePageDTO : moviePageDTOList) {
            System.out.println("Page #" + moviePageDTO.page());
            for (MovieDTO movieDTO : moviePageDTO.results()){
                i++;
                movieDTOList.add(movieDTO);
                System.out.println("Movie #" + i + ": " + movieDTO);
            }
        }

        List<GenreDTO> genreDTOList = new ArrayList<>();

        String json = apiReader.readAPI(apiReader.getGENRE_URL());
        GenreCollectionDTO genreCollectionDTO = apiReader.convertFromGenreJson(json);
        for(GenreDTO genreDTO : genreCollectionDTO.getGenres()) {
            genreDTOList.add(genreDTO);
        }

        List<Movie> movies = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();

        for(MovieDTO movieDTO : movieDTOList) {
            Movie movie = EntityConverter.convertFromMovieDTO(movieDTO);
            movies.add(movie);
        }

        for(GenreDTO genreDTO : genreDTOList) {
            Genre genre = EntityConverter.convertFromGenreDTO(genreDTO);
            genres.add(genre);
        }

        /*
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        for (Movie movie : movie) {
            em.persist(movie);
        }
        for (Genre genre : genres) {
            em.persist(genre);
        }
        em.getTransaction().commit();

        em.close();
        emf.close();
        */
    }
}