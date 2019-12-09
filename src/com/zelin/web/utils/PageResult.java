package com.zelin.web.utils;

import java.util.List;

public class PageResult<T> {
    private List<T> list;       //页每页的记录集合
    private int page;           //当前页
    private long totalPages;       //页面总数
    private long totalCount;      //总记录条数

    public PageResult() {
    }

    public PageResult(List<T> list, int page, long totalPages, long totalCount) {
        this.list = list;
        this.page = page;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "list=" + list +
                ", page=" + page +
                ", totalPages=" + totalPages +
                ", totalCount=" + totalCount +
                '}';
    }
}
