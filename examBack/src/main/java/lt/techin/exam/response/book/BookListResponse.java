package lt.techin.exam.response.book;

import lt.techin.exam.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;

public class BookListResponse {
    private Page<Book> books;
    private HttpStatus status;


    public BookListResponse() {
    }

    public BookListResponse(Page<Book> books, HttpStatus status) {
        this.books = books;
        this.status = status;
    }


    public Page<Book> getBooks() {
        return books;
    }

    public void setBooks(Page<Book> books) {
        this.books = books;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
