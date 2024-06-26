package lt.techin.exam.service;

import lombok.extern.slf4j.Slf4j;
import lt.techin.exam.dto.BookDTO;
import lt.techin.exam.entity.Book;
import lt.techin.exam.entity.BookCategory;
import lt.techin.exam.repository.BookCategoryRepository;
import lt.techin.exam.repository.BookRepository;
import lt.techin.exam.request.book.BookListRequest;
import lt.techin.exam.response.book.BookListResponse;
import lt.techin.exam.response.book.BookResponse;
import lt.techin.exam.utilities.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final BookCategoryRepository bookCategoryRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BookCategoryRepository bookCategoryRepository) {
        this.bookRepository = bookRepository;
        this.bookCategoryRepository = bookCategoryRepository;
    }

    @Transactional
    public BookResponse addBook(BookDTO dto) {
        final Optional<Book> book = bookRepository
                .findByIsbnIgnoreCase(dto.getIsbn());

        if (book.isEmpty()) {
            final Book bookToAdd = BookMapper.DTOToBook(dto);

            if (dto.getCategories() != null) {
                final List<BookCategory> categories = new ArrayList<>();
                for (BookCategory c : dto.getCategories()) {
                    final Optional<BookCategory> categoryToAdd = bookCategoryRepository
                            .findByCategoryNameIgnoreCase(c.getCategoryName());
                    categoryToAdd.ifPresent(bookToAdd::addCategory);
                }
                bookToAdd.setCategories(categories);
            }

            final Book savedBook = bookRepository.save(bookToAdd);
            log.info("added book, with id {}", savedBook.getId());
            return new BookResponse(savedBook, HttpStatus.CREATED);
        }

        final Book bookInRepository = book.get();
        log.info("book already in repository, id {}", bookInRepository.getId());
        return new BookResponse(bookInRepository, HttpStatus.OK);
    }

    public BookListResponse retrieveAllBooks(BookListRequest request) {
        final Sort.Direction direction = request.isSortAsc() ? Sort.Direction.ASC : Sort.Direction.DESC;
        final Sort sort = Sort.by(direction, request.getSortBy());

        int pageNumber = request.getPageNumber();
        final int pageSize = request.getPageSize();
        final String nameContains = request.getNameContains();
        final String categoryContains = request.getCategoryContains();

        final Pageable pageable = PageRequest.of(--pageNumber, pageSize, sort);
        if ((nameContains == null || nameContains.isEmpty()) && (categoryContains == null || categoryContains.isEmpty())) {
            log.info("retrieving all books in repository");
            Page<Book> page = bookRepository.findAll(pageable);
            return new BookListResponse(page, HttpStatus.OK);
        }

        log.info("retrieving containing {}", nameContains);
        Page<Book> page = bookRepository
                .findByBookNameContainingIgnoreCaseAndCategoriesCategoryNameContainingIgnoreCase(
                        pageable,
                        request.getNameContains(),
                        request.getCategoryContains());
        return new BookListResponse(page, HttpStatus.OK);
    }

    public BookResponse retrieveBookByName(String bookName) {
        final Optional<Book> book = bookRepository.findByIsbnIgnoreCase(bookName);

        if (book.isPresent()) {
            final Book bookToRetrieve = book.get();
            log.info("retrieving book with name {}", bookToRetrieve.getIsbn());
            return new BookResponse(bookToRetrieve, HttpStatus.OK);
        }

        log.info("book with name {} was not found", bookName);
        return new BookResponse(new Book(), HttpStatus.NOT_FOUND);
    }

    public BookResponse retrieveBookByISBN(String isbn) {
        final Optional<Book> book = bookRepository.findByIsbnIgnoreCase(isbn);

        if (book.isPresent()) {
            final Book bookToRetrieve = book.get();
            log.info("retrieving book with isbn {}", bookToRetrieve.getIsbn());
            return new BookResponse(bookToRetrieve, HttpStatus.OK);
        }

        log.info("book with isbn {} was not found", isbn);
        return new BookResponse(new Book(), HttpStatus.NOT_FOUND);
    }

    public BookResponse updateBookByISBN(String isbn, BookDTO dto) {
        final Optional<Book> book = bookRepository.findByIsbnIgnoreCase(isbn);

        if (book.isPresent()) {
            final Book bookToUpdate = book.get();
            bookToUpdate.setBookName(dto.getBookName());
            bookToUpdate.setBookDescription(dto.getBookDescription());
            bookToUpdate.setIsbn(dto.getIsbn());
            bookToUpdate.setPageCount(dto.getPageCount());
            bookRepository.save(bookToUpdate);
            log.info("book with isbn {} has been updated", bookToUpdate.getIsbn());
            return new BookResponse(bookToUpdate, HttpStatus.OK);
        }

        log.info("update failed, book with isbn {} was not found", isbn);
        return new BookResponse(new Book(), HttpStatus.NOT_FOUND);
    }

    @Transactional
    public HttpStatus deleteBookByISBN(String isbn) {
        final Optional<Book> book = bookRepository.findByIsbnIgnoreCase(isbn);

        if (book.isPresent()) {
            final Book bookToDelete = book.get();
            bookRepository.delete(bookToDelete);
            log.info("book with isbn {} has been deleted", isbn);
            return HttpStatus.NO_CONTENT;
        }

        log.info("deletion failed, book with isbn {} was not found", isbn);
        return HttpStatus.NOT_FOUND;
    }
}