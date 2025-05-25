package lk.ijse.surfboardmanagementsystem.model;


import lk.ijse.surfboardmanagementsystem.dto.SurfBoard;
import lk.ijse.surfboardmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SurfBoardModel {

    public static ArrayList<SurfBoard> getall() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from Surf_Board");
        ArrayList<SurfBoard> surfBoards = new ArrayList<>();
        while (rs.next()) {
            SurfBoard surfBoard = new SurfBoard(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)

            );
            surfBoards.add(surfBoard);
        }
        return surfBoards;
    }


    public static boolean SaveSurfBoard(SurfBoard surfBoard) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into Surf_Board values(?,?,?)", surfBoard.getSurfboardId(),surfBoard.getBrand(),surfBoard.getConditions());
    }

    public static boolean UpdateSurfBoard(SurfBoard surfBoard) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update Surf_Board set brand=?,condition=? where surfboard_id = ?" ,surfBoard.getBrand(),surfBoard.getConditions(), surfBoard.getSurfboardId());
    }

    public static boolean DeleteSurfBoard(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from Surf_Board where surfboard_id= ? ",id);
    }
    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select surfboard_id from Surf_Board order by surfboard_id DESC limit 1");
        char tableCharactor ='S';
        if(rs.next()){
            String lastId =rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString =String.format("S%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharactor+"001";
    }
}


