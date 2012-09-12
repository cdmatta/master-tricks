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

import static org.apache.commons.lang.StringUtils.leftPad;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class Schema {
    public static void createDatabase(final DataSource dataSource) throws IOException {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        updateSqlFromFile(jdbc, "base.sql");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Integer i = 0;
        while (true) {
            i++;
            Resource[] resources = resolver.getResources("classpath*:" + leftPad(i.toString(), 3, "0") + "_*.sql");
            if (resources.length == 0) {
                break;
            }
            updateSqlTo(jdbc, resources[0].getInputStream());
        }
    }

    private static void updateSqlFromFile(final JdbcTemplate jdbc, final String fileName) throws IOException {
        ClassPathResource base = new ClassPathResource(fileName);
        updateSqlTo(jdbc, base.getInputStream());
    }

    private static void updateSqlTo(final JdbcTemplate jdbc, final InputStream inputStream) throws IOException {
        String sqlFileAsString = IOUtils.toString(inputStream);
        jdbc.update(sqlFileAsString);
    }
}
