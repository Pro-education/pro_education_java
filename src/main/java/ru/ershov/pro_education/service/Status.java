package ru.ershov.pro_education.service;

import java.util.Map;

public enum Status {
    IN_AUTOMATIC_CHECK,
    IN_CHECK,
    NOT_PASSED,
    OK;

    private static final Map<Status, Status> NEXT_MAP =
            Map.of(IN_AUTOMATIC_CHECK, IN_CHECK,
                    IN_CHECK, OK,
                    NOT_PASSED, IN_CHECK);

    public static Status next(Status current) {
        return NEXT_MAP.get(current);
    }

}
