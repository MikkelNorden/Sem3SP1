package app.config;

//import app.entities.Study;
import app.entities.Genre;
import app.entities.Movie;
import org.hibernate.cfg.Configuration;

final class EntityRegistry {

    private EntityRegistry() {}

    static void registerEntities(Configuration configuration) {
        //configuration.addAnnotatedClass(Study.class);
        configuration.addAnnotatedClass(Movie.class);
        configuration.addAnnotatedClass(Genre.class);
        // TODO: Add more entities here...
    }
}