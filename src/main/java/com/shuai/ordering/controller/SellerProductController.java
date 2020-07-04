package com.shuai.ordering.controller;

import com.shuai.ordering.Utils.KeyUtil;
import com.shuai.ordering.VO.ProductInfoVO;
import com.shuai.ordering.dto.OrderDTO;
import com.shuai.ordering.exception.SellException;
import com.shuai.ordering.form.ProductForm;
import com.shuai.ordering.pojo.ProductCategory;
import com.shuai.ordering.pojo.ProductInfo;
import com.shuai.ordering.service.CategoryService;
import com.shuai.ordering.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size, Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);

    }

    //上架
    @RequestMapping("/onsale")
    public ModelAndView onsale(@RequestParam("productId") String productId, Map<String, Object> map) {
        try {
            productService.onsale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("/offsale")
    public ModelAndView offsale(@RequestParam("productId") String productId, Map<String, Object> map) {
        try {
            productService.offsale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId, Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            Optional<ProductInfo> productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo.get());
        }
        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/index", map);
    }

    @PostMapping("/save")
    //@CachePut(cacheNames = "product",key = "123")
    @CacheEvict(cacheNames = "product", key = "123")
    public ModelAndView save(@Valid ProductForm form, BindingResult bindingResult, Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        //Optional<ProductInfo> productInfo = productService.findOne(form.getProductId());
        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空，说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId()).get();

            } else {
                form.setProductId(KeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "sell/seller/product/index");
            return new ModelAndView("common/error", map);

        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);

    }


}
