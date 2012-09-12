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
