package nhom13.covid.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import nhom13.covid.Dao.Dao;
import nhom13.covid.Dao.KhaiBaoYTeDao;
import nhom13.covid.Model.KhaiBaoYTe;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class KhaiBaoYte implements Initializable {
    @FXML
    private TextField filterField;

    @FXML
    private TableView<KhaiBaoYTe> khaiBaoYTeTableView;


    @FXML
    private TableColumn<KhaiBaoYTe, String> hoTenColumn;

    @FXML
    private TableColumn<KhaiBaoYTe, String> cmtColumn;

    @FXML
    private TableColumn<KhaiBaoYTe, Integer> maNhanKhauColumn;

    @FXML
    private TableColumn<KhaiBaoYTe, String> soDTColumn;

    @FXML
    private TableColumn<KhaiBaoYTe, String> emailColumn;

    @FXML
    private TableColumn<KhaiBaoYTe, String> noiDichuyenColumn;

    @FXML
    private TableColumn<KhaiBaoYTe, String> trieuChungColumn;

    @FXML
    private TableColumn<KhaiBaoYTe, Date> thoiDiemColumn;

    @FXML
    private TableColumn<KhaiBaoYTe, String> tienSuColumn;
    private ObservableList<KhaiBaoYTe> khaiBaoYTeObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        khaiBaoYTeObservableList = FXCollections.observableArrayList();
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<KhaiBaoYTe, String>("HoVaTen"));
        cmtColumn.setCellValueFactory(new PropertyValueFactory<KhaiBaoYTe, String>("Cccd"));
        maNhanKhauColumn.setCellValueFactory(new PropertyValueFactory<KhaiBaoYTe, Integer>("MaNhanKhau"));
        soDTColumn.setCellValueFactory(new PropertyValueFactory<KhaiBaoYTe, String>("SoDt"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<KhaiBaoYTe, String>("Email"));
        noiDichuyenColumn.setCellValueFactory(new PropertyValueFactory<KhaiBaoYTe, String>("NoiDiChuyen"));
        trieuChungColumn.setCellValueFactory(new PropertyValueFactory<KhaiBaoYTe, String>("TrieuChung"));
        thoiDiemColumn.setCellValueFactory(new PropertyValueFactory<KhaiBaoYTe, Date>("TdKhaiBao"));
        tienSuColumn.setCellValueFactory(new PropertyValueFactory<KhaiBaoYTe, String>("TienSu"));
        Dao dao = new KhaiBaoYTeDao();
        for (int i = 0; i < dao.getAll().size(); i++) {

            khaiBaoYTeObservableList.add((KhaiBaoYTe) dao.getAll().get(i));
        }

        khaiBaoYTeTableView.setItems(khaiBaoYTeObservableList);

        FilteredList<KhaiBaoYTe> filteredData = new FilteredList<>(khaiBaoYTeObservableList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(khaiBaoYTe -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (khaiBaoYTe.getHoVaTen().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(khaiBaoYTe.getMaNhanKhau()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (khaiBaoYTe.getCccd().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (khaiBaoYTe.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (khaiBaoYTe.getNoiDiChuyen().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (khaiBaoYTe.getTrieuChung().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(khaiBaoYTe.getTdKhaiBao()).indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false;
            });
        });

        SortedList<KhaiBaoYTe> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(khaiBaoYTeTableView.comparatorProperty());
        khaiBaoYTeTableView.setItems(sortedData);


    }


    public void thongke(ActionEvent event) throws IOException {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/nhom13/covid/KhaiBaoYTeThongke.fxml"));
            Parent homeParent = loader.load();
            Scene scene = new Scene(homeParent);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}