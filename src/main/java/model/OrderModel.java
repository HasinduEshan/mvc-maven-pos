package model;

import dto.OrderDto;

import java.sql.SQLException;

public interface OrderModel {
    boolean saveOrder(OrderDto dto);
    OrderDto lastOrder() throws SQLException, ClassNotFoundException;
}
