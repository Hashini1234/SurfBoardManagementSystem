module lk.ijse.surfboardmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;

    exports lk.ijse.surfboardmanagementsystem;
    opens lk.ijse.surfboardmanagementsystem to javafx.fxml;

    opens lk.ijse.surfboardmanagementsystem.controller to javafx.fxml;
    exports lk.ijse.surfboardmanagementsystem.controller;
    opens lk.ijse.surfboardmanagementsystem.dto;
    opens lk.ijse.surfboardmanagementsystem.model;
}
