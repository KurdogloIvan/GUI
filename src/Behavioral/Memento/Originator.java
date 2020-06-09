package Behavioral.Memento;

public class Originator {
    private String name;
    private String surname;
    private String number;
    private String adress;

    private String lastUndoSavepoint;
    CareTaker careTaker;

    public Originator(String name, String surname,String number,String adress, CareTaker careTaker){
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.adress = adress;
        this.careTaker = careTaker;
        createSavepoint("INITIAL");
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

    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setNumber(String number){
        this.name = number;
    }
    public void setAdress(String adress){
        this.adress = adress;
    }
    public void createSavepoint(String savepointName){
        careTaker.saveMemento(new Memento(this.name,this.surname,this.number,this.adress),savepointName);
        lastUndoSavepoint = savepointName;
    }
    public void undo(){
        setOriginatorState(lastUndoSavepoint);
    }
    public void undo(String savepointName){
        setOriginatorState(savepointName);
    }
    public void undoAll(){
        setOriginatorState("INITIAL");
        careTaker.clearSavepoints();
    }

    private void setOriginatorState(String savepointName){
            Memento mem = careTaker.getMemento(savepointName);
            this.name = mem.getName();
            this.surname = mem.getSurname();
            this.number = mem.getNumber();
            this.adress = mem.getAdress();
    }
    public String toString(){
        return "name: "+name + "surname: " + surname +
                "number: "+number + "adress: "+adress;
    }
}
