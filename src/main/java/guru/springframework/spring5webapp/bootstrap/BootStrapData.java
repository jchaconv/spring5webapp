package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {


    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher alfaguara = new Publisher();
        alfaguara.setName("Editorial Alfaguara");
        alfaguara.setCity("Madrid");
        alfaguara.setState("ES");

        publisherRepository.save(alfaguara);

        System.out.println("Publisher count: " + publisherRepository.count());

        Author mario = new Author("Mario", "Vargas Llosa");
        Book firstBook = new Book("La ciudad y los perros", "0-7645-2641-3445");

        mario.getBooks().add(firstBook);
        firstBook.getAuthors().add(mario);

        firstBook.setPublisher(alfaguara);
        alfaguara.getBooks().add(firstBook);

        authorRepository.save(mario);
        bookRepository.save(firstBook);
        publisherRepository.save(alfaguara);


        Author cesar = new Author("César", "Vallejo");
        Book secondBook = new Book("Los heraldos negros", "0-7645-2641-3446");

        cesar.getBooks().add(secondBook);
        secondBook.getAuthors().add(cesar);

        secondBook.setPublisher(alfaguara);
        alfaguara.getBooks().add(secondBook);

        authorRepository.save(cesar);
        bookRepository.save(secondBook);
        publisherRepository.save(alfaguara);

        System.out.println("=== Started in Bootstrap ===");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Publisher number of books: " + alfaguara.getBooks().size());


    }
}
