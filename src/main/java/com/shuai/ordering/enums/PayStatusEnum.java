package com.shuai.ordering.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

//    @Override
//    public Integer getCode() {
//        return this.code;
//    }
//
//    public String getMessage(){
//        return this.message;
//    }
}
