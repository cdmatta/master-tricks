package com.charandeepmatta.database.hsqldb.di;

import static com.charandeepmatta.database.di.Database.HSQLDB;
import static java.util.Arrays.asList;

import com.charandeepmatta.database.di.SingleConnectionLocalDataSourceModule;

public class SingleConnectionLocalOracleDataSourceModule extends SingleConnectionLocalDataSourceModule {
    public SingleConnectionLocalOracleDataSourceModule() {
        super(HSQLDB, asList("sql.syntax_ora=true"));
    }
}
