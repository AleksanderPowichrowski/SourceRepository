package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.PublisherV2;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherV2Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jt on 12/23/19.
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherV2Repository publisherV2Repository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherV2Repository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherV2Repository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");


        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        PublisherV2 pubV21 = new PublisherV2();
        pubV21.setName("DWD");
        pubV21.setAddress("ul. Czestochowska 40/4", "Czestochowa", "05-444");

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        PublisherV2 pubV22 = new PublisherV2();
        pubV22.setName("PWD");
        pubV22.setAddress("ul. Powazkowska 4/40", "Warsaw", "01-555");
        pubV22.getReleasedBooks().add(ddd);

        bookRepository.saveAll(() -> List.of(ddd, noEJB).iterator());
        authorRepository.saveAll(() -> List.of(rod, eric).iterator());
        publisherV2Repository.saveAll(() -> List.of(pubV21, pubV22).iterator());

        addReferences(eric, ddd, pubV22);
        addReferences(rod, noEJB, pubV21);


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of publishersV2: " + publisherV2Repository.count());

        bookRepository.findAll().forEach(System.out::println);
        authorRepository.findAll().forEach(System.out::println);
        publisherV2Repository.findAll().forEach(System.out::println);
    }

    private void addReferences(Author author, Book book, PublisherV2 publisherV2) {
        author.getBooks().add(book);
        book.getAuthors().add(author);
        book.setPublisher(publisherV2);
        publisherV2.getReleasedBooks().add(book);

        authorRepository.save(author);
        bookRepository.save(book);
        publisherV2Repository.save(publisherV2);
    }
}
