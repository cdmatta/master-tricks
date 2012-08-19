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
