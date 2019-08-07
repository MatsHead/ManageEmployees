package pl.matshead.examapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.validator.AnnotationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.matshead.examapp.model.Address;
import pl.matshead.examapp.model.Employee;
import pl.matshead.examapp.model.validator.PersonalIdConstraint;
import pl.matshead.examapp.model.validator.PersonalIdValidator;
import pl.matshead.examapp.repositories.EmployeeRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.annotation.Annotation;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.fail;
import static org.junit.Assert.*;

/**
 * Class which is for testing operation on Employee Entity
 * It's Transactional so any operation in DB will be roll backed
 */
/*
    Soon there will be similar for Department Entity
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    ValidatorFactory factory;
    Validator validator;
    Employee employee;

    @Before
    public void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        employee = new Employee();
        employee.setPersonalId("45612312311");
        employee.setName("Izaak");
        employee.setSurname("Pentarek");
        employee.setAddress(new Address("Poland", "Warsaw", "Marszałkowska", "", "4/231", "00-541", "arak@wp.pl"));
    }

    /**
     * This method validate correct values of Employee
     */
    @Test
    public void testPersonalIdCorrect() {
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        System.out.println(constraintViolations.size());
        assertEquals(0, constraintViolations.size());
    }

    @Test
    @Transactional
    public void addEmployeeToDataBase(){
        employeeRepository.save(employee);
    }

    @Test
    @Transactional
    public void deleteEmployeeFromDataBase(){
        employeeRepository.save(employee);
        long size = employeeRepository.count();
        employeeRepository.delete(employee);
        long sizeAfter = employeeRepository.count();
        assertTrue(sizeAfter == size-1);
    }
}
