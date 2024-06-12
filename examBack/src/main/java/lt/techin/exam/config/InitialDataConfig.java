package lt.techin.exam.config;

import lt.techin.exam.entity.Book;
import lt.techin.exam.entity.BookCategory;
import lt.techin.exam.entity.User;
import lt.techin.exam.repository.BookCategoryRepository;
import lt.techin.exam.repository.BookRepository;
import lt.techin.exam.repository.UserRepository;
import lt.techin.exam.utilities.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class InitialDataConfig {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookCategoryRepository bookCategoryRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialDataConfig(
            UserRepository userRepository,
            BookRepository bookRepository,
            BookCategoryRepository bookCategoryRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookCategoryRepository = bookCategoryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initialDataRunner() {
        return runner -> {
            addUsers();
            addBooks();
            addBookCategories();
            addCategoriesToBooks();
        };
    }

    private void addCategoriesToBooks() {
        final Optional<Book> book01 = bookRepository.findByIsbnIgnoreCase("0-7475-3849-2");
        final Optional<Book> book02 = bookRepository.findByIsbnIgnoreCase("0-7475-9105-9");
        final Optional<Book> book03 = bookRepository.findByIsbnIgnoreCase("0-7475-5079-4");
        final Optional<Book> book04 = bookRepository.findByIsbnIgnoreCase("0-7475-8108-8");
        final Optional<Book> book05 = bookRepository.findByIsbnIgnoreCase("0-7475-5100-6");
        final Optional<Book> book06 = bookRepository.findByIsbnIgnoreCase("978-0-7475-3269-9");
        final Optional<Book> book07 = bookRepository.findByIsbnIgnoreCase("0-7475-4215-5");
        final Optional<Book> book08 = bookRepository.findByIsbnIgnoreCase("076150359");
        final Optional<Book> book09 = bookRepository.findByIsbnIgnoreCase("978-0-393-97283-2");
        final Optional<Book> book10 = bookRepository.findByIsbnIgnoreCase("0-575-01587-X");
        final Optional<Book> book11 = bookRepository.findByIsbnIgnoreCase("0-531-15093-3");
        final Optional<Book> book12 = bookRepository.findByIsbnIgnoreCase("0-7653-4161-1");


        final Optional<BookCategory> fantasyOp = bookCategoryRepository.findByCategoryNameIgnoreCase("fantasy");
        BookCategory fantasy = null;
        if (fantasyOp.isPresent()) {
            fantasy = fantasyOp.get();
        }

        final Optional<BookCategory> horrorOp = bookCategoryRepository.findByCategoryNameIgnoreCase("horror");
        BookCategory horror = null;
        if (horrorOp.isPresent()) {
            horror = horrorOp.get();
        }

        final Optional<BookCategory> comedyOp = bookCategoryRepository.findByCategoryNameIgnoreCase("comedy");
        BookCategory comedy = null;
        if (comedyOp.isPresent()) {
            comedy = comedyOp.get();
        }

        final Optional<BookCategory> sliceOfLifeOp = bookCategoryRepository.findByCategoryNameIgnoreCase("slice of life");

        final Optional<BookCategory> dramaOp = bookCategoryRepository.findByCategoryNameIgnoreCase("drama");
        BookCategory drama = null;
        if (dramaOp.isPresent()) {
            drama = dramaOp.get();
        }


        final Optional<BookCategory> comingOfAgeOp = bookCategoryRepository.findByCategoryNameIgnoreCase("coming of age");
        BookCategory comingOfAge = null;
        if (comingOfAgeOp.isPresent()) {
            comingOfAge = comingOfAgeOp.get();
        }

        final Optional<BookCategory> scienceFictionOp = bookCategoryRepository.findByCategoryNameIgnoreCase("science fiction");
        BookCategory sciFi = null;
        if (scienceFictionOp.isPresent()) {
            sciFi = scienceFictionOp.get();
        }

        final Optional<BookCategory> historicalOp = bookCategoryRepository.findByCategoryNameIgnoreCase("historical");
        BookCategory historical = null;
        if (historicalOp.isPresent()) {
            historical = historicalOp.get();
        }

        Book bookToUpdate;

        if (book01.isPresent()) {
            bookToUpdate = book01.get();
            bookToUpdate.addCategory(fantasy);
            bookRepository.save(bookToUpdate);
        }

        if (book02.isPresent()) {
            bookToUpdate = book02.get();
            bookToUpdate.addCategory(fantasy);
            bookRepository.save(bookToUpdate);
        }

        if (book03.isPresent()) {
            bookToUpdate = book03.get();
            bookToUpdate.addCategory(fantasy);
            bookToUpdate.addCategory(comingOfAge);
            bookRepository.save(bookToUpdate);
        }

        if (book04.isPresent()) {
            bookToUpdate = book04.get();
            bookToUpdate.addCategory(fantasy);
            bookToUpdate.addCategory(comingOfAge);
            bookRepository.save(bookToUpdate);
        }

        if (book05.isPresent()) {
            bookToUpdate = book05.get();
            bookToUpdate.addCategory(fantasy);
            bookToUpdate.addCategory(comingOfAge);
            bookRepository.save(bookToUpdate);
        }

        if (book06.isPresent()) {
            bookToUpdate = book06.get();
            bookToUpdate.addCategory(fantasy);
            bookRepository.save(bookToUpdate);
        }

        if (book07.isPresent()) {
            bookToUpdate = book07.get();
            bookToUpdate.addCategory(fantasy);
            bookRepository.save(bookToUpdate);
        }

        if (book08.isPresent()) {
            bookToUpdate = book08.get();
            bookToUpdate.addCategory(horror);
            bookToUpdate.addCategory(sciFi);
            bookRepository.save(bookToUpdate);
        }

        if (book09.isPresent()) {
            bookToUpdate = book09.get();
            bookToUpdate.addCategory(drama);
            bookToUpdate.addCategory(historical);
            bookRepository.save(bookToUpdate);
        }

        if (book10.isPresent()) {
            bookToUpdate = book10.get();
            bookToUpdate.addCategory(sciFi);
            bookRepository.save(bookToUpdate);
        }

        if (book11.isPresent()) {
            bookToUpdate = book11.get();
            bookToUpdate.addCategory(historical);
            bookToUpdate.addCategory(drama);
            bookRepository.save(bookToUpdate);
        }

        if (book12.isPresent()) {
            bookToUpdate = book12.get();
            bookToUpdate.addCategory(comedy);
            bookRepository.save(bookToUpdate);
        }
    }

    public void addUsers() {
        final User user00 = User.builder()
                .firstName("King")
                .lastName("Bradley")
                .email("king@mail.com")
                .userRole(UserRole.ADMINISTRATOR)
                .password(passwordEncoder.encode("passw"))
                .build();
        userRepository.save(user00);

        final User user01 = User.builder()
                .firstName("Roy")
                .lastName("Mustang")
                .email("rmust@mail.com")
                .userRole(UserRole.USER)
                .password(passwordEncoder.encode("passw"))
                .build();
        userRepository.save(user01);

        final User user02 = User.builder()
                .firstName("Riza")
                .lastName("Hawkeye")
                .email("riza@mail.com")
                .userRole(UserRole.USER)
                .password(passwordEncoder.encode("passw"))
                .build();
        userRepository.save(user02);

        final User user03 = User.builder()
                .firstName("Jean")
                .lastName("Havoc")
                .email("jean@mail.com")
                .userRole(UserRole.USER)
                .password(passwordEncoder.encode("passw"))
                .build();
        userRepository.save(user03);
    }

    public void addBooks() {
        final Book book01 = new Book(
                "harry potter and the philosopher's stone",
                "about stone",
                "978-0-7475-3269-9",
                223
        );
        bookRepository.save(book01);

        final Book book02 = new Book(
                "harry potter and the chamber of secrets",
                "about big snake",
                "0-7475-3849-2",
                251
        );
        bookRepository.save(book02);

        final Book book03 = new Book(
                "harry potter and the prisoner of azkaban",
                "about spooky kissers",
                "0-7475-4215-5",
                317
        );
        bookRepository.save(book03);

        final Book book04 = new Book(
                "harry potter and the goblet of fire",
                "about goblet of fire, duh",
                "0-7475-5079-4",
                636
        );
        bookRepository.save(book04);

        final Book book05 = new Book(
                "harry potter and the order of the phoenix",
                "about nymphadora tonks",
                "0-7475-5100-6",
                766
        );
        bookRepository.save(book05);

        final Book book06 = new Book(
                "harry potter and the half blooded prince",
                "sectumsempra!",
                "0-7475-8108-8",
                607
        );
        bookRepository.save(book06);

        final Book book07 = new Book(
                "harry potter and the deathly hallows",
                "Neville mah man!",
                "0-7475-9105-9",
                607
        );
        bookRepository.save(book07);

        final Book book08 = new Book(
                "the three musketeers",
                "les trois mousquetaires",
                "0-531-15093-3",
                700
        );
        bookRepository.save(book08);

        final Book book09 = new Book(
                "moby-dick",
                "the whale",
                "978-0-393-97283-2",
                635
        );
        bookRepository.save(book09);

        final Book book10 = new Book(
                "rendezvous with rama",
                "cylinder",
                "0-575-01587-X",
                256
        );
        bookRepository.save(book10);

        final Book book11 = new Book(
                "i have no mouth, and i must scream",
                "cogito ergo sum",
                "076150359",
                13
        );
        bookRepository.save(book11);

        final Book book12 = new Book(
                "three men in a boat (to say nothing of the dog)",
                "j",
                "0-7653-4161-1",
                232
        );
        bookRepository.save(book12);
    }

    public void addBookCategories() {
        final var bookCategory01 = new BookCategory("fantasy");
        bookCategoryRepository.save(bookCategory01);

        final var bookCategory02 = new BookCategory("horror");
        bookCategoryRepository.save(bookCategory02);

        final var bookCategory03 = new BookCategory("comedy");
        bookCategoryRepository.save(bookCategory03);

        final var bookCategory04 = new BookCategory("slice of life");
        bookCategoryRepository.save(bookCategory04);

        final var bookCategory05 = new BookCategory("drama");
        bookCategoryRepository.save(bookCategory05);

        final var bookCategory06 = new BookCategory("coming of age");
        bookCategoryRepository.save(bookCategory06);

        final var bookCategory07 = new BookCategory("science fiction");
        bookCategoryRepository.save(bookCategory07);

        final var bookCategory08 = new BookCategory("historical");
        bookCategoryRepository.save(bookCategory08);
    }
}
