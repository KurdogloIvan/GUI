package Creational.Factory;

import java.sql.SQLException;

public abstract class Dialog {
    public void renderWindow()  {
        Panel OkPanel = createPanel();
        OkPanel.render();

    }
    public abstract Panel createPanel();
}
