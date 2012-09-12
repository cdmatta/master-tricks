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
package com.charandeepmatta.database.di;

public enum Database {
    H2("org.h2.Driver", "jdbc:h2:mem:;"),
    HSQLDB("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:testdb;");
    private final String driverClass;
    private final String jdbcUrlPrefix;

    private Database(final String driverClass, final String jdbcUrlPrefix) {
        this.driverClass = driverClass;
        this.jdbcUrlPrefix = jdbcUrlPrefix;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getJdbcUrlPrefix() {
        return jdbcUrlPrefix;
    }
}
