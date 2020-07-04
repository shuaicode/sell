package com.shuai.ordering.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: sell
 * @description: cookie工具类
 * @author: shuaistudy
 * @create: 2020-06-10 21:14
 **/
public class CookieUtil {
    public static void set(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);

    }

    /**
     * @Description: 获取cookie
     * @Param: [request, name]
     * @return: javax.servlet.http.Cookie
     * @Author: shuaistudy
     * @Date: 2020/6/10
     */
    public static Cookie get(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        } else {
            return null;
        }
    }

    /**
     * @Description: 将cookie封装成map
     * @Param: [request]
     * @return: java.util.Map<java.lang.String, javax.servlet.http.Cookie>
     * @Author: shuaistudy
     * @Date: 2020/6/10
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);

            }
        }
        return cookieMap;
    }
}

