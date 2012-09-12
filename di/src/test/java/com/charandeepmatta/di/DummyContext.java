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
