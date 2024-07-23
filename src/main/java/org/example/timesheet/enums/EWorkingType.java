package org.example.timesheet.enums;

import lombok.Getter;

@Getter
public enum EWorkingType {
    NORMAL_TIME("normalTime"),
    OVER_TIME("overTime");

    String text;

    EWorkingType(String text) {
        this.text = text;
    }
}
