package com.shuai.ordering.service;

import com.shuai.ordering.dto.CartDTO;
import com.shuai.ordering.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductInfo> findOne(String productId);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    void increaseStock(List<CartDTO> cartDTOList);

    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onsale(String productId);

    //下架
    ProductInfo offsale(String productId);

}
