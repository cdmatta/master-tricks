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
