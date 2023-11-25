package model;

import dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemModel {
    boolean saveItem(ItemDto dto);
    boolean updateItem(ItemDto dto);
    boolean deleteItem(String code);
    ItemDto getItem(String code) throws SQLException, ClassNotFoundException;
    List<ItemDto> allItems() throws SQLException, ClassNotFoundException;
}
