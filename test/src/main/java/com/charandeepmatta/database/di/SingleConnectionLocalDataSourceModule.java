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

import static org.apache.commons.lang.StringUtils.join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.Scopes;

public class SingleConnectionLocalDataSourceModule extends AbstractModule {
    private final List<String> parameters;
    private final Scope scope;
    private final Database database;

    public SingleConnectionLocalDataSourceModule(final Database database, final List<String> parameters) {
        this(database, parameters, Scopes.SINGLETON);
    }

    public SingleConnectionLocalDataSourceModule(final Database database, final List<String> parameters, final Scope scope) {
        this.database = database;
        this.parameters = parameters;
        this.scope = scope;
    }

    @Override
    protected void configure() {
        try {
            Class.forName(database.getDriverClass());
            bind(DataSource.class).toProvider(new DataSourceProvider(database.getJdbcUrlPrefix(), parameters)).in(scope);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static class DataSourceProvider implements Provider<DataSource> {
        private final String jdbcUrlPrefix;
        private final List<String> parameters;

        public DataSourceProvider(final String jdbcUrlPrefix, final List<String> parameters) {
            this.jdbcUrlPrefix = jdbcUrlPrefix;
            this.parameters = parameters;
        }

        @Override
        public DataSource get() {
            try {
                String url = jdbcUrlPrefix + join(parameters, ";");
                Connection conn = DriverManager.getConnection(url, "sa", "");
                SingleConnectionDataSource dataSource = new SingleConnectionDataSource(conn, true);
                Schema.createDatabase(dataSource);
                return dataSource;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
