package lk.ijse.surfboardmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.surfboardmanagementsystem.Applnitializer;
import lk.ijse.surfboardmanagementsystem.dto.User;
import lk.ijse.surfboardmanagementsystem.model.LoggingPageModel;

import java.io.IOException;

public class LoggingPageController {

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblError;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    private LoggingPageModel loggingPageModel = new LoggingPageModel();


    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        boolean userExists = loggingPageModel.checkUser(userName);

        if(!userExists){

            return;
        }

        User user = loggingPageModel.getUser(userName);

        if(!password.equals(user.getPassword())){
            return;
        }


        FXMLLoader fxmlLoader = new FXMLLoader(Applnitializer.class.getResource("/View/DashBoard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage newStage = new Stage();
        newStage.setTitle("Sign Up");
        newStage.setScene(scene);
        newStage.show();

        Stage currentStage = (Stage) btnLogin.getScene().getWindow();
        currentStage.close();

    }

    @FXML
    void signUpOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Applnitializer.class.getResource("/View/signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage newStage = new Stage();
        newStage.setTitle("Sign Up");
        newStage.setScene(scene);
        newStage.show();

        Stage currentStage = (Stage) btnLogin.getScene().getWindow();
        currentStage.close();
    }


}
