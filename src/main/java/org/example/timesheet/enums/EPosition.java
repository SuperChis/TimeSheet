package org.example.timesheet.enums;

import lombok.Getter;

@Getter
public enum EPosition {
    BackEndDeveloper("back end developer"),
    FrontEndDeveloper("Front end developer"),
    Tester("tester"),
    ProjectManager("project manager");

    String text;

    EPosition(String text) {
        this.text = text;
    }
}
