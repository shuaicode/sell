package com.shuai.ordering.controller;

import com.shuai.ordering.Constant.CookieConstant;
import com.shuai.ordering.Constant.RedisConstant;
import com.shuai.ordering.Utils.CookieUtil;
import com.shuai.ordering.enums.ResultEnum;
import com.shuai.ordering.exception.SellException;
import com.shuai.ordering.form.SellerInfoForm;
import com.shuai.ordering.pojo.SellerInfo;
import com.shuai.ordering.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping({"/index", "/"})
    public ModelAndView index() {
        return new ModelAndView("login/index");
    }


    @PostMapping("/login")
    public ModelAndView login(@Valid SellerInfoForm form, BindingResult bindingResult, HttpServletResponse response, Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "sell/seller/index");
            return new ModelAndView("common/error", map);

        }
        SellerInfo sellerInfo = sellerService.findSellerInfoByUsername(form.getUsername());
        //判断商家用户名是否存在
        if (sellerInfo == null) {
            log.error("【登录】用户名不存在");
            map.put("msg", ResultEnum.SELLERINFO_USERNAME_NOT_EXIST.getMessage());
            map.put("url", "/sell/seller/index");
            return new ModelAndView("common/error", map);
            //判断密码是否正确
        } else if (!sellerInfo.getPassword().equals(form.getPassword())) {
            log.error("【登录】密码错误");
            map.put("msg", ResultEnum.SELLERINFO_PASSWORD_ERROR.getMessage());
            map.put("url", "/sell/seller/index");
            return new ModelAndView("common/error", map);
        }

        //设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), form.getUsername(), expire, TimeUnit.SECONDS);
        //设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return new ModelAndView("redirect:http://localhost:8080/sell/seller/order/list");


    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        //从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            //清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);

        }
        map.put("msg", ResultEnum.SELLERINFO_LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/index");
        return new ModelAndView("common/success", map);

    }

}

