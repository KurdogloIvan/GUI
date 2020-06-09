package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.*;

public class DatabaseHandler extends Config {
    private Graphics2D g;
    public Connection dbConnect;
    public void getDbConnection(){
        String connectionString = "jdbc:sqlserver://WIN-BQKV1N3KBJH\\SQL;databaseName = Registration";
        String username = "tmpsLogin";
        String password = "tmpsLogin";

        System.out.println("Connecting database...");

        try {
            dbConnect = DriverManager.getConnection(connectionString, username, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            System.out.println("Failed");
        }
    }
    public void  dbUpdate(String nameText, String surnameText, String numberText, String adressText){
        try {
            String insert = "INSERT INTO dbo."+Const.USER_TABLE+"(" +Const.USER_NAME+","
            +Const.USER_SURNAME+","+Const.USER_NUMBER+","+Const.USER_ADRESS+")"+"VALUES (?,?,?,?)";
            PreparedStatement statatement = dbConnect.prepareStatement(insert);
            statatement.setString(1, nameText);
            statatement.setString(2, surnameText);
            statatement.setString(3, numberText);
            statatement.setString(4, adressText);
            int rows = statatement.executeUpdate();

            if(rows>0){
                System.out.println("Rows was inserted");
            }
            //dbConnect.close();
        } catch (SQLException e) {
            System.out.println("Failed");
            e.printStackTrace();
        }
    }

    public void select(int id,String nameText,String surnameText,String proffession,String workplace){
        String string = "Select * FROM "+ Const.SPECIALIST_TABLE;
        String result;
        System.out.println("До тру");
        try {
            dbConnect =DriverManager.getConnection("jdbc:sqlserver://WIN-BQKV1N3KBJH\\SQL;databaseName = Registration",
                                                    "tmpsLogin",
                                                    "tmpsLogin");
            System.out.println("До statement");
            Statement statement = dbConnect.createStatement();
            System.out.println("до resset");
            ResultSet resSet = statement.executeQuery(string);
            System.out.println("До while");
            while(resSet.next()){
                //System.out.println("in while");
                id = resSet.getInt(Const.SPECIALIST_ID);
                nameText = resSet.getString(Const.SPECIALIST_NAME);
                surnameText = resSet.getString(Const.SPECIALIST_SURNAME);
                proffession = resSet.getString(Const.SPECIALIST_PROFESSION);
                workplace = resSet.getString(Const.SPECIALIST_WORKPLACE);
                result = new String(id+Const.SPACE+nameText
                                    +Const.SPACE+surnameText
                                    +Const.SPACE+proffession
                                    +Const.SPACE+workplace);
                System.out.println(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void setGraphics(Graphics2D g){
        this.g = g;
    }
}

