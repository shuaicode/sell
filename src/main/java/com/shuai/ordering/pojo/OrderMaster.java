package com.shuai.ordering.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shuai.ordering.enums.OrderStatusEnum;
import com.shuai.ordering.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenId;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;


}
