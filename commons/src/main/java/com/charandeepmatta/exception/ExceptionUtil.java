package com.charandeepmatta.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
    @SuppressWarnings("resource")
    public static String generateStackTraceAsString(final Throwable throwable) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw, true);
            throwable.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        } catch (Exception e) {
            return throwable.toString();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (Exception e) {}
            }
            if (pw != null) {
                try {
                    pw.close();
                } catch (Exception e) {}
            }
        }
    }

    public static boolean contains(final Throwable root, final Class<? extends Exception> expectedException) {
        if (root == null) {
            return false;
        }
        if (expectedException.isAssignableFrom(root.getClass())) {
            return true;
        }
        if (root.getCause() != null) {
            return contains(root.getCause(), expectedException);
        }
        return false;
    }
}
