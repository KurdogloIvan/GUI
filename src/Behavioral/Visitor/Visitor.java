package Behavioral.Visitor;

import sample.Controller;
import sample.SpecialController;
import sample.SpecialistController;

public interface Visitor {
    String visitSpecialController(SpecialController specialController);

    String visitController(Controller controller);

    String visitSpecialistController(SpecialistController specialistController);

    String CompoundUser(CompoundUser compoundUser);
}
