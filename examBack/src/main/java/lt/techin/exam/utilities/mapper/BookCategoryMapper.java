package lt.techin.exam.utilities.mapper;

import lt.techin.exam.dto.BookCategoryDTO;
import lt.techin.exam.entity.BookCategory;

public class BookCategoryMapper {


    public static BookCategory DTOToBookCategory(BookCategoryDTO dto) {
        return new BookCategory(
                dto.getCategoryName(),
                dto.getBooks()
        );
    }
}
