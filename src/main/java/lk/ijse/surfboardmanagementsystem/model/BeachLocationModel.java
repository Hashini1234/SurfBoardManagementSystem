package lk.ijse.surfboardmanagementsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.surfboardmanagementsystem.dto.BeachLocation;
import lk.ijse.surfboardmanagementsystem.dto.Guide;
import lk.ijse.surfboardmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BeachLocationModel {
    public static ArrayList<BeachLocation> getall() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from Beach_Location");
        ArrayList<BeachLocation> beachLocations = new ArrayList<>();
        while (rs.next()) {
            BeachLocation beachLocation = new BeachLocation(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)

            );
            beachLocations.add(beachLocation);
        }
        return beachLocations;
    }

    public static boolean DeleteBeachLocation(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from Beach_Location where beach_id= ? ",id);
    }

    public static boolean SaveBeachLocation(BeachLocation beachLocation) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into Beach_Location values(?,?,?,?)", beachLocation.getBeachId(),beachLocation.getName(),beachLocation.getPeakSeason(),beachLocation.getMonth());

    }

    public static boolean UpdateBeachLocation(BeachLocation beachLocation) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Beach_Location SET name = ?, peak_season = ?, month = ? WHERE beach_id = ?",
                beachLocation.getName(),
                beachLocation.getPeakSeason(),
                beachLocation.getMonth(),
                beachLocation.getBeachId()
        );
    }


    public static ObservableList<String> getAllMonth() {
        return FXCollections.observableArrayList(
                "January","February"
        );
    }

    public static ObservableList<String> getallBeachId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select beach_id from Beach_Location");
        ObservableList list = FXCollections.observableArrayList();

        while (rs.next()){
            list.add(rs.getString("beach_id"));

        }
        return list;
    }

    public static ObservableList<String> getAllSeson() {
        return FXCollections.observableArrayList(
                "January","February"
        );
    }

    public static String getNextId() throws SQLException, ClassNotFoundException {
            ResultSet rs = CrudUtil.execute("SELECT beach_id FROM Beach_Location ORDER BY beach_id DESC LIMIT 1");
            char tableCharactor = 'B';
            if (rs.next()) {
                String lastId = rs.getString(1);
                String lastIdNumberString = lastId.substring(1);
                int lastIdNumber = Integer.parseInt(lastIdNumberString);
                int nextIdNumber = lastIdNumber + 1;
                return String.format("%c%03d", tableCharactor, nextIdNumber);
            }
            return tableCharactor + "001";
        }
    }


