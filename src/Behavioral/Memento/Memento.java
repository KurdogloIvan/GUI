package Behavioral.Memento;

public class Memento {
    private String name;
    private String surname;
    private String number;
    private String adress;

    public Memento(String name, String surname,String number,String adress){
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.adress = adress;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getNumber(){
        return number;
    }
    public String getAdress(){
        return adress;
    }
}
