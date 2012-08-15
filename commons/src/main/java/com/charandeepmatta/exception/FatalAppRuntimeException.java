package com.charandeepmatta.exception;

import static java.text.MessageFormat.format;

public class FatalAppRuntimeException extends RuntimeException {
    public FatalAppRuntimeException(final String message) {
        super(message);
    }

    public FatalAppRuntimeException(final String pattern, final Object... arguments) {
        super(format(pattern, arguments));
    }

    public FatalAppRuntimeException(final Throwable cause, final String message) {
        super(message, cause);
    }

    public FatalAppRuntimeException(final Throwable cause, final String pattern, final Object... arguments) {
        super(format(pattern, arguments), cause);
    }
}
