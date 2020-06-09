package Creational.Factory;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import sample.Const;
import sample.Controller;
import sample.DatabaseHandler;
import sample.SpecialistController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorListView extends JPanel implements Panel {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();

    Scene scene;
    JButton button;
    DatabaseHandler dbHandler = new DatabaseHandler();
    public static Connection dbConnect;
    int id;
    String nameText;
    String surnameText;
    String proffession;
    String workplace;
    @Override
    public void render() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Список специалистов!");
        label.setOpaque(true);
        label.setBackground(new Color(235, 233, 126));
        label.setFont(new Font("Dialog", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JTextArea jTextField = new JTextArea("helloakdbasdf");
        jTextField.setOpaque(true);
        jTextField.setBackground(new Color(235, 233, 126));
       // jTextField.setHorizontalAlignment(SwingConstants.CENTER);

        frame.getContentPane().add(panel);

        panel.add(label);
        panel.add(jTextField);
//        Graphics2D graphics= (Graphics2D)panel.getGraphics();
        //dbHandler.setGraphics(graphics);
//        select1(graphics,id,nameText,surnameText,proffession,workplace);
        onClick();
        panel.add(button);
        jTextField.setLocation(40,40);
        jTextField.setSize(40,60);
        frame.setSize(400, 500);
        frame.setVisible(true);
        //String string = new String(dbHandler.select(id,nameText,surnameText,proffession,workplace));
       // dbHandler.select();

        dbHandler.select(id,nameText,surnameText,proffession,workplace);

        onClick();
    }

    @Override
    public void onClick() {
        button = new  JButton("Закрыть");

        button.addActionListener(actionEvent -> {
            frame.setVisible(false);
            System.exit(0);
        });
    }
    public void select1(int id,String nameText,String surnameText,String proffession,String workplace){
        String string = "Select * FROM "+ Const.SPECIALIST_TABLE;
        String result;
//        g = (Graphics2D) getGraphics();
        System.out.println("До тру");
        try {
            dbConnect = DriverManager.getConnection("jdbc:sqlserver://WIN-BQKV1N3KBJH\\SQL;databaseName = Registration",
                    "tmpsLogin",
                    "tmpsLogin");
            System.out.println("До statement");
            Statement statement = dbConnect.createStatement();
            ResultSet resSet = statement.executeQuery(string);
            System.out.println("До while");
            while(resSet.next()){
                System.out.println("in while");
                id = resSet.getInt(Const.SPECIALIST_ID);
                nameText = resSet.getString(Const.SPECIALIST_NAME);
                surnameText = resSet.getString(Const.SPECIALIST_SURNAME);
                proffession = resSet.getString(Const.SPECIALIST_PROFESSION);
                workplace = resSet.getString(Const.SPECIALIST_WORKPLACE);
                result = new String(id+nameText+surnameText+proffession+workplace);
//                if(result != null){
//                    g.drawString(result,20,20);
//                }
                System.out.println(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
