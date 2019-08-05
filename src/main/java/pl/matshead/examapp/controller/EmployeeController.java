package pl.matshead.examapp.controller;

import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.matshead.examapp.excelHandlerPackage.EmployeesExcelHandler;
import pl.matshead.examapp.model.Address;
import pl.matshead.examapp.model.Employee;
import pl.matshead.examapp.repositories.EmployeeRepository;
import pl.matshead.examapp.static_values.Position;

import javax.validation.Valid;
import java.io.IOException;
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
        Employee employee = new Employee();
        employee.setAddress(new Address());
        modelMap.put("employee", employeeRepository.findById(id).orElse(employee));
        modelMap.put("positions", Position.values());
        return "employee-edit";
    }
    @GetMapping("/employees/{id}/show-one")
    public String showOneEmployee(@PathVariable Integer id, ModelMap modelMap) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        List<Employee> employees = new ArrayList<>();
        if(employee == null){
            modelMap.put("employees", employeeRepository.findAll());
        } else {
            employees.add(employee);
            modelMap.put("employees", employees);
        }
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
    @GetMapping("/employees/import")
    public String importToXls(){
        List<Employee> employees = employeeRepository.findAll();
        System.out.println("***********" + employees.size());
        EmployeesExcelHandler test = new EmployeesExcelHandler();
        test.setOutputFile("/home/mateusz/workspace/RepositoriesForAkademiaKodu/ExamApp/src/main/resources/static/excel/test-1.xls");
        try {
            test.write(employees);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return "employees";
    }
}
