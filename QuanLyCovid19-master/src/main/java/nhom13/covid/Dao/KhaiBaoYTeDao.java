package nhom13.covid.Dao;

import nhom13.covid.DatabaseConnection;
import nhom13.covid.Model.KhaiBaoYTe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KhaiBaoYTeDao implements Dao<KhaiBaoYTe>{
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection conn = databaseConnection.getConnection();
    List<KhaiBaoYTe> khaiBaoYTeList;
    public KhaiBaoYTeDao (){
        khaiBaoYTeList = new ArrayList<KhaiBaoYTe>();
        String sql = "select * from Thongtinkhaibaoyte";
        try{
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()){
                KhaiBaoYTe khaiBaoYTe = new KhaiBaoYTe(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9)) ;
                khaiBaoYTeList.add(khaiBaoYTe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<KhaiBaoYTe> getAll() {

        return khaiBaoYTeList;
    }

    @Override
    public void update(KhaiBaoYTe khaiBaoYTe) {

    }

    @Override
    public void delete(KhaiBaoYTe khaiBaoYTe) {

    }

}
