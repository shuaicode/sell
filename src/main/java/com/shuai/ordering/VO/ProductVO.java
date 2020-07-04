package com.shuai.ordering.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 7818226792025807436L;
    @JsonProperty("name")
    private String categoryname;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;


}
