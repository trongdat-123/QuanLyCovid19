module nhom13.covid {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mssql.jdbc;


    opens nhom13.covid to javafx.fxml;
    opens nhom13.covid.Controller;
    opens nhom13.covid.Model;
    exports nhom13.covid;
}