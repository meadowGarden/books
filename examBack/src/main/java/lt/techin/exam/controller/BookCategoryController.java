package lt.techin.exam.controller;

import lombok.extern.slf4j.Slf4j;
import lt.techin.exam.dto.BookCategoryDTO;
import lt.techin.exam.entity.BookCategory;
import lt.techin.exam.request.category.BookCategoryListRequest;
import lt.techin.exam.response.bookCategory.BookCategoryListResponse;
import lt.techin.exam.response.bookCategory.BookCategoryResponse;
import lt.techin.exam.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/book_categories")
@Slf4j
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    @Autowired
    public BookCategoryController(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> addBookCategory(@RequestBody BookCategoryDTO dto) {
        log.info("request to add book category, name {}", dto.getCategoryName());
        final BookCategoryResponse response = bookCategoryService.addBookCategory(dto);
        final BookCategory bookCategory = response.getBookCategory();
        final HttpStatus status = response.getStatus();
        return new ResponseEntity<>(bookCategory, status);
    }

    @GetMapping
    public ResponseEntity<?> retrieveAllBooks(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String contains,
            @RequestParam(defaultValue = "categoryName") String sortBy,
            @RequestParam(defaultValue = "true") boolean sortAsc
    ) {
        final var request = new BookCategoryListRequest(pageNumber, pageSize, contains, sortBy, sortAsc);
        log.info("request to retrieve list of book categories");
        final BookCategoryListResponse response = bookCategoryService.retrieveAllBookCategories(request);
        final Page<BookCategory> page = response.getBookCategories();
        final HttpStatus status = response.getStatus();
        return new ResponseEntity<>(page, status);
    }

    @GetMapping(path = "/{isbn}")
    public ResponseEntity<?> retrieveBookByISBN(@PathVariable String categoryName) {
        log.info("request to retrieve book categories with name {}", categoryName);
        final BookCategoryResponse response = bookCategoryService.retrieveBookCategoryByName(categoryName);
        final BookCategory bookCategory = response.getBookCategory();
        final HttpStatus status = response.getStatus();
        return new ResponseEntity<>(bookCategory, status);
    }

    @PutMapping(path = "/{isbn}")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> updateBookByISBN(@PathVariable String categoryName, @RequestBody BookCategoryDTO dto) {
        log.info("request to update book category with name {}", categoryName);
        final BookCategoryResponse response = bookCategoryService.updateBookCategoryByName(categoryName, dto);
        final BookCategory bookCategory = response.getBookCategory();
        final HttpStatus status = response.getStatus();
        return new ResponseEntity<>(bookCategory, status);
    }

    @DeleteMapping(path = "/{isbn}")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> deleteBookByISBN(@PathVariable String categoryName) {
        log.info("request to delete book category with name {}", categoryName);
        final HttpStatus status = bookCategoryService.deleteBookCategoryByName(categoryName);
        return new ResponseEntity<>(status);
    }
}