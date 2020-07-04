package com.shuai.ordering.pojo.dao;

import com.shuai.ordering.pojo.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @program: sell
 * @description:
 * @author: shuaistudy
 * @create: 2020-06-12 17:55
 **/
public class ProductCategoryDao {
    @Autowired
    private ProductCategoryMapper mapper;

    public int insertByMap(Map<String, Object> map) {
        return mapper.insertByMap(map);

    }
}

