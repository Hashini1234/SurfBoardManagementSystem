package lk.ijse.surfboardmanagementsystem.model;

import lk.ijse.surfboardmanagementsystem.dbConnection.DBConnection;
import lk.ijse.surfboardmanagementsystem.dto.Session;
import lk.ijse.surfboardmanagementsystem.dto.Session;
import lk.ijse.surfboardmanagementsystem.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SessionModel {

    public boolean saveSession(Session dto) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        con.setAutoCommit(false);

        try {
            // 1. Insert Payment
            boolean isPaymentSaved = CrudUtil.execute(
                    "INSERT INTO payment(payment_id, method, date, amount) VALUES (?, ?, ?, ?)",
                    dto.getPaymentId(), dto.getMethod(), dto.getDate(), dto.getAmount()
            );

            if (!isPaymentSaved) {
                con.rollback();
                return false;
            }

            // 2. Insert Session
            boolean isSessionSaved = CrudUtil.execute(
                    "INSERT INTO session(session_Id, date, time, duration, tourist_Id, beach_Id, guide_Id, status, payment_Id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    dto.getSessionId(), dto.getDate(), dto.getTime(), dto.getDuration(),
                    dto.getTouristId(), dto.getBeachId(), dto.getGuideId(), dto.getStatus(), dto.getPaymentId()
            );

            if (!isSessionSaved) {
                con.rollback();
                return false;
            }

            // 3. Update Guide status to "Assigned"
            boolean isGuideUpdated = CrudUtil.execute(
                    "UPDATE guide SET status = 'Assigned' WHERE guide_Id = ?",
                    dto.getGuideId()
            );

            if (!isGuideUpdated) {
                con.rollback();
                return false;
            }

            // 4. Update Surf Board status to "Assigned"
            boolean isSurfboardUpdated = CrudUtil.execute(
                    "UPDATE surf_board SET status = 'Assigned' WHERE surfboard_Id = ?",
                    dto.getSurfboardId()
            );

            if (!isSurfboardUpdated) {
                con.rollback();
                return false;
            }

            // Commit all if successful
            con.commit();
            return true;

        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public String generateNextSessionId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT session_Id FROM session ORDER BY CAST(SUBSTRING(session_Id, 4) AS UNSIGNED) DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int nextId = Integer.parseInt(lastId.replace("SES", "")) + 1;
            return String.format("SES%03d", nextId);
        } else {
            return "SES001";
        }
    }

    public String generateNextPaymentId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT payment_Id FROM payment ORDER BY CAST(SUBSTRING(payment_Id, 4) AS UNSIGNED) DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int nextId = Integer.parseInt(lastId.replace("PAY", "")) + 1;
            return String.format("PAY%03d", nextId);
        } else {
            return "PAY001";
        }
    }

    public ArrayList<Session> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Session> sessions = new ArrayList<>();
        ResultSet rs = CrudUtil.execute("SELECT * FROM session");

        while (rs.next()) {
            Session session = new Session(
                    rs.getString("session_id"),
                    rs.getDate("date"),
                    rs.getTime("time"),
                    rs.getString("duration"),
                    rs.getString("tourist_id"),
                    rs.getString("beach_id"),
                    rs.getString("guide_id"),
                    rs.getString("status")
            );
            sessions.add(session);
        }

        return sessions;
    }

}
