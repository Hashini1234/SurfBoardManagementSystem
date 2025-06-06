package lk.ijse.surfboardmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.surfboardmanagementsystem.dto.Tourist;
import lk.ijse.surfboardmanagementsystem.model.SessionModel;
import lk.ijse.surfboardmanagementsystem.model.TouristModel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

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
    public TextField txtEmail;
    public TableColumn colEmail;

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
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    public void btnGenerateOnAction(ActionEvent actionEvent) {
        // Generate report functionality
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=lblTouristid.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String contactDetails=txtContactDetails.getText();
        String email=txtEmail.getText();

        Tourist tourist=new Tourist(
                id,name,address,contactDetails,email
        );
        boolean isSave=TouristModel.SaveTourist(tourist);
        if(isSave){
            // Send welcome email to tourist
            sendWelcomeEmail(email, name, id);

            loadtable();
            SetNextId();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION,"Tourist Saved Successfully!",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Failed to Save Tourist",ButtonType.OK).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=lblTouristid.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String contactDetails=txtContactDetails.getText();
        String email=txtEmail.getText();

        Tourist tourist=new Tourist(
                id,name,address,contactDetails,email
        );
        boolean isUpdate=TouristModel.UpdateTourist(tourist);
        if(isUpdate){
            // Send update confirmation email
            sendUpdateEmail(email, name, id);

            loadtable();
            SetNextId();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION,"Tourist Updated Successfully!",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Failed to Update Tourist",ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=lblTouristid.getText();
        String email=txtEmail.getText();
        String name=txtName.getText();

        boolean isDelete=TouristModel.DeleteTourist(id);
        if(isDelete){
            // Send deletion confirmation email
            sendDeletionEmail(email, name, id);

            loadtable();
            SetNextId();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION,"Tourist Deleted Successfully!",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Failed to Delete Tourist",ButtonType.OK).show();
        }
    }

    public void clickOnAction(MouseEvent mouseEvent) {
        Tourist selectedItem = tblTourist.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblTouristid.setText(selectedItem.getTouristId());
            txtName.setText(selectedItem.getName());
            txtAddress.setText(selectedItem.getAddress());
            txtContactDetails.setText(selectedItem.getContactDetails());
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        clearFields();
        try {
            SetNextId();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtName.clear();
        txtAddress.clear();
        txtContactDetails.clear();
        txtEmail.clear();
    }
    private void sendWelcomeEmail(String email, String name, String touristId) {
        String subject = "Welcome to Surf Board Management System!";
        String body = "Dear " + name + ",\n\n" +
                "Welcome to our Surf Board Management System!\n\n" +
                "Your registration has been completed successfully.\n" +
                "Tourist ID: " + touristId + "\n" +
                "Name: " + name + "\n\n" +
                "We're excited to have you join our surfing community. " +
                "You can now book surf boards and enjoy our services.\n\n" +
                "For any inquiries, please contact us.\n\n" +
                "Best regards,\n" +
                "Surf Board Management Team";

        sendEmail(email, subject, body);
    }

    private void sendUpdateEmail(String email, String name, String touristId) {
        String subject = "Profile Updated - Surf Board Management System";
        String body = "Dear " + name + ",\n\n" +
                "Your profile has been successfully updated in our system.\n\n" +
                "Tourist ID: " + touristId + "\n" +
                "Updated Name: " + name + "\n\n" +
                "If you did not make this change, please contact us immediately.\n\n" +
                "Thank you for using our services.\n\n" +
                "Best regards,\n" +
                "Surf Board Management Team";

        sendEmail(email, subject, body);
    }

    private void sendDeletionEmail(String email, String name, String touristId) {
        String subject = "Account Removed - Surf Board Management System";
        String body = "Dear " + name + ",\n\n" +
                "Your account has been removed from our Surf Board Management System.\n\n" +
                "Tourist ID: " + touristId + "\n" +
                "Name: " + name + "\n\n" +
                "If this was done in error, please contact us to restore your account.\n\n" +
                "Thank you for being part of our surfing community.\n\n" +
                "Best regards,\n" +
                "Surf Board Management Team";

        sendEmail(email, subject, body);
    }

    // Generic email sending method
    private static void sendEmail(String recipientEmail, String subject, String body) {
        new Thread(() -> {
            // Email configuration - Update these with your email credentials
            String senderEmail = "hashinidevindi558@gmail.com";
            String senderPassword = "erwrnwqkpzskozwf"; // Use App Password for Gmail

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(senderEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                message.setSubject(subject);

                Multipart multipart = new MimeMultipart();

                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(body);
                multipart.addBodyPart(messageBodyPart);

                message.setContent(multipart);

                Transport.send(message);

                System.out.println("Email sent successfully to: " + recipientEmail);
            } catch (Exception e) {
                System.err.println("Failed to send email: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }
}