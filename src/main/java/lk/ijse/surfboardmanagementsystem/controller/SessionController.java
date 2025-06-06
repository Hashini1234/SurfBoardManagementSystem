package lk.ijse.surfboardmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.surfboardmanagementsystem.dto.Guide;
import lk.ijse.surfboardmanagementsystem.dto.Session;
import lk.ijse.surfboardmanagementsystem.model.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Properties;

public class SessionController {

    private final SessionModel sessionModel = new SessionModel();
    private final GuideModel guideModel = new GuideModel();
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
    public ComboBox<String> cmbSurfBoardId;

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

        cmbGuideId.setOnAction(event -> {
            String selectedGuideId = cmbGuideId.getSelectionModel().getSelectedItem();
            if (selectedGuideId != null) {
                handleGuideSelection(selectedGuideId);
            }
        });


        cmbMethod.setItems(FXCollections.observableArrayList("card", "cash"));
        cmbSurfBoardId.setItems(SurfBoardModel.getallSurfBoardId());
        loadTable();
    }

    private void handleGuideSelection(String selectedGuideId) {
       updateTotalAmount();
    }

    private void updateTotalAmount() {
        String selectedGuideId = cmbGuideId.getSelectionModel().getSelectedItem();
        String durationText = txtDuration.getText();

        if (selectedGuideId == null || durationText.isEmpty()) {
            lblTotalAmount.setText("0.00");
            return;
        }

        try {
            Guide guide = guideModel.getGuideById(selectedGuideId);
            if (guide != null) {
                double duration = Double.parseDouble(durationText);
                double amount = guide.getPayFor() * duration;
                lblTotalAmount.setText(String.format("%.2f", amount));
            }
        } catch (NumberFormatException e) {
            // Optional: handle invalid number format
            lblTotalAmount.setText("0.00");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error calculating amount");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
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
            System.out.println("Payment ID: " + paymentId);
            Date date = Date.valueOf(dateSession.getValue());
            Time time = Time.valueOf(txtTime.getText());
            String duration = txtDuration.getText();
            String touristId = cmbTouristId.getValue();
            String beachId = cmbBeachId.getValue();
            String guideId = cmbGuideId.getValue();
            String status = cmbStatus.getValue();
            String method = cmbMethod.getValue();
            String amount = String.valueOf(Double.parseDouble(lblTotalAmount.getText()));
            String surfboardId = cmbSurfBoardId.getValue();

            Session dto = new Session(sessionId, date, time, duration, touristId, beachId, guideId, status, paymentId, method, amount, surfboardId);

            boolean isSaved = sessionModel.saveSession(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Session Placed Successfully!").show();
                sendEmail(TouristModel.getEmail(touristId),time,date);
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
        cmbSurfBoardId.getSelectionModel().clearSelection();
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

    private void sendEmail(Object email, Time time, Date date)  {

        new Thread(() -> {

            String senderEmail = "punchihewadevindi@gmail.com";
            String senderPassword = "ttdeuwznuhfslwdg";
            String subject = "Your Order Alert";
            String body = "Dear Customer,\n" +
                    "This is to inform you that your order has been placed successfully. We will contact you shortly to confirm the details.\n" +
                    "\n price: " + time + "\n" +
                    "\n supplement Name: " + date + "\n" +
                    "Thank you for choosing us.\n" +
                    "Best regards,\n" +
                    "Gym Management System";
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            javax.mail.Session session = javax.mail.Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(senderEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse((String) email));
                message.setSubject(subject);

                Multipart multipart = new MimeMultipart();

                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(body);
                multipart.addBodyPart(messageBodyPart);

                MimeBodyPart attachmentPart = new MimeBodyPart();

                message.setContent(multipart);

                Transport.send(message);

                System.out.println("Email sent successfully with the QR code attachment.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();



    }

}
