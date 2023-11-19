package model.impl;

import db.DBConnection;
import dto.CustomerDto;
import dto.ItemDto;
import model.ItemModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModelImpl implements ItemModel {
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItem(ItemDto id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        List<ItemDto> list = new ArrayList<>();
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM item");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            ));
        }
        return list;
    }

    @Override
    public CustomerDto searchItem(String id) {
        return null;
    }
}
