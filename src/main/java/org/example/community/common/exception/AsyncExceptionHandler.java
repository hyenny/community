package org.example.community.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        String paramsString = Arrays.stream(params)
                .map(param -> Objects.toString(param, "null"))
                .collect(Collectors.joining(", "));

        log.error("method = {}, params = [{}], error = {}", method.getName(), paramsString, ex.getMessage(), ex);
    }
}