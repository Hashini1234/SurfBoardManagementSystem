package lk.ijse.surfboardmanagementsystem.model;

import lk.ijse.surfboardmanagementsystem.dto.Item;
import lk.ijse.surfboardmanagementsystem.dto.Supplier;
import lk.ijse.surfboardmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemMOdel {
    public static boolean SaveItem(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into Item values(?,?,?,?,?)", item.getItemId(),item.getName(),item.getType(),item.getConditions(),item.getAvailabilityStatus());
    }

    public static boolean UpdateItem(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update Item set name = ?,type = ?,conditions = ?,availability_status = ?,where  = item_id?" , item.getName(),item.getType(),item.getConditions(),item.getAvailabilityStatus());
    }

    public static ArrayList<Item> getall() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from Item");
        ArrayList<Item> items = new ArrayList<>();
        while (rs.next()) {
            Item item = new Item(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)

            );
            items.add(item);
        }
        return items;
    }

    public static boolean DeleteItem(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from Item where Item_id = ? ",id);
    }

    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select Item_id from Item order by Item_id DESC limit 1");
        char tableCharactor ='I';
        if(rs.next()){
            String lastId =rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString =String.format("I%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharactor+"001";
    }
}
