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

import static com.charandeepmatta.di.DummyContext.BINDING_STRING;
import static com.charandeepmatta.di.DummyContext.BINDING_VALUE;

import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

@RunWith(JDaveRunner.class)
public class SystemPropertyContextClassResolverSpec extends Specification<SystemPropertyContextClassResolver> {
    private static final String environmentKey = "environment.key";

    public class WhenResolvingEnvironment {
        public SystemPropertyContextClassResolver create() {
            System.setProperty(environmentKey, DummyContext.class.getCanonicalName());
            return new SystemPropertyContextClassResolver(environmentKey);
        }

        public void hasCorrectBinding() {
            Injector injector = context.newInjectorFactory().createFullInjector();
            DummyClassWithBinding dummyClass = injector.getInstance(DummyClassWithBinding.class);
            specify(dummyClass.getValue(), BINDING_VALUE);
        }

        public void destroy() {
            System.clearProperty(environmentKey);
        }
    }

    static class DummyClassWithBinding {
        @Inject
        @Named(BINDING_STRING)
        private String value;

        public String getValue() {
            return value;
        }
    }
}
