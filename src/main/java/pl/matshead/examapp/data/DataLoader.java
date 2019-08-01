package pl.matshead.examapp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.matshead.examapp.model.Address;
import pl.matshead.examapp.model.Department;
import pl.matshead.examapp.model.Employee;
import pl.matshead.examapp.repositories.DepartmentRepository;
import pl.matshead.examapp.repositories.EmployeeRepository;
import pl.matshead.examapp.static_values.Position;



/**
 * This class puts some sample departments,
 * if in database there are not even 1
 */
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) throws Exception {

        if (departmentRepository.count() == 0) {

            departmentRepository.save(new Department(new Address("Poland", "Warsaw",
                    "Wojska Polskiego", "", "13B",
                    "00-200", "department1@wp.pl"), "Department of Justice"));
            departmentRepository.save(new Department(new Address("Poland", "Gdynia",
                    "Adama Mickiewicza", "", "27G",
                    "02-230", "department2@org.pl"), "Department of Anger"));
            departmentRepository.save(new Department(new Address("England", "London",
                    "Bakery Street", "", "21B",
                    "42-950", "department3@eng.com"), "Department of Hapinnes"));
            departmentRepository.save(new Department(new Address("Germany", "Dortmund",
                    "Strase", "12/87", "",
                    "33-081", "department4%deutch.de"), "Department of Smile"));
            departmentRepository.save(new Department(new Address("Russia", "Moscow",
                    "Andrieja", "4/15", "",
                    "11-202", "department5%uu.pec"), "Department of Joy"));
        }
        if (employeeRepository.count() == 0) {
            employeeRepository.save(new Employee(
                    "Mateusz",
                    "Jezierski",
                    96020800000l,
                    new Address(
                            "Poland", "Warszawa", "Melsztyńska", "4/10 37", "", "02-537",
                            "jezierski.mat@gmail.com"),
                    Position.PROGRAMMER,
                    10_000,
                    false, departmentRepository.findById(1).get()
                    ));
            employeeRepository.save(new Employee(
                    "Jacek",
                    "Placek",
                    96020833300l,
                    new Address(
                            "England", "London", "Bakery", "", "12B", "02-111",
                            "jaco-placo.@gmail.com"),
                    Position.PROGRAMMER,
                    4800,
                    false, departmentRepository.findById(1).get()
            ));
        }

    }
}
