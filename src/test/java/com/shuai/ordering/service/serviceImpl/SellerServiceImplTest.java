package com.shuai.ordering.service.serviceImpl;

import com.shuai.ordering.pojo.SellerInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SellerServiceImplTest {
    private static final String username = "admin";
    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    void findByUsername() {
        SellerInfo result = sellerService.findSellerInfoByUsername(username);
        Assert.assertEquals(username, result.getUsername());
    }
}