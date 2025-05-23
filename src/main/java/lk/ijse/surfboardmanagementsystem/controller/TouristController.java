package lk.ijse.surfboardmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.surfboardmanagementsystem.dto.Tourist;
import lk.ijse.surfboardmanagementsystem.model.TouristModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class TouristController {
    public Label lblTouristid;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContactDetails;
    public TableView <Tourist> tblTourist;
    public TableColumn<?,?> colTouristId;
    public TableColumn <?,?> colName;
    public TableColumn<?,?>  colAddress;
    public TableColumn <?,?> colContactDetails;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        SetNextId();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<Tourist> tourists= TouristModel.getall();
        ObservableList<Tourist> observableList= FXCollections.observableArrayList();
        for (Tourist tourist:tourists) {
            observableList.add(tourist);
        }
        tblTourist.setItems(observableList);
    }

    private void SetNextId() throws SQLException, ClassNotFoundException {
        String nextId = TouristModel.getNextId();
        lblTouristid.setText(nextId);

    }

    private void setcellvaluefactory() {
        colTouristId.setCellValueFactory(new PropertyValueFactory<>("touristId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactDetails.setCellValueFactory(new PropertyValueFactory<>("contactDetails"));

    }

    public void btnGenerateOnAction(ActionEvent actionEvent) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=lblTouristid.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String contactDetails=txtContactDetails.getText();

        Tourist tourist=new Tourist(
                id,name,address,contactDetails
        );
        boolean isSave=TouristModel.SaveTourist(tourist);
        if(isSave){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"Tourist Saved",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Tourist not Save",ButtonType.OK).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=lblTouristid.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String contactDetails=txtContactDetails.getText();

        Tourist tourist=new Tourist(
                id,name,address,contactDetails
        );
        boolean isUpdate=TouristModel.UpdateTourist(tourist);
        if(isUpdate){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"Tourist Update",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Tourist not Update",ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=lblTouristid.getText();
        boolean isDelete=TouristModel.DeleteTourist(id);
        if(isDelete){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"Tourist Delete",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Tourist not Delete",ButtonType.OK).show();
        }


    }
    public void clickOnAction(MouseEvent mouseEvent) {
        Tourist selectedItem =  tblTourist.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblTouristid.setText(selectedItem.getTouristId());
            txtName.setText(selectedItem.getName());
            txtAddress.setText(selectedItem.getAddress());
            txtContactDetails.setText(selectedItem.getContactDetails());

//            btnSave.setDisable(true);
//
//            btnUpdate.setDisable(false);
//            btnDelete.setDisable(false);
        }
    }
    public void btnResetOnAction(ActionEvent actionEvent) {
    }
}
