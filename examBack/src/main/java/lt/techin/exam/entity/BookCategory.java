package lt.techin.exam.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "book_category")
public class BookCategory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType   .UUID)
    private UUID id;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;


    public BookCategory() {
    }

    public BookCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public BookCategory(String categoryName, List<Book> books) {
        this.categoryName = categoryName;
        this.books = books;
    }


    public void addBook(Book book) {
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
    }


    public UUID getId() {
        return id;
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
