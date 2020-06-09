package sample;

import Behavioral.Visitor.Shape;
import Behavioral.Visitor.XMLExportVisitor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Main extends Application {
    Connection dbConnect;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("user.fxml"));
        primaryStage.setTitle("Registration");
        primaryStage.setScene(new Scene(root, 640, 361));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
