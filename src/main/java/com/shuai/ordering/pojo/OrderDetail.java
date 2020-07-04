package com.shuai.ordering.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

//    private Date createTime;
//
//    private Date updateTime;


}
