package com.smart.store.enums;

public enum ResponseEnum {

    SUCCESS("00000", "请求成功"),
    ERROR("00001", "请求失败"),
    A2101("A2101", "分数不足"),
    A2102("A2102", "找不到任务"),
    A2103("A2103", "找不到商品"),
    A2104("A2104", "已签到")
    ;

    private String code;

    private String message;

    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
