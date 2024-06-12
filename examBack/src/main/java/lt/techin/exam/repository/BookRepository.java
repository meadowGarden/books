package lt.techin.exam.repository;

import lt.techin.exam.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> findByIsbnIgnoreCase(String isbn);
    Page<Book> findByBookNameContainingIgnoreCase(Pageable pageable, String bookName);
    Page<Book> findByBookNameContainingIgnoreCaseAndCategoriesCategoryNameContainingIgnoreCase(Pageable pageable, String bookName, String bookCategory);
    Page<Book> findByBookNameContainingIgnoreCaseOrCategoriesCategoryNameContainingIgnoreCase(Pageable pageable, String bookName, String bookCategory);
}
