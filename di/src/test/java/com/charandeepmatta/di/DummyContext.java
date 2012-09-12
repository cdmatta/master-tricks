package com.charandeepmatta.di;

import static com.google.inject.name.Names.named;

import java.util.Collection;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

public class DummyContext extends InjectorFactory {
    static final String BINDING_STRING = "dummy-binding";
    static final String BINDING_VALUE = "A";

    @Override
    protected void collectModulesInto(final Collection<Module> modules) {
        modules.add(new StringPropertyModule());
    }

    static class StringPropertyModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(String.class).annotatedWith(named(BINDING_STRING)).toInstance(BINDING_VALUE);
        }
    }
}
