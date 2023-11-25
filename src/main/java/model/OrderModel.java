package model;

import dto.OrderDto;

public interface OrderModel {
    boolean saveOrder(OrderDto dto);
}
