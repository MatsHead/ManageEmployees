package pl.matshead.examapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.matshead.examapp.model.Address;
import pl.matshead.examapp.model.Employee;
import pl.matshead.examapp.repositories.EmployeeRepository;
import pl.matshead.examapp.static_values.Position;

import javax.script.Bindings;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }
    @GetMapping("/employees")
    public String getEmployees(ModelMap modelMap){
        modelMap.put("employees", employeeRepository.findAll(new Sort(Sort.Direction.ASC, "id")));
        return "employees";
    }
    @GetMapping("/employees/{id}/edit")
    public String editSingleEmployee(@PathVariable Integer id, ModelMap modelMap){
        modelMap.put("employee", employeeRepository.findById(id).get());
        modelMap.put("positions", Position.values());
        return "employee-edit";
    }
    @GetMapping("/employees/{id}/show-one")
    public String showOneEmployee(@PathVariable Integer id, ModelMap modelMap) {
        List<Employee> employees = new ArrayList<Employee>(){{add(employeeRepository.findById(id).get());}};
        if (employees.size() == 1)
        modelMap.put("employees" , employees);
        return "employees";
    }
    @GetMapping("/employees/add")
    public String addNewEmployee(ModelMap modelMap){
        Employee employee = new Employee();
        employee.setAddress(new Address());
        modelMap.put("employee", employee);
        modelMap.put("positions", Position.values());
        return "employee-edit";
    }
    @PostMapping("/employees")
    public String saveEmployee(@Valid Employee employee, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "employee-edit";

        }
        employeeRepository.save(employee);
        return "redirect:/api/employees";
    }
    @DeleteMapping("/employees/{id}/delete")
    public String deleteUserById(@PathVariable Integer id, ModelMap modelMap) {
        employeeRepository.deleteById(id);
        return "employees";
    }
}
