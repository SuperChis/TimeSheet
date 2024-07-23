package org.example.timesheet.enums;

import lombok.Getter;

@Getter
public enum EUserType {
    INTERN("intern"),
    FRESHER("fresher"),
    JUNIOR("junior"),
    MIDDLE("middle"),
    SENIOR("senior");

    String text;

    EUserType(String text) {
        this.text = text;
    }
}
