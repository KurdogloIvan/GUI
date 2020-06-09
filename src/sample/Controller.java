package sample;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import Behavioral.Memento.CareTaker;
import Behavioral.Memento.Originator;
import Behavioral.Visitor.CompoundUser;
import Behavioral.Visitor.Visitor;
import Behavioral.Visitor.XMLExportVisitor;
import Creational.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField number;

    @FXML
    private TextField adress;

    @FXML
    private Button btnSend;

    @FXML
    void initialize() {
        CareTaker careTaker = new CareTaker();


        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.getDbConnection();
        btnSend.setOnAction(actionEvent -> {

            String nameText = name.getText().trim();
            String surnameText = surname.getText().trim();
            String numberText = number.getText().trim();
            String adressText = adress.getText().trim();
           dbHandler.dbUpdate(nameText,surnameText,numberText,adressText);
            Originator originator = new Originator(nameText,surnameText,numberText,adressText,careTaker);
            originator.setName(originator.getName());
            originator.setSurname(originator.getSurname());
            originator.setNumber(originator.getNumber());
            originator.setAdress(originator.getAdress());
            originator.toString();
            originator.createSavepoint("firstSavePoint");

            CompoundUser compoundUser = new CompoundUser(5);
            compoundUser.add(nameText);
            compoundUser.add(surnameText);
            compoundUser.add(numberText);
            compoundUser.add(adressText);
            XMLExportVisitor xmlExportVisitor = new XMLExportVisitor();
            xmlExportVisitor.export("User"+compoundUser);

            Singleton sing =Singleton.getInstance(nameText,surnameText,numberText,adressText);

            btnSend.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            System.out.println("Name "+name.getText()+"\nSurname "+surname.getText()+"\nNumeber "+number.getText()+"\nAdress "+adress.getText());

            loader.setLocation(getClass().getResource("/sample/Specialist.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
    public String accept(Visitor visitor){
        return visitor.visitController(this);
    }
    public String getName(){
        String nameText = name.getText().trim();
        return nameText;
    }
    public String getSurname(){
        String surnameText = surname.getText().trim();
        return surnameText;
    }
    public String getNumber(){
        String numberText = number.getText().trim();
        return numberText;
    }
    public String getAdress(){
        String adressText = adress.getText().trim();
        return adressText;
    }
}
