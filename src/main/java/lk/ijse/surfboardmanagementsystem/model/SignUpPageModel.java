package lk.ijse.surfboardmanagementsystem.model;

import lk.ijse.surfboardmanagementsystem.dbConnection.DBConnection;
import lk.ijse.surfboardmanagementsystem.dto.User;
import lk.ijse.surfboardmanagementsystem.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpPageModel {

    public String getId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT id FROM users ORDER BY id DESC LIMIT 1");
        char tableCharactor = 'U';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format("%c%03d", tableCharactor, nextIdNumber);
        }
        return tableCharactor + "001";
    }

    public boolean saveUser(User user) {
        String sql = "INSERT INTO users (id, name, email, role, password) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, user.getId());
            pstm.setString(2, user.getName());
            pstm.setString(3, user.getEmail());
            pstm.setString(4, user.getRole());
            pstm.setString(5, user.getPassword()); // Ideally hash before saving

            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
