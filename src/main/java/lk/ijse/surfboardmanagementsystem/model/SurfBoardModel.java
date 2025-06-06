package lk.ijse.surfboardmanagementsystem.model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
                    rs.getString(3),
                    rs.getString(4)

            );
            surfBoards.add(surfBoard);
        }
        return surfBoards;
    }


    public static boolean SaveSurfBoard(SurfBoard surfBoard) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into Surf_Board values(?,?,?,?)", surfBoard.getSurfboardId(),surfBoard.getBrand(),surfBoard.getConditions(),surfBoard.getStatus());
    }

    public static boolean UpdateSurfBoard(SurfBoard surfBoard) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update Surf_Board set brand=?,condition=? where surfboard_id = ?" ,surfBoard.getBrand(),surfBoard.getConditions(), surfBoard.getSurfboardId());
    }

    public static boolean DeleteSurfBoard(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from Surf_Board where surfboard_id= ? ",id);
    }
    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT surfboard_id FROM Surf_Board ORDER BY surfboard_id DESC LIMIT 1");
        char tableCharactor = 'S';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format("%c%03d", tableCharactor, nextIdNumber);
        }
        return tableCharactor + "001";
    }

    public static ObservableList<String> getallSurfBoardId() {
        try {
            ObservableList<String> surfBoardId = FXCollections.observableArrayList();
            ResultSet rs = CrudUtil.execute("SELECT surfboard_id FROM Surf_Board");
            while (rs.next()) {
                surfBoardId.add(rs.getString(1));
            }
            return surfBoardId;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}


