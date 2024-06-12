package lt.techin.exam.service;

import lombok.extern.slf4j.Slf4j;
import lt.techin.exam.dto.BookCategoryDTO;
import lt.techin.exam.entity.Book;
import lt.techin.exam.entity.BookCategory;
import lt.techin.exam.utilities.mapper.BookCategoryMapper;
import lt.techin.exam.repository.BookCategoryRepository;
import lt.techin.exam.repository.BookRepository;
import lt.techin.exam.request.category.BookCategoryListRequest;
import lt.techin.exam.response.bookCategory.BookCategoryListResponse;
import lt.techin.exam.response.bookCategory.BookCategoryResponse;
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
public class BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookCategoryService(BookCategoryRepository bookCategoryRepository, BookRepository bookRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
        this.bookRepository = bookRepository;
    }


    @Transactional
    public BookCategoryResponse addBookCategory(BookCategoryDTO dto) {
        final Optional<BookCategory> bookCategory = bookCategoryRepository
                .findByCategoryNameIgnoreCase(dto.getCategoryName());

        if (bookCategory.isEmpty()) {
            final BookCategory bookCategoryToAdd = BookCategoryMapper.DTOToBookCategory(dto);

            if (dto.getBooks() != null) {
                final List<Book> books = new ArrayList<>();
                for (Book b : dto.getBooks()) {
                    final Optional<Book> bookToAdd = bookRepository
                            .findByIsbnIgnoreCase(b.getIsbn());
                    bookToAdd.ifPresent(books::add);
                }
                bookCategoryToAdd.setBooks(books);
            }

            final BookCategory savedBookCategory = bookCategoryRepository.save(bookCategoryToAdd);
            log.info("added book category, with id {}", savedBookCategory.getId());
            return new BookCategoryResponse(savedBookCategory, HttpStatus.CREATED);
        }
        final BookCategory bookCategoryInRepository = bookCategory.get();
        log.info("book category already in repository, id {}", bookCategoryInRepository.getId());
        return new BookCategoryResponse(bookCategoryInRepository, HttpStatus.OK);
    }


    public BookCategoryListResponse retrieveAllBookCategories(BookCategoryListRequest request) {
        final Sort.Direction direction = request.isSortAsc() ? Sort.Direction.ASC : Sort.Direction.DESC;
        final Sort sort = Sort.by(direction, request.getSortBy());

        int pageNumber = request.getPageNumber();
        final int pageSize = request.getPageSize();
        final String contains = request.getContains();

        final Pageable pageable = PageRequest.of(--pageNumber, pageSize, sort);
        if (contains == null || contains.isEmpty()) {
            log.info("retrieving all book categories in repository");
            Page<BookCategory> page = bookCategoryRepository.findAll(pageable);
            return new BookCategoryListResponse(page, HttpStatus.OK);
        }

        log.info("retrieving book categories containing {}", contains);
        Page<BookCategory> page = bookCategoryRepository
                .findByCategoryNameContainingIgnoreCase(pageable, request.getContains());
        return new BookCategoryListResponse(page, HttpStatus.OK);
    }


    public BookCategoryResponse retrieveBookCategoryByName(String categoryName) {
        final Optional<BookCategory> bookCategory = bookCategoryRepository.findByCategoryNameIgnoreCase(categoryName);

        if (bookCategory.isPresent()) {
            final BookCategory bookCategoryToRetrieve = bookCategory.get();
            log.info("retrieving book category with name {}", bookCategoryToRetrieve.getCategoryName());
            return new BookCategoryResponse(bookCategoryToRetrieve, HttpStatus.OK);
        }

        log.info("book category with name {} was not found", categoryName);
        return new BookCategoryResponse(new BookCategory(), HttpStatus.NOT_FOUND);
    }


    public BookCategoryResponse updateBookCategoryByName(String categoryName, BookCategoryDTO dto) {
        final Optional<BookCategory> bookCategory = bookCategoryRepository.findByCategoryNameIgnoreCase(categoryName);

        if (bookCategory.isPresent()) {
            final BookCategory bookCategoryToUpdate = bookCategory.get();
            bookCategoryToUpdate.setCategoryName(dto.getCategoryName());
            log.info("book category with name {} has been updated", bookCategoryToUpdate.getCategoryName());
            return new BookCategoryResponse(bookCategoryToUpdate, HttpStatus.OK);
        }

        log.info("update failed, book category with name {} was not found", categoryName);
        return new BookCategoryResponse(new BookCategory(), HttpStatus.NOT_FOUND);
    }


    public HttpStatus deleteBookCategoryByName(String categoryName) {
        final Optional<BookCategory> bookCategory = bookCategoryRepository.findByCategoryNameIgnoreCase(categoryName);

        if (bookCategory.isPresent()) {
            final BookCategory bookToDelete = bookCategory.get();
            bookCategoryRepository.deleteById(bookToDelete.getId());
            log.info("book category with name {} has been deleted", categoryName);
            return HttpStatus.NO_CONTENT;
        }

        log.info("deletion failed, book category with name {} was not found", categoryName);
        return HttpStatus.NOT_FOUND;
    }
}
