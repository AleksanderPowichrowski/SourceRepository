package guru.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 12/22/19.
 */
@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public PublisherV2 getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherV2 publisher) {
        this.publisher = publisher;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
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

    public String authorsToString(Set<Author> authors){
        StringBuilder authorString= new StringBuilder();
        for(var author : authors){
            authorString.append("Author{id=").append(author.getId()).append(", firstName='").append(author.getFirstName()).append("'} ");
        }

        return authorString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id != null ? id.equals(book.id) : book.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
