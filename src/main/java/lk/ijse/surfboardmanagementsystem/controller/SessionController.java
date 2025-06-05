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
    public ComboBox<String> cmbBeachId;
    public ComboBox<String> cmbGuideId;
    public ComboBox<String> cmbStatus;
    public ComboBox<String> cmbMethod;
    public Label lblTotalAmount;
    public TextField txtPaidAmount;
    public Button btnCheckBalance;
    public Label lblChange;
    public Label lblPaymentId;
    public TextField txtSurfboardId;

    @FXML
    private Label lblSessionId;

    @FXML
    private DatePicker dateSession;

    @FXML
    private TextField txtTime;

    @FXML
    private TextField txtDuration;

    @FXML
    private TableView<Session> tblSessions;

    @FXML
    private TableColumn<?, ?> colSessionId, colDate, colTime, colDuration, colTouristId, colBeachId, colGuideId, colStatus;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setNextIds();
        cmbStatus.setItems(FXCollections.observableArrayList("Scheduled", "Complete", "Cancelled"));
        cmbBeachId.setItems(BeachLocationModel.getallBeachId());
        cmbTouristId.setItems(TouristModel.getAllTourist());
        cmbGuideId.setItems(GuideModel.getallGuide());
        cmbMethod.setItems(FXCollections.observableArrayList("card", "cash"));
        loadTable();
    }

    private void loadTable() throws SQLException, ClassNotFoundException {
        ArrayList<Session> sessions = sessionModel.getAll(); // you need to implement getAll() in the new SessionModel if missing
        ObservableList<Session> list = FXCollections.observableArrayList(sessions);
        tblSessions.setItems(list);
    }

    private void setNextIds() throws SQLException, ClassNotFoundException {
        lblSessionId.setText(sessionModel.generateNextSessionId());
        lblPaymentId.setText(sessionModel.generateNextPaymentId());
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
    void btnPlaceSession(ActionEvent actionEvent) {
        try {
            String sessionId = lblSessionId.getText();
            String paymentId = lblPaymentId.getText();
            Date date = Date.valueOf(dateSession.getValue());
            Time time = Time.valueOf(txtTime.getText());
            String duration = txtDuration.getText();
            String touristId = cmbTouristId.getValue();
            String beachId = cmbBeachId.getValue();
            String guideId = cmbGuideId.getValue();
            String status = cmbStatus.getValue();
            String method = cmbMethod.getValue();
            String amount = String.valueOf(Double.parseDouble(lblTotalAmount.getText()));
            String surfboardId = txtSurfboardId.getText();

            Session dto = new Session(sessionId, date, time, duration, touristId, beachId, guideId, status, paymentId, method, amount, surfboardId);

            boolean isSaved = sessionModel.saveSession(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Session Placed Successfully!").show();
                loadTable();
                setNextIds();
                resetFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Place Session!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnCheckBalanceOnAction(ActionEvent event) {
        try {
            double total = Double.parseDouble(lblTotalAmount.getText());
            double paid = Double.parseDouble(txtPaidAmount.getText());
            double change = paid - total;
            lblChange.setText(String.format("%.2f", change));
        } catch (NumberFormatException e) {
            lblChange.setText("Invalid input");
        }
    }

    private void resetFields() {
        txtTime.clear();
        txtDuration.clear();
        txtPaidAmount.clear();
        lblChange.setText("");
        cmbTouristId.getSelectionModel().clearSelection();
        cmbBeachId.getSelectionModel().clearSelection();
        cmbGuideId.getSelectionModel().clearSelection();
        cmbStatus.getSelectionModel().clearSelection();
        cmbMethod.getSelectionModel().clearSelection();
        txtSurfboardId.clear();
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

}
