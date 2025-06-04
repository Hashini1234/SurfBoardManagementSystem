package lk.ijse.surfboardmanagementsystem.model;

import lk.ijse.surfboardmanagementsystem.dbConnection.DBConnection;
import lk.ijse.surfboardmanagementsystem.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoggingPageModel {

    public boolean checkUser(String userName) {
        String sql = "SELECT name FROM users WHERE name = ?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, userName);

            ResultSet resultSet = pstm.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUser(String userName) {
        String sql = "SELECT * FROM users WHERE name = ?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, userName);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                        resultSet.getString("password")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
