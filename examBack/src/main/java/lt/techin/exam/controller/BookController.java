package lt.techin.exam.controller;

import lombok.extern.slf4j.Slf4j;
import lt.techin.exam.dto.BookDTO;
import lt.techin.exam.entity.Book;
import lt.techin.exam.request.book.BookListRequest;
import lt.techin.exam.response.book.BookListResponse;
import lt.techin.exam.response.book.BookResponse;
import lt.techin.exam.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> addBook(@RequestBody BookDTO dto) {
        log.info("request to add book, name {}, isbn {}", dto.getBookName(), dto.getIsbn());
        final BookResponse response = bookService.addBook(dto);
        final Book book = response.getBook();
        final HttpStatus status = response.getStatus();
        return new ResponseEntity<>(book, status);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR', 'ROLE_USER')")
    public ResponseEntity<?> retrieveAllBooks(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String nameContains,
            @RequestParam(required = false) String categoryContains,
            @RequestParam(defaultValue = "bookName") String sortBy,
            @RequestParam(defaultValue = "true") boolean sortAsc
    ) {
        final BookListRequest request = new BookListRequest(
                pageNumber, pageSize, nameContains, categoryContains, sortBy, sortAsc
        );
        log.info("request to retrieve list of books");
        final BookListResponse response = bookService.retrieveAllBooks(request);
        final Page<Book> page = response.getBooks();
        final HttpStatus status = response.getStatus();
        return new ResponseEntity<>(page, status);
    }

    @GetMapping(path = "/{isbn}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR', 'ROLE_USER')")
    public ResponseEntity<?> retrieveBookByISBN(@PathVariable String isbn) {
        log.info("request to retrieve book with isbn {}", isbn);
        final BookResponse response = bookService.retrieveBookByISBN(isbn);
        final Book book = response.getBook();
        final HttpStatus status = response.getStatus();
        return new ResponseEntity<>(book, status);
    }

    @PutMapping(path = "/{isbn}")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> updateBookByISBN(@PathVariable String isbn, @RequestBody BookDTO dto) {
        log.info("request to update book with isbn {}", isbn);
        final BookResponse response = bookService.updateBookByISBN(isbn, dto);
        final Book book = response.getBook();
        final HttpStatus status = response.getStatus();
        return new ResponseEntity<>(book, status);
    }

    @DeleteMapping(path = "/{isbn}")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> deleteBookByISBN(@PathVariable String isbn) {
        log.info("request to delete book with isbn {}", isbn);
        final HttpStatus status = bookService.deleteBookByISBN(isbn);
        return new ResponseEntity<>(status);
    }
}
