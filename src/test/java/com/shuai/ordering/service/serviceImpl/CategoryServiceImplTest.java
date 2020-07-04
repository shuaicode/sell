package com.shuai.ordering.service.serviceImpl;

import com.shuai.ordering.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;


    @Test
    void findOne() {
        Optional<ProductCategory> productCategory = categoryService.findOne(2);
        System.out.println(productCategory.get().getCategoryName());

    }

    @Test
    void findAll() {
        List<ProductCategory> productCategories = categoryService.findAll();
        Assert.assertNotEquals(0, productCategories.size());
    }
}