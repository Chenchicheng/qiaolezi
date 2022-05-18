package com.smart.store.config.web;

import com.smart.store.enums.ResponseEnum;
import com.smart.store.model.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = "com.smart.store.controller.v1")
public class ResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Void) {
            return Result.<Void>builder()
                    .msg(ResponseEnum.SUCCESS.getMessage())
                    .code(ResponseEnum.SUCCESS.getCode())
                    .build();
        }
        if (body instanceof byte[]) {
            return body;
        }
        if (body instanceof String) {
            return Result.<String>builder()
                    .msg(ResponseEnum.SUCCESS.getMessage())
                    .code(ResponseEnum.SUCCESS.getCode())
                    .data((String) body)
                    .build();
        } else {
            return Result.builder()
                    .msg(ResponseEnum.SUCCESS.getMessage())
                    .code(ResponseEnum.SUCCESS.getCode())
                    .data(body)
                    .build();
        }
    }
}
