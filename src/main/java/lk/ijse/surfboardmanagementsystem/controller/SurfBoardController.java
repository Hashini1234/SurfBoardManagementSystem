package lk.ijse.surfboardmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.surfboardmanagementsystem.dto.Guide;
import lk.ijse.surfboardmanagementsystem.dto.SurfBoard;
import lk.ijse.surfboardmanagementsystem.model.GuideModel;
import lk.ijse.surfboardmanagementsystem.model.SurfBoardModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class SurfBoardController {
    
    public TextField txtSurfboardId;
    public TextField txtBrand;
    public TextField txtCondition;
    public TableView tblSurfboards;
    public TableColumn colSurfboardId;
    public TableColumn colBrand;
    public TableColumn colCondition;
    public Label lblSurfBoard;
    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        SetNextId();
        loadtable();
    }

    private void loadtable() {
        ArrayList<SurfBoard> surfboards = SurfBoardModel.getall();
        ObservableList<SurfBoard> observableList= FXCollections.observableArrayList();
        for (SurfBoard surfboard:surfboards) {
            observableList.add(surfboard);
        }
        tblSurfboards.setItems(observableList);
    }

    private void SetNextId() {
        String nextId = SurfBoardModel.getNextId();
        lblSurfBoard.setText(nextId);

    }

    private void setcellvaluefactory() {
        colSurfboardId.setCellValueFactory(new PropertyValueFactory<>("surfboardId"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colCondition.setCellValueFactory(new PropertyValueFactory<>("conditions"));
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id=lblSurfBoard.getText();
        String brand=txtBrand.getText();
        String conditions=txtCondition.getText();


        SurfBoard SurfBoard=new SurfBoard(
                id,brand,conditions
        );
        boolean isSave=SurfBoardModel.SaveSurfBoard(SurfBoard);
        if(isSave){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"SurfBoard Saved",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"SurfBoard not Save",ButtonType.OK).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id=lblSurfBoard.getText();
        String brand=txtBrand.getText();
        String conditions=txtCondition.getText();


        SurfBoard SurfBoard=new SurfBoard(
                id,brand,conditions
        );
        boolean isUpdate=SurfBoardModel.UpdateSurfBoard(SurfBoard);
        if(isUpdate){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"SurfBoard Update",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"SurfBoard not Update",ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id=lblSurfBoard.getText();
        boolean isDelete=SurfBoardModel.DeleteSurfBoard(id);
        if(isDelete){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"SurfBoard Delete",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"SurfBoard not Delete",ButtonType.OK).show();
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
    }

    public void btnGenerateOnAction(ActionEvent actionEvent) {
    }

    public void clickOnAction(MouseEvent mouseEvent) {
        SurfBoard selectedItem = tblSurfboards.getSelectionModel().getSelectedItem();

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
}
