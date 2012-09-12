/**
 *  Copyright 2012 Charandeep S. Matta
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
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
