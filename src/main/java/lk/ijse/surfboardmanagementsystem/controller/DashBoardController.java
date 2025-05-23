package lk.ijse.surfboardmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public  class DashBoardController implements Initializable {

    public AnchorPane ancDash;
    public Label lblDash;
    public AnchorPane ancPanal;

    public void btnBeach_LocationOnAction(ActionEvent actionEvent) {
        nevigateTo("/View/BeachLocation.fxml");
    }

    public void btnBillOnAction(ActionEvent actionEvent) {
        nevigateTo("/View/Bill.fxml");
    }

    public void btnGuideOnAction(ActionEvent actionEvent) {
        nevigateTo("/View/Guide.fxml");
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
    }

    public void btnSessionOnAction(ActionEvent actionEvent) {
        nevigateTo("/View/Session.fxml");
    }

    public void btnItemOnAction(ActionEvent actionEvent) {
        nevigateTo("/View/Item.fxml");
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) {
        nevigateTo("/View/Supplier.fxml");
    }

    public void btnSurf_BoardOnAction(ActionEvent actionEvent) {
        nevigateTo("/View/SurfBoard.fxml");
    }

    public void btnTouristOnAction(ActionEvent actionEvent) {
        nevigateTo("/View/Tourist.fxml");
    }

    public void btnTransportOnAction(ActionEvent actionEvent) {
        nevigateTo("/View/Transport.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nevigateTo("/View/BeachLocation.fxml");
    }
        private void nevigateTo (String s){
            try {
                ancPanal.getChildren().clear();
                AnchorPane pane = FXMLLoader.load(getClass().getResource(s));

                pane.prefWidthProperty().bind(ancPanal.widthProperty());
                pane.prefHeightProperty().bind(ancPanal.heightProperty());

                ancPanal.getChildren().add(pane);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Page Not Found!").show();
                e.printStackTrace();

            }
        }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
    }
}

