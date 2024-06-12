package lt.techin.exam.response.book;

import lt.techin.exam.entity.Book;
import org.springframework.http.HttpStatus;

public class BookResponse {
    private Book book;
    private HttpStatus status;


    public BookResponse() {
    }

    public BookResponse(Book book, HttpStatus status) {
        this.book = book;
        this.status = status;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
