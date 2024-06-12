package lt.techin.exam.utilities.mapper;

import lt.techin.exam.dto.BookDTO;
import lt.techin.exam.entity.Book;

public class BookMapper {




    public static Book DTOToBook(BookDTO dto) {
        return new Book(
                dto.getBookName(),
                dto.getBookDescription(),
                dto.getIsbn(),
                dto.getPageCount(),
                dto.getCategories()
        );
    }











}
