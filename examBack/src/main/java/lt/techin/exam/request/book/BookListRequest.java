package lt.techin.exam.request.book;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookListRequest {
    private int pageNumber;
    private int pageSize;
    private String nameContains;
        private String categoryContains;
    private String SortBy;
    private boolean SortAsc;
}
