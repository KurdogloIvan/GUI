package Creational.Factory;

import java.sql.SQLException;

public class Factory {
    private static Dialog dialog;

    public static void main(String[] args) throws SQLException {
        configure();
        runBusinessLogic();
    }
    static void configure()
    {
        if (System.getProperty("os.name").equals("Windows 10")) {
            dialog = new PanelDialog();
        }
    }
    private static void runBusinessLogic() throws SQLException {
        dialog.renderWindow();
    }
}
