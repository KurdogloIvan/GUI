package Creational.Factory;

public class PanelDialog extends Dialog {
    @Override
    public Panel createPanel() {
        return new DoctorListView();
    }
}
