package lk.ijse.surfboardmanagementsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.surfboardmanagementsystem.dto.Guide;
import lk.ijse.surfboardmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuideModel {


    public static boolean SaveGuide(Guide guide) throws SQLException, ClassNotFoundException {
      return CrudUtil.execute("insert into Guide values(?,?,?,?,?)", guide.getGuideId(),guide.getName(),guide.getContactDetails(),guide.getExperienceLevel(),guide.getPayFor());
    }

    public static boolean UpdateGuide(Guide guide) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update Guide set name=?,contact_details=?,experience_level=?,pay_for=? where guide_id=?" , guide.getName(),guide.getContactDetails(),guide.getExperienceLevel(),guide.getPayFor(),guide.getGuideId());
    }

    public static ArrayList<Guide> getall() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from Guide");
        ArrayList<Guide> guides = new ArrayList<>();
        while (rs.next()) {
            Guide guide = new Guide(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getDouble(5)

            );
            guides.add(guide);
        }
        return guides;
    }

    public static boolean DeleteGuide(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from Guide where guide_id= ? ",id);
    }

    public static ObservableList<String> getallGuide() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select guide_id from Guide");
        ObservableList list = FXCollections.observableArrayList();

        while (rs.next()){
            list.add(rs.getString("guide_id"));

        }
        return list;
    }



    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select guide_id from Guide order by guide_id DESC limit 1");
        char tableCharactor ='G';
        if(rs.next()){
            String lastId =rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString =String.format("G%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharactor+"001";
    }

}
