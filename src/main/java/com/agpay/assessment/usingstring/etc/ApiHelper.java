package com.agpay.assessment.usingstring.etc;

public interface ApiHelper {
    public static <T> boolean isEmpty(T value) {
        if (value == null)
            return true;
        if (value instanceof String && ((String) value).trim().isEmpty())
            return true;
        return false;
    }

    public static <T> boolean isNotEmpty(T value) {
        return !isEmpty(value);
    }

}
