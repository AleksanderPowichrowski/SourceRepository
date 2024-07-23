package guru.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Publisher implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    private String streetName;
    private String city;
    private String postalCode;

    @OneToMany
    @JoinColumn(name="publisher_id")
    private final Set<Book> releasedBooks = new HashSet<>();

    public Publisher() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

       public Set<Book> getReleasedBooks() {
        return releasedBooks;
    }

    public void setName(String name) {
        this.name = name;
    }
}
