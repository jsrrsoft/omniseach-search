package com.omniseach.omniseachsearchservice.util;

public class SearchUtils {

    public static String sanitize(String query) {
        if (query == null) return "";
        return query.trim().replaceAll("[^a-zA-Z0-9 ]", "");
    }
}
