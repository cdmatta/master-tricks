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

import static java.util.Arrays.asList;

import com.charandeepmatta.database.h2.di.SingleConnectionLocalOracleDataSourceModule;
import com.charandeepmatta.di.GuiceSpecification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.google.inject.Inject;

import jdave.junit4.JDaveRunner;

@RunWith(JDaveRunner.class)
public class QueryPaginatorSpec extends GuiceSpecification<QueryPaginator> {
    @Override
    protected void addBindings() {
        install(new SingleConnectionLocalOracleDataSourceModule());
    }

    public class WithQueryPaginator {
        public QueryPaginator create() {
            return getInstance(QueryPaginator.class);
        }

        public void paginatedResultsCanBeFetched() {
            ActorFetcher actorFetcher = getInstance(ActorFetcher.class);
            String paginatedQuery = context.paginate("select name from actor", new Page(3, 2));
            List<String> results = actorFetcher.runQuery(paginatedQuery);
            specify(results, asList("Laurence Fishburne", "Jean-Claude Van Damme"));
        }
    }

    public static class ActorFetcher {
        @Inject
        DataSource dataSource;

        public List<String> runQuery(final String query) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            return jdbcTemplate.query(query, new RowMapper<String>() {
                @Override
                public String mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                    return rs.getString(1);
                }
            });
        }
    }
}
