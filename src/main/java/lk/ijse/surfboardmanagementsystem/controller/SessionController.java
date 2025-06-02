package lk.ijse.surfboardmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import lk.ijse.surfboardmanagementsystem.dto.Session;
import lk.ijse.surfboardmanagementsystem.model.BeachLocationModel;
import lk.ijse.surfboardmanagementsystem.model.GuideModel;
import lk.ijse.surfboardmanagementsystem.model.SessionModel;
import lk.ijse.surfboardmanagementsystem.model.TouristModel;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class SessionController {

    private final SessionModel sessionModel = new SessionModel();
    public ComboBox<String> cmbTouristId;
    public ComboBox <String>cmbBeachId;
    public ComboBox<String> cmbGuideId;
    public ComboBox<String> cmbStatus;

    @FXML
    private Label lblSessionId;

    @FXML
    private DatePicker dateSession;

    @FXML
    private TextField txtTime;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtTouristId;

    @FXML
    private TextField txtBeachId;

    @FXML
    private TextField txtGuideId;

    @FXML
    private TextField txtStatus;

    @FXML
    private TableView<Session> tblSessions;

    @FXML
    private TableColumn<?, ?> colSessionId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colTouristId;

    @FXML
    private TableColumn<?, ?> colBeachId;

    @FXML
    private TableColumn<?, ?> colGuideId;

    @FXML
    private TableColumn<?, ?> colStatus;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setNextId();
        cmbStatus.setItems(FXCollections.observableArrayList("Sceduled","Complete","Cancelled"));
        cmbBeachId.setItems(BeachLocationModel.getallBeachId());
        cmbTouristId.setItems(TouristModel.getAllTourist());
        cmbGuideId.setItems(GuideModel.getallGuide());
        loadTable();
    }

    private void loadTable() throws SQLException, ClassNotFoundException {
        ArrayList<Session> sessions = sessionModel.getAll();
        ObservableList<Session> list = FXCollections.observableArrayList(sessions);
        tblSessions.setItems(list);
    }

    private void setNextId() throws SQLException, ClassNotFoundException {
        String nextId = sessionModel.getNextId();
        lblSessionId.setText(nextId);
    }

    private void setCellValueFactory() {
        colSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colTouristId.setCellValueFactory(new PropertyValueFactory<>("touristId"));
        colBeachId.setCellValueFactory(new PropertyValueFactory<>("beachId"));
        colGuideId.setCellValueFactory(new PropertyValueFactory<>("guideId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Session session = new Session(
                lblSessionId.getText(),
                Date.valueOf(dateSession.getValue()),
                Time.valueOf(txtTime.getText()),
                txtDuration.getText(),
                cmbTouristId.getValue(),
                cmbBeachId.getValue(),
                cmbGuideId.getValue(),
                cmbStatus.getValue()
        );

        boolean isSaved = sessionModel.save(session);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Session Saved!").show();
            loadTable();
            setNextId();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save session!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Session session = new Session(
                lblSessionId.getText(),
                Date.valueOf(dateSession.getValue()),
                Time.valueOf(txtTime.getText()),
                txtDuration.getText(),
                cmbTouristId.getValue(),
                cmbBeachId.getValue(),
                cmbGuideId.getValue(),
                cmbStatus.getValue()
        );

        boolean isUpdated = sessionModel.update(session);
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Session Updated!").show();
            loadTable();
            setNextId();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update session!").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String sessionId = lblSessionId.getText();

        boolean isDeleted = sessionModel.delete(sessionId);
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Session Deleted!").show();
            loadTable();
            setNextId();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete session!").show();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        lblSessionId.setText("");
        dateSession.setValue(null);
        txtTime.clear();
        txtDuration.clear();
        cmbTouristId.getSelectionModel().clearSelection();
        cmbBeachId.getSelectionModel().clearSelection();
        cmbGuideId.getSelectionModel().clearSelection();
        cmbStatus.getSelectionModel().clearSelection();
    }

    @FXML
    void clickOnAction(MouseEvent event) {
        Session selected = tblSessions.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lblSessionId.setText(selected.getSessionId());
            dateSession.setValue(selected.getDate().toLocalDate());
            txtTime.setText(String.valueOf(selected.getTime()));
            txtDuration.setText(selected.getDuration());
            cmbTouristId.setValue(selected.getTouristId());
            cmbBeachId.setValue(selected.getBeachId());
            cmbGuideId.setValue(selected.getGuideId());
            cmbStatus.setValue(selected.getStatus());
        }
    }

    @FXML
    void btnGenerateOnAction(ActionEvent event) {
        // Optional: Generate random data or autofill for testing
    }
}
