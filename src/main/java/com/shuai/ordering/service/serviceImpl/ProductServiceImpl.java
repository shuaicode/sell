package com.shuai.ordering.service.serviceImpl;

import com.shuai.ordering.dto.CartDTO;
import com.shuai.ordering.enums.ProductStatusEnum;
import com.shuai.ordering.enums.ResultEnum;
import com.shuai.ordering.exception.SellException;
import com.shuai.ordering.pojo.ProductInfo;
import com.shuai.ordering.repository.ProductInfoRepository;
import com.shuai.ordering.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public Optional<ProductInfo> findOne(String productId) {
        return repository.findById(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfo = repository.findById(cartDTO.getProductId());
            if (productInfo.get() == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.get().getProductStock() + cartDTO.getProductQuantity();
            productInfo.get().setProductStock(result);

            repository.save(productInfo.get());
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfo = repository.findById(cartDTO.getProductId());
            if (productInfo.get() == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.get().getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.get().setProductStock(result);

            repository.save(productInfo.get());

        }

    }

    @Override
    public ProductInfo onsale(String productId) {
        Optional<ProductInfo> productInfo = repository.findById(productId);
        if (productInfo.get() == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.get().getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.get().setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo.get());
    }

    @Override
    public ProductInfo offsale(String productId) {
        Optional<ProductInfo> productInfo = repository.findById(productId);
        if (productInfo.get() == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.get().getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.get().setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo.get());

    }
}
