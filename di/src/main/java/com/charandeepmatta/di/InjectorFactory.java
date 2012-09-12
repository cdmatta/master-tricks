package com.charandeepmatta.di;

import java.util.ArrayList;
import java.util.Collection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;

public abstract class InjectorFactory {
    public Injector createFullInjector() {
        return Guice.createInjector(getStage(), collectModules());
    }

    private Collection<Module> collectModules() {
        final Collection<Module> modules = new ArrayList<Module>();
        collectModulesInto(modules);
        return modules;
    }

    protected abstract void collectModulesInto(Collection<Module> modules);

    protected Stage getStage() {
        return Stage.DEVELOPMENT;
    }
}
