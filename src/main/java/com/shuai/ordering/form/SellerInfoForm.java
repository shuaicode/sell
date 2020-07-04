package com.shuai.ordering.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SellerInfoForm {
    @NotEmpty(message = "用户名必填")
    private String username;
    @NotEmpty(message = "密码必填")
    private String password;
}
