package com.shuai.ordering.repository;

import com.shuai.ordering.Utils.KeyUtil;
import com.shuai.ordering.pojo.SellerInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerid(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("root");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abd");

        SellerInfo result = repository.save(sellerInfo);
        Assert.assertNotNull(result);

    }

    @Test
    void findByUsername() {
        SellerInfo result = repository.findByUsername("admin");
        Assert.assertEquals("admin", result.getUsername());
    }
}