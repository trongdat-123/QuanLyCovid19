package nhom13.covid.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import nhom13.covid.DatabaseConnection;
import nhom13.covid.Model.KhaiBaoYTe;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class KhaiBaoYteThongke implements Initializable {
    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.getConnection();
        ArrayList<String> trieuChung = new ArrayList();
        int khongBieuhienCount = 0;
        int sotCount = 0;
        int hoCount = 0;
        int khoThoCount = 0;
        int trieuChungkhacCount = 0;
        String sql = "select count(*) from Thongtinkhaibaoyte where Trieuchung is null";

        try{
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()){
                khongBieuhienCount += rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql1 = "select Trieuchung from Thongtinkhaibaoyte where Trieuchung is not null";
        try{
            Statement ps = conn.createStatement();
            ResultSet rs1 = ps.executeQuery(sql1);
            while (rs1.next()){
                trieuChung.add(rs1.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < trieuChung.size(); i++) {
            if(trieuChung.get(i).toLowerCase().contains("sốt")){
                sotCount++;
            }
            if(trieuChung.get(i).toLowerCase().contains("ho")){
                hoCount++;
            }
            if(trieuChung.get(i).toLowerCase().contains("khó thở")){
                khoThoCount++;
            }
            if(trieuChung.get(i).toLowerCase().contains("triệu chứng khác")){
                trieuChungkhacCount++;
            }

        }

        PieChart.Data sot = new PieChart.Data("Sốt", sotCount);
        PieChart.Data ho = new PieChart.Data("Ho", hoCount);
        PieChart.Data khoTho = new PieChart.Data("Khó thở", khoThoCount);
        PieChart.Data trieuChungKhac = new PieChart.Data("Triệu chứng khác", trieuChungkhacCount);
        PieChart.Data khongBieuhien = new PieChart.Data("Không có biểu hiện", khongBieuhienCount);

        pieChart.getData().clear();
        pieChart.getData().addAll(sot, ho, khoTho, trieuChungKhac, khongBieuhien);

        for(PieChart.Data data: pieChart.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Triệu chứng");
                alert.setContentText(data.getName() + ": " + Integer.valueOf((int) data.getPieValue()) +" người");
                alert.showAndWait();
            });
        }
    }
}
