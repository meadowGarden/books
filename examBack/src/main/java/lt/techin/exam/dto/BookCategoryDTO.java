package lt.techin.exam.dto;

import lt.techin.exam.entity.Book;

import java.util.List;
import java.util.UUID;

public class BookCategoryDTO {
    private UUID id;
    private String categoryName;
    private List<Book> books;



    public BookCategoryDTO() {
    }

    public BookCategoryDTO(String categoryName, List<Book> books) {
        this.categoryName = categoryName;
        this.books = books;
    }



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
