package com.charandeepmatta.database.h2.di;

import static com.charandeepmatta.database.di.Database.H2;
import static java.util.Arrays.asList;

import com.charandeepmatta.database.di.SingleConnectionLocalDataSourceModule;

public class SingleConnectionLocalOracleDataSourceModule extends SingleConnectionLocalDataSourceModule {
    public SingleConnectionLocalOracleDataSourceModule() {
        super(H2, asList("MODE=ORACLE"));
    }
}
