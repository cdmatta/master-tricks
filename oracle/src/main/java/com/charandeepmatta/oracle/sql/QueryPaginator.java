package com.charandeepmatta.oracle.sql;

public class QueryPaginator {
    public String paginate(final String selectQuery, final Page page) {
        return "select * from (select a.*, ROWNUM rnum from (" + selectQuery + ") a" + "  where ROWNUM <= " + page.maximumResultRow() + " ) where rnum >= " + page.minimumResultRow();
    }
}
