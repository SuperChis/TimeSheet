package org.example.timesheet.enums;

public enum EBranch {
    HaNoi1("Ha Noi 1"),
    HaNoi2("Ha Noi 2"),
    HaNoi3("Ha Noi 3"),
    Vinh("Vinh"),
    DaNang("Da Nang"),
    QuyNhon("Quy Nhon"),
    HoChiMinh("Ho Chi Minh");


    String text;

    EBranch(String text) {
        this.text = text;
    }

}
