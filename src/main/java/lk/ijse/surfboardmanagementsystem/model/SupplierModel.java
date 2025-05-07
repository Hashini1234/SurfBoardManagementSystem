package lk.ijse.surfboardmanagementsystem.model;
import lk.ijse.surfboardmanagementsystem.dto.Supplier;
import lk.ijse.surfboardmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public static boolean SaveSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into Supplier values(?,?,?)", supplier.getSupplierId(),supplier.getName(),supplier.getContactInfo());
    }

    public static boolean UpdateSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update Supplier set name = ?,contact_info = ? where supplier_id = ?" , supplier.getName(),supplier.getContactInfo(),supplier.getSupplierId());
    }

    public static ArrayList<Supplier> getall() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from Supplier");
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (rs.next()) {
            Supplier supplier = new Supplier(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)
            );
            suppliers.add(supplier);
        }
        return suppliers;
    }

    public static boolean DeleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from Supplier where supplier_id = ? ",id);
    }

    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select supplier_id from Supplier order by supplier_id DESC limit 1");
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
