package org.example.timesheet.enums;

import lombok.Getter;

@Getter
public enum EUserLevel {
    STAFF("staff"),
    INTERN("intern"),
    COLABORATOR("colaborator");

    String text;

    EUserLevel(String text) {
        this.text = text;
    }
}
