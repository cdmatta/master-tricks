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

import com.charandeepmatta.exception.FatalAppRuntimeException;

public class SystemPropertyContextClassResolver {
    private final String environmentPropertyName;

    public SystemPropertyContextClassResolver(final String environmentKeyName) {
        this.environmentPropertyName = environmentKeyName;
    }

    public InjectorFactory newInjectorFactory() {
        Class<?> injectorFactoryClass = getContextClass();
        try {
            return (InjectorFactory) injectorFactoryClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Class<?> getContextClass() {
        final String contextClassName = System.getProperty(environmentPropertyName);
        if (contextClassName == null) {
            throw new FatalAppRuntimeException("JVM property " + environmentPropertyName + " has not been specified.");
        }
        try {
            return Class.forName(contextClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not find context class '" + contextClassName + "' specified by JVM property " + environmentPropertyName, e);
        }
    }
}
