package com.shuai.ordering.service.serviceImpl;

import com.shuai.ordering.enums.ProductStatusEnum;
import com.shuai.ordering.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;


    @Test
    void findOne() throws Exception {
        Optional<ProductInfo> productInfo = productService.findOne("123456");
        Assert.assertEquals("123456", productInfo.get().getProductId());
    }

    @Test
    void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(1, productInfoList.size());
    }

    @Test
    void findAll() {
        PageRequest request = PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        //System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());

    }

    @Test
    void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123458");
        productInfo.setProductName("炒面");
        productInfo.setProductPrice(new BigDecimal(10.0));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好吃的炒面");
        productInfo.setProductIcon("https://chaomian.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.ordinal());
        productInfo.setCategoryType(2);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    void offsale() {
        ProductInfo result = productService.offsale("123456");
        Assert.assertEquals(ProductStatusEnum.DOWN, result.getProductStatusEnum());

    }
}