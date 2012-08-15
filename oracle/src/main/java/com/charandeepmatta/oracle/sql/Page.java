package com.charandeepmatta.oracle.sql;

import com.charandeepmatta.domain.ReflectionEqualsHashCode;

public class Page extends ReflectionEqualsHashCode {
    private final int pageNumber;
    private final int numberOfRowsPerPage;

    public Page(final int pageNumber, final int numberOfRowsPerPage) {
        this.pageNumber = pageNumber;
        this.numberOfRowsPerPage = numberOfRowsPerPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getNumberOfRowsPerPage() {
        return numberOfRowsPerPage;
    }

    public int minimumResultRow() {
        return (pageNumber - 1) * numberOfRowsPerPage + 1;
    }

    public int maximumResultRow() {
        return pageNumber * numberOfRowsPerPage;
    }
}
