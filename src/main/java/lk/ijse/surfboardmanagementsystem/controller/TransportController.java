package lk.ijse.surfboardmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.surfboardmanagementsystem.dto.Guide;
import lk.ijse.surfboardmanagementsystem.dto.Transport;
import lk.ijse.surfboardmanagementsystem.model.GuideModel;
import lk.ijse.surfboardmanagementsystem.model.TouristModel;
import lk.ijse.surfboardmanagementsystem.model.TransportModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransportController {
    public TextField txtTransportId;
    public TextField txtLocation;
    public TextField txtCost;
    public TableView<Transport> tblTransport;
    public TableColumn <?,?>colTransportId;
    public TableColumn <?,?>colLocation;
    public TableColumn <?,?>colCost;
    public TableColumn <?,?>colTouristId;
    public TableColumn <?,?>colVehicleType;
    public ComboBox <String>cmbTourist;

    public ComboBox<String> cmbVehicle;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        SetNextId();
        cmbVehicle.setItems(FXCollections.observableArrayList("Car ","Van ","Bus"));
        cmbTourist.setItems(TouristModel.getAllTourist());
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<Transport> transports= TransportModel.getall();
        ObservableList<Transport> observableList= FXCollections.observableArrayList();
        for (Transport transport:transports) {
            observableList.add(transport);
        }
        tblTransport.setItems(observableList);
    }

    private void SetNextId() throws SQLException, ClassNotFoundException {
        String nextId = TransportModel.getNextId();
        txtTransportId.setText(nextId);
    }

    private void setcellvaluefactory() {
        colTransportId.setCellValueFactory(new PropertyValueFactory<>("transportId"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colTouristId.setCellValueFactory(new PropertyValueFactory<>("touristId"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
    }

    public void btnGenerateOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=txtTransportId.getText();
        String location=txtLocation.getText();
        String cost=txtCost.getText();
        String tourist_id=cmbTourist.getValue();
        String vehicle_type=cmbVehicle.getValue();


        Transport transport=new Transport(
                id,location,cost,tourist_id,vehicle_type
        );
        boolean isSave=TransportModel.SaveTransport(transport);
        if(isSave){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"Transport Saved",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Transport not Save",ButtonType.OK).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=txtTransportId.getText();
        String location=txtLocation.getText();
        String cost=txtCost.getText();
        String tourist_id=cmbTourist.getValue();
        String vehicle_type=cmbVehicle.getValue();


        Transport transport=new Transport(
                id,location,cost,tourist_id,vehicle_type
        );
        boolean isUpdate=TransportModel.UpdateTransport(transport);
        if(isUpdate){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"Transport Updated",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Transport not Updated",ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=txtTransportId.getText();
        boolean isDelete=TransportModel.DeleteTransport(id);
        if(isDelete){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"Transport Delete", ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Transport not Delete",ButtonType.OK).show();
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
    }
    public void clickOnAction(MouseEvent mouseEvent) {
        Transport selectedItem = tblTransport.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtTransportId.setText(selectedItem.getTransportId());
            txtLocation.setText(selectedItem.getLocation());
            txtCost.setText(selectedItem.getCost());
            cmbTourist.setValue(selectedItem.getTouristId());
            cmbVehicle.setValue(selectedItem.getVehicleType());

           /* // save button disable
            btnSave.setDisable(true);

            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);*/
        }
    }
}
