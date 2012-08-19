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
