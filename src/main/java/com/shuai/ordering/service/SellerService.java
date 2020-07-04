package com.shuai.ordering.service;

import com.shuai.ordering.pojo.SellerInfo;

public interface SellerService {
    SellerInfo findSellerInfoByUsername(String username);
    //SellerInfo findSellerInfoByOpenid(String opedid);
}
