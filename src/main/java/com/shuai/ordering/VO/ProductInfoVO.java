package com.shuai.ordering.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

//商品详情
@Data
public class ProductInfoVO implements Serializable {


    private static final long serialVersionUID = -2185588665088261178L;
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("desciption")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

}
