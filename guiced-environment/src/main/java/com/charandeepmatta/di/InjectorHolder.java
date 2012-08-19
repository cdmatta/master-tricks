package com.charandeepmatta.di;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectorHolder {
    private static Injector injector;
    static {
        injector = Guice.createInjector();
    }

    public static void setInjector(final Injector injector) {
        InjectorHolder.injector = injector;
    }

    public static Injector getInjector() {
        return injector;
    }
}
