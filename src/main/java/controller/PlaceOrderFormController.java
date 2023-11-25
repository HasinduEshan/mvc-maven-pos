package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDto;
import dto.ItemDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.CustomerModel;
import model.ItemModel;
import model.impl.CustomerModelImpl;
import model.impl.ItemModelImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderFormController {
    public JFXComboBox cmbCustId;
    public JFXComboBox cmbItemCode;
    public JFXTextField txtCustName;
    public JFXTextField txtDesc;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;

    private List<CustomerDto> customers;
    private List<ItemDto> items;

    private CustomerModel customerModel = new CustomerModelImpl();
    private ItemModel itemModel = new ItemModelImpl();



    public void initialize(){
        loadCustomerIds();
        loadItemCodes();

        cmbCustId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, id) -> {
            for (CustomerDto dto:customers) {
                if (dto.getId().equals(id)){
                    txtCustName.setText(dto.getName());
                }
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, code) -> {
            for (ItemDto dto:items) {
                if (dto.getCode().equals(code)){
                    txtDesc.setText(dto.getDesc());
                    txtUnitPrice.setText(String.format("%.2f",dto.getUnitPrice()));
                }
            }
        });
    }

    private void loadItemCodes() {
        try {
            items = itemModel.allItems();
            ObservableList list = FXCollections.observableArrayList();
            for (ItemDto dto:items) {
                list.add(dto.getCode());
            }
            cmbItemCode.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerIds() {
        try {
            customers = customerModel.allCustomers();
            ObservableList list = FXCollections.observableArrayList();
            for (CustomerDto dto:customers) {
                list.add(dto.getId());
            }
            cmbCustId.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) {

    }
}
