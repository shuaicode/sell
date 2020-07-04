package com.shuai.ordering.handler;

//import com.shuai.ordering.config.ProjectUrlConfig;

import com.shuai.ordering.Utils.ResultVOUtli;
import com.shuai.ordering.VO.ResultVO;
import com.shuai.ordering.config.ProjectUrlConfig;
import com.shuai.ordering.exception.SellException;
import com.shuai.ordering.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: sell
 * @description: 异常处理
 * @author: shuaistudy
 * @create: 2020-06-10 22:32
 **/
@ControllerAdvice
public class SellerExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:".concat(projectUrlConfig.getSell()).concat("/sell/seller/index"));
        //return new ModelAndView("redirect:http://localhost:8080/sell/seller/index");
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e) {
        return ResultVOUtli.error(e.getCode(), e.getMessage());

    }
}

