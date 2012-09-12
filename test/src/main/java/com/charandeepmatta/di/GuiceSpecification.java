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
