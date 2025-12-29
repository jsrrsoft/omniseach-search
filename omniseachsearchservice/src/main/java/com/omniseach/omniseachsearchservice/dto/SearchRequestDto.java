package com.omniseach.omniseachsearchservice.dto;

public class SearchRequestDto {

    private String query;
    private int page = 0;
    private int size = 10;

    public String getQuery() { return query; }
    public int getPage() { return page; }
    public int getSize() { return size; }

    public void setQuery(String query) { this.query = query; }
    public void setPage(int page) { this.page = page; }
    public void setSize(int size) { this.size = size; }
}
