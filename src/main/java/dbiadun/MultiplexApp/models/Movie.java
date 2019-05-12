package dbiadun.MultiplexApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Movies")
public class Movie {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    @NotNull
    @Size(max = 128)
    private String title;

    public Movie() {}

    public Movie(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
