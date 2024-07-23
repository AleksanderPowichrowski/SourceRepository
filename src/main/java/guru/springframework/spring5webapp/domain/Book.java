package guru.springframework.spring5webapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by jt on 12/22/19.
 */
@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String isbn;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private PublisherV2 publisher;

    public Book() {
    }

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors=" + authorsToString(authors) + '\'' +
                ", Publisher=" + (publisher != null ? publisherSimpleToString() : "null") +
                '}';
    }

    private String publisherSimpleToString() {
        return "PublisherV2{id=" + publisher.getId() +
                ", name='" + publisher.getName() + '\'' +
                ", address='" + publisher.getAddress().toString() + "'}";
    }

    public String authorsToString(Set<Author> authors) {
        StringBuilder authorString = new StringBuilder();
        for (var author : authors) {
            authorString.append("Author{id=").append(author.getId()).append(", firstName='").append(author.getFirstName()).append("'} ");
        }
        return authorString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn);
    }
}
