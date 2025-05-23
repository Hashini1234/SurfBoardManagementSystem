package lk.ijse.surfboardmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.surfboardmanagementsystem.dto.Guide;
import lk.ijse.surfboardmanagementsystem.model.GuideModel;
import java.sql.SQLException;
import java.util.ArrayList;


public class GuidController  {
    private final GuideModel guideModel =new GuideModel();
    public TableColumn clmGuideid;
    public Label lblId;

    @FXML
    private TableColumn<?, ?> clmContact;

    @FXML
    private TableColumn<?, ?> clmExperience;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmPay;

    @FXML
    private TableView<Guide> tblGuide;

    @FXML
    private TextField txtContactDetails;

    @FXML
    private TextField txtExperience_Year;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPayForHour;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        SetNextId();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<Guide> guides=GuideModel.getall();
        ObservableList<Guide> observableList= FXCollections.observableArrayList();
        for (Guide guide:guides) {
            observableList.add(guide);
        }
        tblGuide.setItems(observableList);
    }



    private void SetNextId() throws SQLException, ClassNotFoundException {
        String nextId = guideModel.getNextId();
        lblId.setText(nextId);


    }

    private void setcellvaluefactory() {
        clmGuideid.setCellValueFactory(new PropertyValueFactory<>("guideId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contactDetails"));
        clmExperience.setCellValueFactory(new PropertyValueFactory<>("experienceLevel"));
        clmPay.setCellValueFactory(new PropertyValueFactory<>("payFor"));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id=lblId.getText();
        boolean isDelete=GuideModel.DeleteGuide(id);
        if(isDelete){
            loadtable();
            SetNextId();
            new Alert(Alert.AlertType.INFORMATION,"Guide Delete",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Guide not Delete",ButtonType.OK).show();
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
    String id=lblId.getText();
    String name=txtName.getText();
    String contactDetails=txtContactDetails.getText();
    String experience_Year=txtExperience_Year.getText();
    Double payForHour= Double.valueOf(txtPayForHour.getText());
        //System.out.println(id+name+contactDetails+payForHour);

     Guide guide=new Guide(
             id,name,contactDetails,experience_Year,payForHour
     );
     boolean isSave=GuideModel.SaveGuide(guide);
     if(isSave){
         loadtable();
         SetNextId();
         new Alert(Alert.AlertType.INFORMATION,"Guide Saved",ButtonType.OK).show();
     }else {
         new Alert(Alert.AlertType.ERROR,"Guide not Save",ButtonType.OK).show();
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
}



