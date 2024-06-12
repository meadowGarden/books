package lt.techin.exam.response.bookCategory;

import lt.techin.exam.entity.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

public class BookCategoryListResponse {
    private Page<BookCategory> bookCategories;
    private HttpStatus status;


    public BookCategoryListResponse() {
    }

    public BookCategoryListResponse(Page<BookCategory> bookCategories, HttpStatus status) {
        this.bookCategories = bookCategories;
        this.status = status;
    }


    public Page<BookCategory> getBookCategories() {
        return bookCategories;
    }

    public void setBookCategories(Page<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
