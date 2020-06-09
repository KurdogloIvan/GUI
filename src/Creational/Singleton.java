package Creational;

public  final class Singleton{
    private static Singleton instance;
    public String adress;
    public String number;
    public String surname;
    public String name;


    private Singleton(String name, String surname, String number, String adress){
        try {
            Thread.sleep(565);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.adress = adress;
    }
    public static Singleton getInstance(String name,String surname, String number, String adress){
        if(instance == null){
            instance = new Singleton(name,surname,number,adress);
        }
        return instance;
    }
}
