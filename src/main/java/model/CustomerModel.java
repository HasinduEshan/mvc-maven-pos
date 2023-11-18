package model;

import dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerModel {
    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDto dto);
    boolean deleteCustomer(String id);
    List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException;
    CustomerDto searchCustomer(String id);
}
