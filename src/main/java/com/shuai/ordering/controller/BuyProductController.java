package com.shuai.ordering.controller;


import com.shuai.ordering.Utils.ResultVOUtli;
import com.shuai.ordering.VO.ProductInfoVO;
import com.shuai.ordering.VO.ProductVO;
import com.shuai.ordering.VO.ResultVO;
import com.shuai.ordering.pojo.ProductCategory;
import com.shuai.ordering.pojo.ProductInfo;
import com.shuai.ordering.service.CategoryService;
import com.shuai.ordering.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/buy/product")
public class BuyProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
//    @Cacheable(cacheNames = "product",key = "#sellerId",condition = "#sellerId.length() > 3",unless = "#result.getCode()!=0")
    @Cacheable(cacheNames = "product", key = "123")
//    public ResultVO list(@RequestParam("sellerId")String sellerId) {
    public ResultVO list() {


        List<ProductInfo> productInfoList = productService.findUpAll();

        List<Integer> categoryTypeList = new ArrayList<>();
        List<ProductVO> productVOList = new ArrayList<>();


        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());

        }
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryname(productCategory.getCategoryName());


            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }

            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);

        }


        return ResultVOUtli.success(productVOList);
    }

}
