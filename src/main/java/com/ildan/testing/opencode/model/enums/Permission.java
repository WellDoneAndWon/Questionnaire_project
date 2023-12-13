package com.ildan.testing.opencode.model.enums;

import lombok.Getter;

public enum  Permission {

    ADMIN_FORM("ADMIN:PERMISSION"),
    USER_FORM("USER:PERMISSION");

    @Getter
    private final String title;

    Permission(String title) {
        this.title = title;
    }
}