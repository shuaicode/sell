package com.shuai.ordering.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shuai.ordering.dto.OrderDTO;
import com.shuai.ordering.enums.ResultEnum;
import com.shuai.ordering.exception.SellException;
import com.shuai.ordering.form.OrderForm;
import com.shuai.ordering.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenId(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误，string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAN_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;


    }
}
