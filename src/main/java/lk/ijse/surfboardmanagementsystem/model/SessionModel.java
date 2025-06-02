package lk.ijse.surfboardmanagementsystem.model;

import lk.ijse.surfboardmanagementsystem.dto.Session;
import lk.ijse.surfboardmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SessionModel {

    public static boolean save(Session session) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Session VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                session.getSessionId(),
                session.getDate(),
                session.getTime(),
                session.getDuration(),
                session.getTouristId(),
                session.getBeachId(),
                session.getGuideId(),
                session.getStatus()
        );
    }

    public static boolean update(Session session) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Session SET date = ?, time = ?, duration = ?, tourist_id = ?, beach_id = ?, guide_id = ?, status = ? WHERE session_id = ?",
                session.getDate(),
                session.getTime(),
                session.getDuration(),
                session.getTouristId(),
                session.getBeachId(),
                session.getGuideId(),
                session.getStatus(),
                session.getSessionId()
        );
    }

    public static boolean delete(String sessionId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Session WHERE session_id = ?", sessionId);
    }

    public static ArrayList<Session> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Session");
        ArrayList<Session> sessions = new ArrayList<>();

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

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT session_id FROM Session ORDER BY session_id DESC LIMIT 1");
        char prefix = 'S';

        if (rs.next()) {
            String lastId = rs.getString(1);
            int num = Integer.parseInt(lastId.substring(1));
            return String.format("%c%03d", prefix, num + 1);
        }

        return prefix + "001";
    }
}
