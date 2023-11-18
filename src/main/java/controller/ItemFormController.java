package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import db.DBConnection;
import dto.CustomerDto;
import dto.ItemDto;
import dto.tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import dto.tm.ItemTm;

import java.io.IOException;
import java.sql.*;

public class ItemFormController {

    @FXML
    private TreeTableColumn colCode;

    @FXML
    private TreeTableColumn colDesc;

    @FXML
    private TreeTableColumn colOption;

    @FXML
    private TreeTableColumn colQty;

    @FXML
    private TreeTableColumn colUnitPrice;

    @FXML
    private BorderPane pane;

    @FXML
    private JFXTreeTableView<ItemTm> tblItem;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtUnitPrice;

    public void initialize(){
        colCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new TreeItemPropertyValueFactory<>("desc"));
        colUnitPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        loadItemTable();
    }

    private void loadItemTable() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM item";

        try {
            Statement stm = DBConnection.getInstance().getConnection().createStatement();
            ResultSet result = stm.executeQuery(sql);

            while (result.next()){
                JFXButton btn = new JFXButton("Delete");

                ItemTm tm = new ItemTm(
                        result.getString(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    deleteItem(tm.getCode());
                });

                tmList.add(tm);
            }

            TreeItem<ItemTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            tblItem.setRoot(treeItem);
            tblItem.setShowRoot(false);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteItem(String code) {
        String sql = "DELETE from item WHERE code=?";

        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1,code);
            int result = pstm.executeUpdate(sql);
            if (result>0){
                new Alert(Alert.AlertType.INFORMATION,"Item Deleted!").show();
                loadItemTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveButtonOnAction(ActionEvent event) {
        ItemDto dto = new ItemDto(txtCode.getText(),
                txtDesc.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );
        String sql = "INSERT INTO item VALUES(?,?,?,?)";

        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1,dto.getCode());
            pstm.setString(2,dto.getDesc());
            pstm.setDouble(3,dto.getUnitPrice());
            pstm.setInt(4,dto.getQty());
            int result = pstm.executeUpdate();
            if (result>0){
                new Alert(Alert.AlertType.INFORMATION,"Item Saved!").show();
                loadItemTable();
//                clearFields();
            }

        } catch (SQLIntegrityConstraintViolationException ex){
            new Alert(Alert.AlertType.ERROR,"Duplicate Entry").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateButtonOnAction(ActionEvent event) {

    }

}
