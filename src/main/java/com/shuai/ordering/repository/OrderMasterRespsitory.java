package com.shuai.ordering.repository;

import com.shuai.ordering.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRespsitory extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> findByBuyerOpenId(String buyerOpenId, Pageable pageable);


}
