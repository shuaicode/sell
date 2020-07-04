package com.shuai.ordering.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = -4207235980941437713L;
    //错误码
    private Integer code;
    //提示信息
    private String msg;
    //具体内容
    private T data;


}
