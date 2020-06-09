package sample;

import Behavioral.Memento.CareTaker;
import Behavioral.Memento.Originator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Behavioral.Visitor.Visitor;
import Creational.Factory.Dialog;
import Creational.Factory.DoctorListView;
import Creational.Factory.Factory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class SpecialistController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public ChoiceBox<String> specChooseList;

    @FXML
    private ChoiceBox<String> hospitalChooseList;

    @FXML
    private Button signUpButton;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private Button backBtn;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Factory factory = new Factory();

        specChooseList.getItems().addAll("Кардиолог","Хирург","Травматолог", "Уролог", "ЛОР");
        hospitalChooseList.getItems().addAll("Госпиталь","Травмпункт","Урология");
        signUpButton.setOnAction(actionEvent -> {
            String specialization = specChooseList.getValue();
            String hospital = hospitalChooseList.getValue();
            String data = dataPicker.getEditor().getText();

            signUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Special.fxml"));
            try {
                DoctorListView doctorListView = new DoctorListView();
                doctorListView.render();
               // factory.getClass().getClassLoader().getName();
                loader.load();
            } catch (IOException e) {
                System.out.println("The load was failed");
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            SpecialController controller = loader.getController();
            controller.initialize();

        });
        backBtn.setOnAction(actionEvent -> {

            String nameText = new String();
            String surnameText = new String();
            String numberText = new String();
            String adressText = new String();
            CareTaker careTaker = new CareTaker();
            careTaker.getMemento("firstSavePoint");

            Originator originator = new Originator(nameText,surnameText,numberText,adressText,careTaker);
            originator.undo();
            backBtn.getScene().getWindow().hide();
            FXMLLoader lo = new FXMLLoader();
            lo.setLocation(getClass().getResource("/sample/user.fxml"));
            try {

                lo.load();
            } catch (IOException e) {
                System.out.println("The load was failed");
                e.printStackTrace();
            }
            Parent root = lo.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
//            SpecialController controller = lo.getController();
//            controller.initialize();
        });
    }
    public String accept(Visitor visitor){
        return visitor.visitSpecialistController(this);
    }
    public String getHospitalChooseList() {
        String hospital = hospitalChooseList.getValue();
        return hospital;
    }
    public String getSpecChooseList() {
        String specialization = specChooseList.getValue();
        return specialization;
    }
    public String getDataPicker() {
        String data = dataPicker.getEditor().getText();
        return data;
    }
    public void select(){
        String string = "Select * FROM"+ Const.SPECIALIST_TABLE;

    }
}