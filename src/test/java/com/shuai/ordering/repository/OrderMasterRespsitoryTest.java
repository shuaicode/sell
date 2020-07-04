package com.shuai.ordering.repository;

import com.shuai.ordering.pojo.OrderMaster;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterRespsitoryTest {
    @Autowired
    private OrderMasterRespsitory respsitory;

    private final String OPENID = "110110";

    @Test
    void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123455");
        orderMaster.setBuyerName("小朱");
        orderMaster.setBuyerPhone("17777777778");
        orderMaster.setBuyerAddress("成都");
        orderMaster.setBuyerOpenId(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));


        OrderMaster result = respsitory.save(orderMaster);
        Assert.assertNotNull(result);


    }

    @Test
    void findByBuyerOpenId() {
        PageRequest request = PageRequest.of(0, 3);
        Page<OrderMaster> result = respsitory.findByBuyerOpenId(OPENID, request);
        System.out.println(result.getTotalElements());
        Assert.assertNotEquals(0, result.getTotalElements());


    }
}