package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CustomerFormController {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colOption;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    public void initialize(){

    }

    @FXML
    void reloadButtonOnAction(ActionEvent event) {

    }

    @FXML
    void saveButtonOnAction(ActionEvent event) {
        Customer c = new Customer(txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );
        String sql = "INSERT INTO customer VALUES('"+c.getId()+"','"+c.getName()+"','"+c.getAddress()+"',"+c.getSalary()+")";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(sql);
            if (result>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Saved!").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateButtonOnAction(ActionEvent event) {

    }

}
