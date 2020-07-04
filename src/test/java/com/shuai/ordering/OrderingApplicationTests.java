package com.shuai.ordering;

import com.shuai.ordering.pojo.ProductCategory;
import com.shuai.ordering.repository.ProductCategoryRepository;
import com.shuai.ordering.service.serviceImpl.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderingApplicationTests {
    @Autowired
    private CategoryServiceImpl categoryService;


    @Test
    public void Test() throws Exception {
        List<ProductCategory> categoryServiceAll = categoryService.findAll();
        Assert.assertNotEquals(0, categoryServiceAll.size());


    }


}
