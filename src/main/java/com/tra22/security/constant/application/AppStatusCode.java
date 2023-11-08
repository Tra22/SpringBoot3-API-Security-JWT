package com.tra22.security.constant.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AppStatusCode {
    UNEXPECTED_ERR("005"),
    AUTHENTICATION("003"),
    NOT_FOUND_ENTITY("002"),
    APP_ERR("001"),
    SUCCESS("000");
    private final String code;
}
