package com.shuai.ordering.service.serviceImpl;

import com.shuai.ordering.pojo.SellerInfo;
import com.shuai.ordering.repository.SellerInfoRepository;
import com.shuai.ordering.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByUsername(String username) {
        return repository.findByUsername(username);

    }

//    @Override
//    public SellerInfo findSellerInfoByOpenid(String opedid) {
//        return repository.findByOpenid(opedid);
//    }
}
