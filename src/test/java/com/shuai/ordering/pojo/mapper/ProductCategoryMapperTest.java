package com.shuai.ordering.pojo.mapper;

import com.shuai.ordering.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "小帅不爱");
        map.put("category_type", "101");
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("小帅最不爱");
        productCategory.setCategoryType(102);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    void findByCategoryType() {
        ProductCategory result = mapper.findCategoryType(2);
        Assert.assertNotNull(result);
    }

    @Test
    void findByCategoryName() {
        List<ProductCategory> result = mapper.findCategoryName("女生最爱");
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    void updateByCategoryType() {
        int result = mapper.updateByCategoryType("小帅最爱", 102);
        Assert.assertEquals(1, result);
    }

    @Test
    void updateByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("小帅最不爱");
        productCategory.setCategoryType(102);
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(0, result);
    }

    @Test
    void deleteByCategoryType() {
        int result = mapper.deleteByCategoryType(102);
        Assert.assertEquals(1, result);
    }

    @Test
    void selectByCategoryType() {
        ProductCategory productCategory = mapper.selectByCategoryType(2);
        Assert.assertNotNull(productCategory);
    }

}