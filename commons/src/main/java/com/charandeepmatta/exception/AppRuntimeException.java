package com.charandeepmatta.exception;

import static java.text.MessageFormat.format;

public class AppRuntimeException extends RuntimeException {
    public AppRuntimeException(final String message) {
        super(message);
    }

    public AppRuntimeException(final String pattern, final Object... arguments) {
        super(format(pattern, arguments));
    }

    public AppRuntimeException(final Throwable cause, final String message) {
        super(message, cause);
    }

    public AppRuntimeException(final Throwable cause, final String pattern, final Object... arguments) {
        super(format(pattern, arguments), cause);
    }
}
