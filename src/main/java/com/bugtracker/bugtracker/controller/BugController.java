package com.bugtracker.bugtracker.controller;


import com.bugtracker.bugtracker.model.Bug;
import com.bugtracker.bugtracker.model.Label;
import com.bugtracker.bugtracker.service.LabelService;
import com.bugtracker.bugtracker.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private BugService bugService;

    @Autowired
    private LabelService labelService;



    //display list of employees
    @GetMapping("/EmployeeList")
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees", bugService.getAllBug());
        return "indexEmployee";
    }

    //display list of employees
    @GetMapping("/searchEmployee")
    public String getEmployee(Model model, String keyword){
        if (keyword != null){
            model.addAttribute("listEmployees", bugService.findByKeyWord(keyword));
        }else {
            model.addAttribute("listEmployees", bugService.getAllBug());
        }
        return "indexEmployee";
    }


    @GetMapping("/showFormForDetails/{id}")
    public String showFormForDetails(@Valid @PathVariable (value = "id") long id, Model model){

        //get bug from the service
        Bug bug = bugService.getBugById(id);


        //set bug as a model attribute to pre-populate the form
        model.addAttribute("employeeDetails", bug);
        return "employee_details";
    }


    @PostMapping("/showFormForDetails/{id}")
    public String updateEmployeeStatus(@Valid @PathVariable (value = "id") long id, Model model){

        //get bug from the service
        Bug bug = bugService.getBugById(id);
        bug.setActive(!bug.isActive());
        bugService.saveBug(bug);
        model.addAttribute("employeeDetails", bug);
        return "employee_details";
    }


    @GetMapping("/shownewEmployeeForm")
    public String shownewEmployeeForm(Model model){
        //create model attribute to bind form data
        Bug bug = new Bug();
        List<Label> listDepartments2 = labelService.getAllLabel();
        model.addAttribute("listLabel2", listDepartments2);
        model.addAttribute("employee", bug);
        return "new_employee";
    }


    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Bug bug, BindingResult bindingResult, Model model){

        List<Label> listDepartments2 = labelService.getAllLabel();
        model.addAttribute("listLabel2", listDepartments2);

        if (bindingResult.hasErrors()){

            return "new_employee";
        }
        //save bug to database
        bugService.saveBug(bug);
        return "redirect:/shownewEmployeeForm?successadd";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@Valid @PathVariable (value = "id") long id, Model model){


        //get bug from the service
        Bug bug = bugService.getBugById(id);
        bug.setActive(!bug.isActive());

        //set bug as a model attribute to pre-populate the form
        model.addAttribute("employee", bug);
        List<Label> listLabel2 = labelService.getAllLabel();
        model.addAttribute("listLabel2", listLabel2);
        return "update_employee";
    }


    @PostMapping("/updateEmployee")
    public String updateEmployee(@Valid  @ModelAttribute("employee") Bug bug, BindingResult bindingResult, Model model){
        List<Label> listDepartments2 = labelService.getAllLabel();
        model.addAttribute("listLabel2", listDepartments2);

        if (bindingResult.hasErrors()){

            return "update_employee";
        }
        bugService.updateBug(bug);
        return "update_employee";

    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id){

        // call delete employee

        this.bugService.deleteBugById(id);
        return "redirect:/EmployeeList";
    }
}
