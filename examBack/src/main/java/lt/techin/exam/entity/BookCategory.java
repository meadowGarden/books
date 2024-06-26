package lt.techin.exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "book_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCategory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories",
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Book> books = new ArrayList<>();

    public BookCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public BookCategory(String categoryName, List<Book> books) {
        this.categoryName = categoryName;
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
        book.getCategories().add(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.getCategories().remove(this);
    }
}
