package lt.techin.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private List<BookCategory> categories = new ArrayList<>();  // Initialize the collection

//    @ManyToMany(fetch = FetchType.EAGER,
//            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}
//    )
//    @JoinTable(name = "user_books",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private List<User> users = new ArrayList<>();

    public Book(String bookName, String bookDescription, String isbn, int pageCount) {
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
        categories.add(bookCategory);
        bookCategory.getBooks().add(this);
    }

    public void removeCategory(BookCategory bookCategory) {
        categories.remove(bookCategory);
        bookCategory.getBooks().remove(this);
    }

//    public void addUser(User user) {
//        users.add(user);
//        user.getFavouriteBooks().add(this);
//    }
//
//    public void removeUser(User user) {
//        users.remove(user);
//        user.getFavouriteBooks().remove(this);
//    }
}
