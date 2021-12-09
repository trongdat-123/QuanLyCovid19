package nhom13.covid;
import java.sql.*;

public class DatabaseConnection {
    public Connection databaselink;
    public Connection getConnection(){
        String databaseUser = "sa";
        String databasePassword = "fcbvsdat";
        String url = "jdbc:sqlserver://localhost:1433;database=Quanlycovid19";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            databaselink = DriverManager.getConnection(url, databaseUser, databasePassword);

        }catch (Exception e){
            e.printStackTrace();
        }
        return databaselink;
    }
    public void excute (String sql){
        Connection conn = getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String excuted (String sql) throws SQLException {
        Connection conn = getConnection();

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String a = rs.getString(1);
        return a;
    }
}
