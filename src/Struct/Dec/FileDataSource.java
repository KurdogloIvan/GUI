package Struct.Dec;

import java.io.*;

public class FileDataSource implements Decorator {
    private String FileName;

    public FileDataSource(String name) {
        this.FileName = name;
    }
    public void writeData(String data) {
        File file = new File(FileName);
        try (OutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes(), 0, data.length());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String readData() {
        char[] buffer = null;
        File file = new File(FileName);
        try (FileReader reader = new FileReader(file)) {
            buffer = new char[(int) file.length()];
            reader.read(buffer);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return new String(buffer);
    }
}
