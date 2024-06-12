package lt.techin.exam.request.category;

public class BookCategoryListRequest {
    private int pageNumber;
    private int pageSize;
    private String contains;
    private String SortBy;
    private boolean SortAsc;


    public BookCategoryListRequest() {
    }

    public BookCategoryListRequest(int pageNumber, int pageSize, String contains, String sortBy, boolean sortAsc) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.contains = contains;
        SortBy = sortBy;
        SortAsc = sortAsc;
    }


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public String getSortBy() {
        return SortBy;
    }

    public void setSortBy(String sortBy) {
        SortBy = sortBy;
    }

    public boolean isSortAsc() {
        return SortAsc;
    }

    public void setSortAsc(boolean sortAsc) {
        SortAsc = sortAsc;
    }
}
