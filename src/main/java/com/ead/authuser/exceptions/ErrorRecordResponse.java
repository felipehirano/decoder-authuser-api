package com.ead.authuser.exceptions;

import java.util.Map;

public record ErrorRecordResponse(
    int errorCode,
    String errorMessage,
    Map<String, String> errorDetails
) {}