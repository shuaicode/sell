package com.shuai.ordering.repository;

import com.shuai.ordering.pojo.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByUsername(String username);
    //SellerInfo findByOpenid(String openid);
}
