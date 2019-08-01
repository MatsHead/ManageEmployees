package pl.matshead.examapp.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Class with logic for validating personal id of employee
 * It implements interface ConstraintValidator and implements isValid
 */
public class PersonalIdValidator implements ConstraintValidator<PersonalIdConstraint, String> {


    @Override
    public void initialize(PersonalIdConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() == 11 && s.matches("^[0-9]{11}$");
    }
}
