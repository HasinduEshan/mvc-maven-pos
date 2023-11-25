package model;

import dto.OrderDetailsDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsModel {
    boolean saveOrderDetails(List<OrderDetailsDto> list) throws SQLException, ClassNotFoundException;
}
