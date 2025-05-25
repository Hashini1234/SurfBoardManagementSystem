package lk.ijse.surfboardmanagementsystem.model;

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
        return CrudUtil.execute("update Beach_Location set name=?,peakSeason=?,month=?, where beach_id=?" ,beachLocation.getBeachId(),beachLocation.getName(),beachLocation.getPeakSeason(),beachLocation.getMonth());

    }
}
