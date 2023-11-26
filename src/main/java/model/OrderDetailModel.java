package model;

import dto.OrderDetailDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailModel {
    boolean saveOrderDetails(List<OrderDetailDto> list) throws SQLException, ClassNotFoundException;
}
