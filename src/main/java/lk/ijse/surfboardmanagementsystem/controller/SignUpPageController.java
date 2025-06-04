package lk.ijse.surfboardmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.surfboardmanagementsystem.Applnitializer;
import lk.ijse.surfboardmanagementsystem.dto.User;
import lk.ijse.surfboardmanagementsystem.model.SignUpPageModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpPageController implements Initializable {

    @FXML
    private Button btnSignUp;

    @FXML
    private ChoiceBox<String> choiceRole;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    private SignUpPageModel signUpPageModel = new  SignUpPageModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            txtId.setText(signUpPageModel.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        choiceRole.getItems().addAll("Admin", "User", "Manager");
        choiceRole.setValue("User");
    }

    @FXML
    void goToLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Applnitializer.class.getResource("/View/Logging.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage newStage = new Stage();
            newStage.setTitle("Login");
            newStage.setScene(scene);
            newStage.show();

            Stage currentStage = (Stage) btnSignUp.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signUpOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        String role = choiceRole.getValue();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || role == null) {
            showAlert("All fields are required.");
            return;
        }
//
//        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
//            showAlert("Invalid email format.");
//            return;
//        }

        if (!password.equals(confirmPassword)) {
            showAlert("Passwords do not match.");
            return;
        }

        if (password.length() < 6) {
            showAlert("Password must be at least 6 characters.");
            return;
        }


        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        try {
            boolean isSaved = signUpPageModel.saveUser(user);
            if (isSaved) {
                showAlert("User registered successfully.");
                goToLogin(event);
            } else {
                showAlert("Failed to register user. Try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Something went wrong: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Signup Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
