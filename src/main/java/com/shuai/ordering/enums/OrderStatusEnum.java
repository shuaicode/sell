package com.shuai.ordering.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0, "新订单"),
    Finish(1, "完结"),
    CANCEL(2, "已取消");

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


//    @Override
//    public Integer getCode() {
//        return this.code;
//    }
//    public String getMessage(){
//        return this.message;
//    }
}
