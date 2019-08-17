package com.atguigu.gmall.user.common.enums;

public enum ResponseEnum {

    SUCCESS("200","成功"),
    ERROR("404","失败"),
    ERROR_ERROR("405","系统异常");

    private final String code;
    private final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseEnum{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
