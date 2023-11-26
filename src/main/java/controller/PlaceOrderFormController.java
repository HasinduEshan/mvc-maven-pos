package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import db.DBConnection;
import dto.CustomerDto;
import dto.ItemDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import model.CustomerModel;
import model.ItemModel;
import model.impl.CustomerModelImpl;
import model.impl.ItemModelImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderFormController {

    @FXML
    private JFXComboBox<?> cmbCustId;

    @FXML
    private JFXComboBox<?> cmbCode;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTreeTableView<?> tblItem;

    @FXML
    private TreeTableColumn<?, ?> colCode;

    @FXML
    private TreeTableColumn<?, ?> colDesc;

    @FXML
    private TreeTableColumn<?, ?> colQty;

    @FXML
    private TreeTableColumn<?, ?> colAmount;

    @FXML
    private TreeTableColumn<?, ?> colOption;

    @FXML
    private Label lblTotal;

    private CustomerModel customerModel = new CustomerModelImpl();
    private ItemModel itemModel = new ItemModelImpl();
    private List<CustomerDto> customers;
    private List<ItemDto> items;

    public void initialize(){
        try {
            customers = customerModel.allCustomers();
            items = itemModel.allItems();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadCustomerIds();
    }

    private void loadCustomerIds() {
        ObservableList list = FXCollections.observableArrayList();

        for (CustomerDto dto:customers) {
            list.add(dto.getId());
        }

        cmbCustId.setItems(list);
    }

    @FXML
    void addToCartButtonOnAction(ActionEvent event) {

    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) tblItem.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void placeOrderButtonOnAction(ActionEvent event) {

    }

}
