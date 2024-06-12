package lt.techin.exam.repository;

import lt.techin.exam.entity.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, UUID> {

    Optional<BookCategory> findByCategoryNameIgnoreCase(String name);
    Page<BookCategory> findByCategoryNameContainingIgnoreCase(Pageable pageable, String name);
}
