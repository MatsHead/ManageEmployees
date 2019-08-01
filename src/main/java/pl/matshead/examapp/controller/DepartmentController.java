package pl.matshead.examapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.matshead.examapp.model.Address;
import pl.matshead.examapp.model.Department;
import pl.matshead.examapp.repositories.DepartmentRepository;

@Controller
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/departments")
    public String getDepartments(ModelMap modelMap) {

        modelMap.put("departments", departmentRepository.findAll(new Sort(Sort.Direction.ASC, "id")));
        return "departments";
    }

    @DeleteMapping("/departments/{id}/delete")
    public String deleteDepartment(@PathVariable Integer id){
        departmentRepository.deleteById(id);
        return "departments";
    }
    @GetMapping("/departments/add")
    public String addNewDepartment(ModelMap modelMap){
        Department department = new Department();
        department.setAddress(new Address());
        modelMap.put("department", department);
        return "department-edit";
    }
    @PostMapping("/departments")
    public String saveDepartment(@ModelAttribute Department department) {
        departmentRepository.save(department);
        return "redirect:/api/departments";
    }
    @GetMapping("/departments/{id}/edit")
    public String editDepartment(@PathVariable Integer id, ModelMap modelMap){
        modelMap.put("department", departmentRepository.findById(id));
        return "department-edit";
    }

}
