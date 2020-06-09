package Struct.Dec;

public class DataSourceDecorator implements Decorator {
    private Decorator wrappee;

    DataSourceDecorator(Decorator source) {
        this.wrappee = source;
    }
    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }
}
