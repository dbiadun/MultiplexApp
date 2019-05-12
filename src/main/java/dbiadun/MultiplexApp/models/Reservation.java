package dbiadun.MultiplexApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    @NotNull
    @Size(max = 64)
    private String firstName;

    @NotBlank
    @NotNull
    @Size(max = 64)
    private String lastName;

    public Reservation() {}

    public Reservation(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
