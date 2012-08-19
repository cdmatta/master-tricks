package com.charandeepmatta.di;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import jdave.Specification;

public abstract class GuiceSpecification<T> extends Specification<T> {
    protected Binder binder;
    protected Injector injector;

    @Override
    public void create() {
        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                binder = binder();
                addBindings();
            }
        });
        InjectorHolder.setInjector(injector);
    }

    protected abstract void addBindings();

    protected void install(final Module... modules) {
        for (Module module : modules) {
            binder.install(module);
        }
    }

    protected <X> X getInstance(final Class<X> clazz) {
        return injector.getInstance(clazz);
    }
}
