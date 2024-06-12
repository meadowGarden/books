package lt.techin.exam.dto;

import lt.techin.exam.entity.BookCategory;

import java.util.List;

public class BookDTO {
    private String bookName;
    private String bookDescription;
    private String isbn;
    private long pageCount;
    private List<BookCategory> categories;


    public BookDTO() {
    }

    public BookDTO(String bookName, String bookDescription, String isbn, long pageCount, List<BookCategory> categories) {
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.categories = categories;
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public List<BookCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<BookCategory> categories) {
        this.categories = categories;
    }
}
