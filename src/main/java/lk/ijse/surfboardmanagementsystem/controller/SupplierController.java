package lk.ijse.surfboardmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.surfboardmanagementsystem.dto.Guide;
import lk.ijse.surfboardmanagementsystem.dto.Supplier;
import lk.ijse.surfboardmanagementsystem.model.GuideModel;
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

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        SetNextId();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers= SupplierModel.getall();
        ObservableList<Supplier> observableList= FXCollections.observableArrayList(suppliers);
        for (Supplier supplier:suppliers) {
            observableList.add(supplier);
        }
        tblSuppliers.setItems(observableList);
    }

    private void GetNextId() {
    }

    private void SetNextId() throws SQLException, ClassNotFoundException {
        String nextId = SupplierModel.getNextId();
        System.out.println(nextId +" jjjjjjj");
        lblid.setText(nextId);


    }

    private void setcellvaluefactory() {
        clmSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactDetails"));

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id=lblid.getText();
        boolean isDelete=SupplierModel.DeleteSupplier(id);
        if(isDelete){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"Supplier Delete", ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Supplier not Delete",ButtonType.OK).show();
        }


    }

    @FXML
    void btnGenarateROnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id=lblid.getText();
        String name=txtSupplierName.getText();
        String contactDetails=txtContactInfo.getText();
        //System.out.println(id+name+contactDetails+payForHour);

        Supplier supplier=new Supplier(
                id,name,colContactInfo
        );
        boolean isSave=SupplierModel.SaveSupplier(supplier);
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
        String id=lblId.getText();
        String name=txtName.getText();
        String contactDetails=txtContactDetails.getText();
        String experience_Year=txtExperience_Year.getText();
        Double payForHour= Double.valueOf(txtPayForHour.getText());
        //System.out.println(id+name+contactDetails+payForHour);

        Guide guide=new Guide(
                id,name,contactDetails,experience_Year,payForHour
        );
        boolean isUpdate=GuideModel.UpdateGuide(guide);
        if(isUpdate){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"Guide Update",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Guide not Update",ButtonType.OK).show();
        }
    }

    public void clickOnAction(MouseEvent mouseEvent) {
        Guide selectedItem = tblGuide.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getGuideId());
            txtName.setText(selectedItem.getName());
            txtContactDetails.setText(selectedItem.getContactDetails());
            txtExperience_Year.setText(selectedItem.getExperienceLevel());
            txtPayForHour.setText(String.valueOf(selectedItem.getPayFor()));

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

}
