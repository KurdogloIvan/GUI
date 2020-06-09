package sample;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Behavioral.Visitor.CompoundUser;
import Behavioral.Visitor.Shape;
import Behavioral.Visitor.Visitor;
import Behavioral.Visitor.XMLExportVisitor;
import Creational.Singleton;
import Struct.Dec.CompressionDecorator;
import Struct.Dec.DataSourceDecorator;
import Struct.Dec.EncryptDecorator;
import Struct.Dec.FileDataSource;
import Struct.Dec.Decorator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class SpecialController implements Shape {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextFlow SpecialistTextFlow;

    @FXML
    private TextField SpecialistNameField;

    @FXML
    private TextField SpecialistSurnameField;

    @FXML
    private TextField SpecialistSpecializationField;

    @FXML
    private Button sendButton;

    @FXML
    void initialize(){
        SpecialistTextFlow.getChildren().clear();
        DatabaseHandler dbHandler = new DatabaseHandler();
        String nameText = new String();
        String nameUser = new String();
        ResultSet resultSet = null;
       // resultSet = dbHandler.getList(nameText);

        String adress = new String();
        String number = new String();
        String surname = new String();
        String name = new String();
        Singleton sing =Singleton.getInstance(name,surname,number,adress);
        Text textF1 = new Text( "Пациент: "+sing.name
                                +Const.SPACE+sing.surname
                                +Const.NEXT_LINE+"Контакты: "+sing.number
                                +Const.NEXT_LINE+"Адрес: "+Const.SPACE + sing.adress);
            String string = new String(textF1.getText());
            System.out.println("String "+string);
        SpecialistTextFlow.setTextAlignment(TextAlignment.LEFT);
        SpecialistTextFlow.getChildren().addAll(textF1);

        sendButton.setOnAction(actionEvent -> {
            String specName = SpecialistNameField.getText().trim();
            String specSurname = SpecialistSurnameField.getText().trim();
            String  specProff = SpecialistSpecializationField.getText().trim();
            Text SpecNameText = new Text(   Const.NEXT_LINE+"Имя врача: "+specName +
                                            Const.NEXT_LINE + "Фамилия врача: "+specSurname +
                                            Const.NEXT_LINE + "Профессия: "+ specProff);
            SpecNameText.setFill(Color.RED);
            SpecNameText.setFont(new Font("Dialog",14));
            SpecialistTextFlow.setTextAlignment(TextAlignment.LEFT);
            SpecialistTextFlow.getChildren().addAll(SpecNameText);
            CompoundUser compoundUser = new CompoundUser(5);
            compoundUser.add(specName);
            compoundUser.add(specSurname);
            compoundUser.add(specProff);
            XMLExportVisitor xmlExportVisitor = new XMLExportVisitor();
            xmlExportVisitor.export(specName + compoundUser);

            Text textF2 = new Text("Пациент: "+sing.name
                                    +Const.SPACE+sing.surname
                                    +Const.NEXT_LINE+"Контакты: "+sing.number
                                    +Const.NEXT_LINE+"Адрес: "+Const.SPACE + sing.adress
                                    +Const.NEXT_LINE+"Имя врача: "+specName +
                                    Const.NEXT_LINE + "Фамилия врача: "+specSurname +
                                    Const.NEXT_LINE + "Профессия: "+ specProff);

            String textF3 = new String(textF2.getText());

            DataSourceDecorator dataSourceDecorator =   new CompressionDecorator(
                    new EncryptDecorator(new FileDataSource("src/Output.txt")));
            dataSourceDecorator.writeData(textF3);
            Decorator plan = new FileDataSource("src/Output.txt");
            String str = new String(plan.readData());
            Text text2 = new Text(Const.NEXT_LINE+"Код: "+str);
            SpecialistTextFlow.getChildren().addAll(text2);
            System.out.println("str "+str);
            System.out.println("Encoded: "+plan.readData());
            System.out.println("Decoded: "+Const.NEXT_LINE+dataSourceDecorator.readData());
        });
    }
    public String accept(Visitor visitor){
        return visitor.visitSpecialController(this);
    }

    public String getSpecialistNameField() {
        String specName = SpecialistNameField.getText().trim();
        return specName;
    }
    public String getSpecialistSurnameField(){
        String specSurname = SpecialistSurnameField.getText().trim();
        return specSurname;
    }

    public String getSpecialistSpecializationField() {
        String  specProff = SpecialistSpecializationField.getText().trim();
        return specProff;
    }

}

