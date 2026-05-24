package com.community.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    void success_shouldReturnCode200() {
        Result<String> result = Result.success("hello");
        assertEquals(200, result.getCode());
        assertEquals("success", result.getMessage());
        assertEquals("hello", result.getData());
    }

    @Test
    void error_shouldReturnCode400() {
        Result<String> result = Result.error("出错了");
        assertEquals(400, result.getCode());
        assertEquals("出错了", result.getMessage());
        assertEquals(null,result.getData());
    }
}
