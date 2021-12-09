package nhom13.covid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {


    public static void main(String args[]) {

        launch(args);

    }
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("KhaibaoYTe.fxml"));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}