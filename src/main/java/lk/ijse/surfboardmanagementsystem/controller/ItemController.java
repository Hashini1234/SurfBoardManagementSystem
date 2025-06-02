package lk.ijse.surfboardmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.surfboardmanagementsystem.dto.Item;
import lk.ijse.surfboardmanagementsystem.model.ItemMOdel;


import java.sql.SQLException;
import java.util.ArrayList;

public class ItemController {
    public TextField txtName;
    public TextField txtType;
    public TextField txtAvailability;
    public TextField txtCondition;
    public TableView tblItems;
    public TableColumn colItemId;
    public TableColumn colName;
    public TableColumn colType;
    public TableColumn colCondition;
    public TableColumn colAvailability;

    private final ItemMOdel Imodel = new ItemMOdel();
    public Label lblid;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        SetNextId();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = Imodel.getall();
        ObservableList<Item> observableList = FXCollections.observableArrayList();
        for (Item item : items) {
            observableList.add(item);
        }
        tblItems.setItems(observableList);
    }

    private void GetNextId() {
    }

    private void SetNextId() throws SQLException, ClassNotFoundException {
        String nextId = Imodel.getNextId();

        lblid.setText(nextId);


    }

    private void setcellvaluefactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCondition.setCellValueFactory(new PropertyValueFactory<>("conditions"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availabilityStatus"));


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = lblid.getText();
        boolean isDelete = Imodel.DeleteItem(id);
        if (isDelete) {
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION, "Item Delete", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Item not Delete", ButtonType.OK).show();
        }


    }


    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = lblid.getText();
        String name = txtName.getText();
        String type = txtType.getText();
        String condition = txtCondition.getText();
        String availability = txtAvailability.getText();

        //System.out.println(id+name+contactDetails+payForHour);

        Item item = new Item(
                id, name, type, condition, availability
        );
        boolean isSave = Imodel.SaveItem(item);
        if (isSave) {
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION, "Item Saved", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Item not Save", ButtonType.OK).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = lblid.getText();
        String name = txtName.getText();
        String type = txtType.getText();
        String condition = txtCondition.getText();
        String availability = txtAvailability.getText();


        Item item = new Item(
                id, name, type, condition, availability
        );
        boolean isUpdate = Imodel.UpdateItem(item);
        if (isUpdate) {
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION, "Item Update", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Item not Update", ButtonType.OK).show();
        }
    }

    public void btnGenerateOnAction(ActionEvent actionEvent) {
    }

    public void tblClickOnAction(MouseEvent mouseEvent) {
        Item selectedItem = (Item) tblItems.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblid.setText(selectedItem.getItemId());
            txtName.setText(selectedItem.getName());
            txtType.setText(selectedItem.getType());
            txtCondition.setText(selectedItem.getConditions());
            txtAvailability.setText(selectedItem.getAvailabilityStatus());


        }
    }
}



