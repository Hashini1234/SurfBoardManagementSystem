package lk.ijse.surfboardmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.surfboardmanagementsystem.User;

import java.io.IOException;


        public class LoggingPageController {
            public TextField txtUsername;
            public PasswordField txtPassword;
            public Button btnLogin;
            public Label lblError;
            private User user = new User("hashi", "2003");

            private void showAlert (String message, Alert.AlertType type){
                Alert alert = new Alert(type);
                alert.setContentText(message);
                alert.show();
            }

            public void loginOnAction(ActionEvent actionEvent) {
                String username = txtUsername.getText();
                String password = txtPassword.getText();

                if (User.verifyLogin(username, password)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashBoard.fxml"));
                        Parent dashboardRoot = loader.load();
                        Stage stage = (Stage) txtUsername.getScene().getWindow();
                        Scene scene = new Scene(dashboardRoot);
                        stage.setScene(scene);
                        stage.setTitle("Dashboard");
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    showAlert("Invalid username or password.", Alert.AlertType.ERROR);
                }
            }
        }


