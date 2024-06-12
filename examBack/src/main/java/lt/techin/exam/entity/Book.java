package lt.techin.exam.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_description")
    private String bookDescription;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "page_count")
    private long pageCount;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnore
    private List<BookCategory> categories;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinTable(name = "user_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;


    public Book() {
    }

    public Book(String bookName, String bookDescription, String isbn, long pageCount) {
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.isbn = isbn;
        this.pageCount = pageCount;
    }

    public Book(String bookName, String bookDescription, String isbn, long pageCount, List<BookCategory> categories) {
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.categories = categories;
    }


    public void addCategory(BookCategory bookCategory) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(bookCategory);
    }


    public UUID getId() {
        return id;
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
