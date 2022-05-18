package com.smart.store.config.web;

import com.smart.store.enums.ResponseEnum;
import com.smart.store.exception.AlreadySignException;
import com.smart.store.exception.RewardNotFoundException;
import com.smart.store.exception.ScoreNotEnoughException;
import com.smart.store.exception.TaskNotFoundException;
import com.smart.store.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = "com.smart.store.controller.v1")
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(ScoreNotEnoughException.class)
    public Result handleScoreNotEnoughException(HttpServletRequest request, ScoreNotEnoughException ex) {
        log.error("method {}, uri: {}, exception: {}", request.getMethod(), request.getRequestURI(), ex);
        return getResult(ex, ResponseEnum.A2101);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public Result handleTaskNotFoundException(HttpServletRequest request, TaskNotFoundException ex) {
        log.error("method {}, uri: {}, exception: {}", request.getMethod(), request.getRequestURI(), ex);
        return getResult(ex, ResponseEnum.A2102);
    }

    @ExceptionHandler(RewardNotFoundException.class)
    public Result handleRewardNotFoundException(HttpServletRequest request, RewardNotFoundException ex) {
        log.error("method {}, uri: {}, exception: {}", request.getMethod(), request.getRequestURI(), ex);
        return getResult(ex, ResponseEnum.A2103);
    }

    @ExceptionHandler(AlreadySignException.class)
    public Result handleAlreadySignException(HttpServletRequest request, AlreadySignException ex) {
        log.warn("method {}, uri: {}, exception: {}", request.getMethod(), request.getRequestURI(), ex);
        return getResult(ex, ResponseEnum.A2104);
    }

    public Result getResult(Throwable ex, ResponseEnum responseEnum) {
        String msg = String.format("%s", responseEnum.getMessage());
        return Result.builder()
                .code(responseEnum.getCode())
                .msg(msg)
                .build();
    }
}
