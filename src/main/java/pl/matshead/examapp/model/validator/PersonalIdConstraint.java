package pl.matshead.examapp.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation used for validating personal id of employee
 */
@Documented
@Constraint(validatedBy = PersonalIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonalIdConstraint {
    String message() default "Invalid Personal Id. It has to be numeric and 11 length.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}