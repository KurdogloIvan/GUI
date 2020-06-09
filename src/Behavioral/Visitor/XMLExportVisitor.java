package Behavioral.Visitor;

import sample.Controller;
import sample.SpecialController;
import sample.SpecialistController;

public class XMLExportVisitor implements Visitor {
    public String export(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String shape : args) {
            sb.append("User");
            //sb.append(shape.accept(this)).append("\n");
           // sb.append(shape.lines().findAny().get()).append("\n");
            sb.append(this);
            System.out.println(sb.toString());
            sb.setLength(0);
        }
        return sb.toString();
    }

    @Override
    public String visitSpecialController(SpecialController specialController) {
        return specialController.getSpecialistNameField()+
                specialController.getSpecialistSurnameField()+
                specialController.getSpecialistSpecializationField();
    }

    @Override
    public String visitController(Controller controller) {
        return controller.getName()+
                controller.getSurname()+
                controller.getNumber()+
                controller.getAdress();
    }

    @Override
    public String visitSpecialistController(SpecialistController specialistController) {
        return specialistController.getSpecChooseList()+
                specialistController.getHospitalChooseList()+
                specialistController.getDataPicker();
    }

    @Override
    public String CompoundUser(CompoundUser compoundUser){
        StringBuilder sb = new StringBuilder();
        for (String shape : compoundUser.children) {
            String obj = shape.toString();
            // Proper indentation for sub-objects.
            obj = "    " + obj.replace("\n", "\n    ") + "\n";
            sb.append(obj);
        }
        return sb.toString();
    }
}
