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
            boolean isSessionSaved = CrudUtil.execute("INSERT INTO Session VALUES (?,?,?,?,?,?,?,?)",
                    dto.getSessionId(),
                    dto.getDate(),
                    dto.getTime(),
                    dto.getDuration(),
                    dto.getTouristId(),
                    dto.getBeachId(),
                    dto.getGuideId(),
                    dto.getStatus());

            System.out.println("Session Saved: " + isSessionSaved);

            if (!isSessionSaved) {
                con.rollback();
                return false;
            }

            System.out.println("Called Payment");

                boolean isPaymentSaved = CrudUtil.execute("INSERT INTO Payment VALUES (?,?,?,?,CURDATE())",
                        dto.getPaymentId(),
                        dto.getAmount(),
                        dto.getSessionId(),
                        dto.getMethod());

                System.out.println("Payment Saved: " + isPaymentSaved);

                if (!isPaymentSaved) {
                    con.rollback();
                    return false;
                }

                if (dto.getStatus().equals("Scheduled")) {
                    boolean isGuideUpdated = CrudUtil.execute(
                            "UPDATE Guide SET status = 'Assigned' WHERE guide_Id = ?",
                            dto.getGuideId()
                    );

                        boolean isSurfboardUpdated = CrudUtil.execute(
                                "UPDATE Surf_Board SET status = 'Assigned' WHERE surfboard_Id = ?",
                                dto.getSurfboardId()
                        );

                    if (!isGuideUpdated || !isSurfboardUpdated) {
                        con.rollback();
                        return false;
                    }
                    }
                if (dto.getStatus().equals("Complete") || dto.getStatus().equals("Cancelled")) {
                    boolean isGuideUpdated = CrudUtil.execute(
                            "UPDATE Guide SET status = 'Available' WHERE guide_Id = ?",
                            dto.getGuideId()
                    );

                        boolean isSurfboardUpdated = CrudUtil.execute(
                                "UPDATE Surf_Board SET status = 'Available' WHERE surfboard_Id = ?",
                                dto.getSurfboardId()
                        );

                    if (!isGuideUpdated || !isSurfboardUpdated) {
                        con.rollback();
                        return false;
                    }
                }
                con.commit();
                return true;

        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }
        return false;
    }


    public String generateNextSessionId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT session_Id FROM Session ORDER BY CAST(SUBSTRING(session_Id, 4) AS UNSIGNED) DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int nextId = Integer.parseInt(lastId.replace("SES", "")) + 1;
            return String.format("SES%03d", nextId);
        } else {
            return "SES001";
        }
    }

    public String generateNextPaymentId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT payment_Id FROM Payment ORDER BY CAST(SUBSTRING(payment_Id, 4) AS UNSIGNED) DESC LIMIT 1");
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
        ResultSet rs = CrudUtil.execute("SELECT * FROM Session");

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
