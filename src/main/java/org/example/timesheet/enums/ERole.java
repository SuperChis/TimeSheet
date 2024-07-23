package org.example.timesheet.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum ERole {
    ROLE_USER("user"),
    ROLE_ADMIN("admin");

    String text;

    ERole(String text) {
        this.text = text;
    }
}
