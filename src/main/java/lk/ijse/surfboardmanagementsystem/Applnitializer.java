package lk.ijse.surfboardmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Applnitializer extends Application {



    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Applnitializer.class.getResource("/View/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
