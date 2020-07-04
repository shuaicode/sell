package com.shuai.ordering.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SellerInfo {
    @Id
    private String sellerid;

    private String username;

    private String password;

    private String openid;

}
