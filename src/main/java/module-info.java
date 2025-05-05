module lk.ijse.surfboardmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens lk.ijse.surfboardmanagementsystem to javafx.fxml;
    exports lk.ijse.surfboardmanagementsystem.controller;
}
