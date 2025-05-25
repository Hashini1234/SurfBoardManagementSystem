package lk.ijse.surfboardmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.surfboardmanagementsystem.dto.BeachLocation;
import lk.ijse.surfboardmanagementsystem.dto.Guide;
import lk.ijse.surfboardmanagementsystem.model.BeachLocationModel;
import lk.ijse.surfboardmanagementsystem.model.GuideModel;
import lk.ijse.surfboardmanagementsystem.model.SurfBoardModel;

import java.sql.SQLException;
import java.util.ArrayList;


public class BeachLocationController {


    public Label lblId;
    public TableColumn clmbeach_location;
    public TableColumn clmlocation_name;
    public TableColumn clmpeak_season;
    public TableColumn clmmonth;
    @FXML
    private ComboBox<String> cbMonth;

    @FXML
    private ComboBox<String> cbSeason;

    @FXML
    private TableView<BeachLocation> tblBeach_Location;

    @FXML
    private TextField txtLocationName;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        SetNextId();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<BeachLocation> beachLocations = BeachLocationModel.getall();
        ObservableList<BeachLocation> observableList = FXCollections.observableArrayList();
        for (BeachLocation beachLocation : beachLocations) {
            observableList.add(beachLocation);
        }
        tblBeach_Location.setItems(observableList);

    }

    private void SetNextId() throws SQLException, ClassNotFoundException {
        String nextId = SurfBoardModel.getNextId();
        lblId.setText(nextId);

    }

    private void setcellvaluefactory() {
        clmbeach_location.setCellValueFactory(new PropertyValueFactory<>("beachId"));
        clmlocation_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmpeak_season.setCellValueFactory(new PropertyValueFactory<>("peakSeason"));
        clmmonth.setCellValueFactory(new PropertyValueFactory<>("month"));

    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = lblId.getText();
        boolean isDelete = BeachLocationModel.DeleteBeachLocation(id);
        if (isDelete) {
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION, "BeachLocation Delete", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "BeachLocation not Delete", ButtonType.OK).show();
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
        String id = lblId.getText();
        String name = txtLocationName.getText();
        String peakSeason = (String) cbSeason.getValue();
        String month = (String) cbMonth.getValue();


        BeachLocation beachLocation = new BeachLocation(
                id, name, peakSeason, month
        );
        boolean isSave = BeachLocationModel.SaveBeachLocation(beachLocation);
        if (isSave) {
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION, "BeachLocation Saved", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "BeachLocation not Save", ButtonType.OK).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = lblId.getText();
        String name = txtLocationName.getText();
        String peakSeason = (String) cbSeason.getValue();
        String month = (String) cbMonth.getValue();

        BeachLocation beachLocation = new BeachLocation(
                id, name, peakSeason, month
        );
        boolean isUpdate = BeachLocationModel.UpdateBeachLocation(beachLocation);
        if (isUpdate) {
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION, "BeachLocation Update", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "BeachLocation not Update", ButtonType.OK).show();
        }
    }

    public void clickOnAction(MouseEvent mouseEvent) {
        BeachLocation selectedItem = tblBeach_Location.getSelectionModel().getSelectedItem();


        if (selectedItem != null) {
            lblId.setText(selectedItem.getBeachId());
            txtLocationName.setText(selectedItem.getName());
           cbSeason.setValue(selectedItem.getPeakSeason());
           cbMonth.setValue(selectedItem.getMonth());

           /* // save button disable
            btnSave.setDisable(true);

            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);*/
        }

    }
}

