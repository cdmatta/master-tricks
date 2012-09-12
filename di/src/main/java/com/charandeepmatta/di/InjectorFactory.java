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
