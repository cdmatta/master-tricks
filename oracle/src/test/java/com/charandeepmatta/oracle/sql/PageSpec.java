package com.charandeepmatta.oracle.sql;

import org.junit.runner.RunWith;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

@RunWith(JDaveRunner.class)
public class PageSpec extends Specification<Page> {
    public class PageInstance {
        private final int pageNumber = 5;
        private final int numberOfRows = 15;

        public Page create() {
            return new Page(pageNumber, numberOfRows);
        }

        public void hasCorrectPageNumber() {
            specify(context.getPageNumber(), pageNumber);
        }

        public void hasCorrectNumberOfRows() {
            specify(context.getNumberOfRowsPerPage(), numberOfRows);
        }

        public void returnCorrectStartingRowNumber() {
            specify(context.minimumResultRow(), 61);
        }

        public void returnsCorrectLastRowNumber() {
            specify(context.maximumResultRow(), 75);
        }
    }
}
