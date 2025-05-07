package lk.ijse.surfboardmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.surfboardmanagementsystem.dto.Supplier;
import lk.ijse.surfboardmanagementsystem.model.SupplierModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierController {
    public TextField txtSupplierName;
    public TextField txtContactInfo;
    public TableView tblSuppliers;
    public TableColumn colSupplierId;
    public TableColumn colName;
    public TableColumn colContactInfo;
    public TextField txtSupplierId;
    public Label lblid;
private final SupplierModel suppModel = new SupplierModel();
    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        SetNextId();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers= suppModel.getall();
        ObservableList<Supplier> observableList= FXCollections.observableArrayList();
        for (Supplier supplier:suppliers) {
            observableList.add(supplier);
        }
        tblSuppliers.setItems(observableList);
    }

    private void GetNextId() {
    }

    private void SetNextId() throws SQLException, ClassNotFoundException {
        String nextId = suppModel.getNextId();

        lblid.setText(nextId);


    }

    private void setcellvaluefactory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id=lblid.getText();
        boolean isDelete=suppModel.DeleteSupplier(id);
        if(isDelete){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"Supplier Delete", ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Supplier not Delete",ButtonType.OK).show();
        }


    }


    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id=lblid.getText();
        String name=txtSupplierName.getText();
        String contactInfo=txtContactInfo.getText();
        //System.out.println(id+name+contactDetails+payForHour);

        Supplier supplier=new Supplier(
                id,name,contactInfo
        );
        boolean isSave=suppModel.SaveSupplier(supplier);
        if(isSave){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"Supplier Saved",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Supplier not Save",ButtonType.OK).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id=lblid.getText();
        String name=txtSupplierName.getText();
        String contactinfo=txtContactInfo.getText();


        //System.out.println(id+name+contactDetails+payForHour);

        Supplier supplier=new Supplier(
                id,name,contactinfo
        );
        boolean isUpdate=suppModel.UpdateSupplier(supplier);
        if(isUpdate){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"Supplier Update",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Supplier not Update",ButtonType.OK).show();
        }
    }

    public void clickOnAction(MouseEvent mouseEvent) {
        Supplier selectedItem = (Supplier) tblSuppliers.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblid.setText(selectedItem.getSupplierId());
            txtSupplierName.setText(selectedItem.getName());
            txtContactInfo.setText(selectedItem.getContactInfo());



           /* // save button disable
            btnSave.setDisable(true);

            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);*/
        }
    }

    public void btnGenerateOnAction(ActionEvent actionEvent) {
    }
}


