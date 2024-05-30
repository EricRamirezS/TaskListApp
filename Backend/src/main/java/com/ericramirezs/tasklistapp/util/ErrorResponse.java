package com.ericramirezs.tasklistapp.util;

import lombok.Getter;

@SuppressWarnings("unused")
@Getter
public abstract class ErrorResponse {
    private long timestamp;
    private Integer status;
    private String error;
    private String path;

    private ErrorResponse() {}
}
