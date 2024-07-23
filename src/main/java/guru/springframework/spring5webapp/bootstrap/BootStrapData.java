package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.domain.PublisherV2;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import guru.springframework.spring5webapp.repositories.PublisherV2Repository;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jt on 12/23/19.
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final PublisherV2Repository publisherV2Repository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, PublisherV2Repository publisherV2Repository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.publisherV2Repository = publisherV2Repository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");


        PublisherV2 pubV22 = new PublisherV2();
        pubV22.setName("DWD");
        pubV22.setAddress("ul. Czestochowska 40/4","Czestochowa","05-444");
        publisherV2Repository.save(pubV22);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(pubV22);
        pubV22.getReleasedBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherV2Repository.save(pubV22);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        PublisherV2 pubV21 = new PublisherV2();

        pubV21.setName("PWD");
        pubV21.setAddress("ul. Powazkowska 4/40","Warsaw","01-555");
        pubV21.getReleasedBooks().add(ddd);
        publisherV2Repository.save(pubV21);

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(pubV21);
        pubV21.getReleasedBooks().add(noEJB);


        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherV2Repository.save(pubV21);

        Publisher pub1 = new Publisher();
        pub1.setName("PWD");
        pub1.setStreetName("ul. Powazkowska 4/40");
        pub1.setCity("Warsaw");
        pub1.setPostalCode("01-555");

        Publisher pub2 = new Publisher();
        pub2.setName("DWD");
        pub2.setStreetName("ul. Czestochowska 40/4");
        pub2.setCity("Czestochowa");
        pub2.setPostalCode("05-444");

        publisherRepository.saveAll(() -> List.of(pub1, pub2).iterator());

//        PublisherV2 pubV21 = new PublisherV2();
//        pubV21.setName("PWD");
//        pubV21.setAddress("ul. Powazkowska 4/40","Warsaw","01-555");
//        pubV21.getReleasedBooks().add(ddd);
//
//        PublisherV2 pubV22 = new PublisherV2();
//        pubV22.setName("DWD");
//        pubV22.setAddress("ul. Czestochowska 40/4","Czestochowa","05-444");
//        pubV22.getReleasedBooks().add(noEJB);




//        publisherV2Repository.saveAll(() -> List.of(pubV21, pubV22).iterator());




        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of publishersV2: " + publisherV2Repository.count());




        showRepositories();
    }

    @Transactional
    public void showRepositories( ) {



        var books = bookRepository.findAll();

        books.forEach(book -> book.getAuthors());

        var authors = authorRepository.findAll();
        authors.forEach(author -> author.getBooks());

        var publishers = publisherV2Repository.findAll();
        publishers.forEach(publisher -> publisher.getReleasedBooks());


        authors.forEach(System.out::println);
        books.forEach(System.out::println);
        publishers.forEach(System.out::println);
    }
}
