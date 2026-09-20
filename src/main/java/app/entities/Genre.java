package app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Genre(String name) {
        this.name = name;
    }
}