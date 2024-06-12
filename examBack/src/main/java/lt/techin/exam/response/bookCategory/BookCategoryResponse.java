package lt.techin.exam.response.bookCategory;

import lt.techin.exam.entity.BookCategory;
import org.springframework.http.HttpStatus;

public class BookCategoryResponse {
    private BookCategory bookCategory;
    private HttpStatus status;


    public BookCategoryResponse() {
    }

    public BookCategoryResponse(BookCategory bookCategory, HttpStatus status) {
        this.bookCategory = bookCategory;
        this.status = status;
    }


    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
