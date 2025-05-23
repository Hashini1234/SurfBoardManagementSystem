package lk.ijse.surfboardmanagementsystem.model;

import lk.ijse.surfboardmanagementsystem.dto.Transport;
import lk.ijse.surfboardmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransportModel {

    public static boolean UpdateTransport(Transport transport) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update Transport set location = ?,cost = ?,tourist_id = ?,vehicle_type = ? where transport_id =?",transport.getLocation(),transport.getCost(),transport.getTouristId(),transport.getVehicleType(),transport.getTransportId());

    }

    public static boolean SaveTransport(Transport transport) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into Transport values(?,?,?,?,?)" ,transport.getTransportId(),transport.getLocation(),transport.getCost(),transport.getTouristId(),transport.getVehicleType());

    }

    public static boolean DeleteTransport(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from Transport where transport_id = ? ",id);

    }

    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select transport_id from Transport order by transport_id DESC limit 1");
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

    public static ArrayList<Transport> getall() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from Transport");
        ArrayList<Transport> transports = new ArrayList<>();
        while (rs.next()) {
            Transport transport = new Transport(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)

            );
            transports.add(transport);
        }
        return transports;
    }
}
