package lk.ijse.surfboardmanagementsystem.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.surfboardmanagementsystem.dto.Tourist;
import lk.ijse.surfboardmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TouristModel {
    public static ArrayList<Tourist> getall() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from Tourist");
        ArrayList<Tourist> tourists = new ArrayList<>();
        while (rs.next()) {
            Tourist tourist = new Tourist(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            tourists.add(tourist);
        }
        return tourists;
    }

    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select tourist_id from Tourist order by tourist_id DESC limit 1");
        char tableCharactor ='T';
        if(rs.next()){
            String lastId =rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString =String.format("T%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharactor+"001";
    }

    public static boolean DeleteTourist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from Tourist where tourist_id = ? ",id);

    }

    public static boolean SaveTourist(Tourist tourist) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into Tourist values(?,?,?,?,?)", tourist.getTouristId(),tourist.getName(),tourist.getAddress(),tourist.getContactDetails(),tourist.getEmail());

    }

    public static boolean UpdateTourist(Tourist tourist) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update Tourist set name = ?,address = ? ,contact_details=?,email = ? where tourist_id = ?" , tourist.getName(),tourist.getAddress(),tourist.getContactDetails(),tourist.getTouristId());

    }

    public static ObservableList<String> getAllTourist() throws SQLException, ClassNotFoundException {
            ResultSet rs = CrudUtil.execute("select  tourist_id from Tourist");
            ObservableList list = FXCollections.observableArrayList();

            while (rs.next()){
                list.add(rs.getString("tourist_id"));

            }
            return list;
        }

    public static Object getEmail(String touristId) {
        try {
            ResultSet resultSet = CrudUtil.execute("select email from Session where tourist_id = ?", touristId);
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

