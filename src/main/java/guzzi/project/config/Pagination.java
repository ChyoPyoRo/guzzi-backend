package guzzi.project.config;

public class Pagination {
    private int page;
    private int size;
    private int totalElements;
    private int startIdx;
    private int endIdx;
    private int totalPages;


    public Pagination(Object page, Object size, int voteTotalCnt){

        int Page = Integer.parseInt((String) page);
        int Size = Integer.parseInt((String) size);

        if ( Page > (voteTotalCnt-1) / Size + 1){
            Page =  (voteTotalCnt-1) / Size + 1;
        } else if (Page < 1) {
            Page = 1;
        }


        this.totalPages = (voteTotalCnt-1) / Size + 1;
        this.page = Page;
        this.size = Size;

    }
    public void setTotalRecordCount(int voteTotalCnt) {
        this.totalElements = voteTotalCnt;

        if (voteTotalCnt > 0) {
            voteTotalCnt();
        }
    }

    public void voteTotalCnt(){
        this.startIdx = (this.getPage()-1) * this.getSize();
        this.endIdx = this.getPage() * this.getSize();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int currentPage) {
        this.page = currentPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int pagePerSize) {
        this.size = pagePerSize;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int voteTotalCnt) {
        this.totalElements = voteTotalCnt;
    }

    public int getStartIdx() {
        return startIdx;
    }

    public void setStartIdx(int startIdx) {
        this.startIdx = startIdx;
    }

    public int getEndIdx() {
        return endIdx;
    }

    public void setEndIdx(int endIdx) {
        this.endIdx = endIdx;
    }
    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
