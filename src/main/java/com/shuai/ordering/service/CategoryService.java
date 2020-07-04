package com.shuai.ordering.service;

import com.shuai.ordering.pojo.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<ProductCategory> findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);


}
