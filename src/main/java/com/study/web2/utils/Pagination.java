package com.study.web2.utils;

import lombok.Data;

@Data
public class Pagination {
    private int totalDataCount;
    private int numOfRows;
    private int pageNum;
    private int totalPages;

    public Pagination(int totalDataCount, int numOfRows, int pageNum) {
        this.totalDataCount = totalDataCount;
        this.numOfRows = numOfRows;
        this.pageNum = pageNum;
        this.totalPages = totalDataCount / numOfRows + (totalDataCount % numOfRows != 0 ? 1 : 0);
    }
}
