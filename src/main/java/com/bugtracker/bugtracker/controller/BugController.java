package com.bugtracker.bugtracker.controller;


import com.bugtracker.bugtracker.model.Bug;
import com.bugtracker.bugtracker.model.Label;
import com.bugtracker.bugtracker.model.Priority;
import com.bugtracker.bugtracker.model.Status;
import com.bugtracker.bugtracker.service.LabelService;
import com.bugtracker.bugtracker.service.BugService;
import com.bugtracker.bugtracker.service.PriorityService;
import com.bugtracker.bugtracker.service.StatusService;
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
public class BugController {


    private final BugService bugService;
    private final LabelService labelService;
    private final PriorityService priorityService;
    private final StatusService statusService;

    public BugController(BugService bugService, LabelService labelService, PriorityService priorityService, StatusService statusService) {
        this.bugService = bugService;
        this.labelService = labelService;
        this.priorityService = priorityService;
        this.statusService = statusService;
    }


    //display list of employees
    @GetMapping("/BugList")
    public String viewHomePage(Model model){
        model.addAttribute("listBugs", bugService.getAllBug());
        return "indexBug";
    }



    @GetMapping("/showFormForDetails/{id}")
    public String showFormForDetails(@Valid @PathVariable (value = "id") long id, Model model){

        //get bug from the service
        Bug bug = bugService.getBugById(id);

        //set bug as a model attribute to pre-populate the form
        model.addAttribute("bugDetails", bug);
        return "bug_details";
    }



    @PostMapping("/showFormForDetails/{id}")
    public String updateBugIsActive(@Valid @PathVariable (value = "id") long id, Model model){

        //get bug from the service
        Bug bug = bugService.getBugById(id);
        bug.setActive(!bug.isActive());
        bugService.saveBug(bug);
        model.addAttribute("bugDetails", bug);

        return "bug_details";
    }



    @GetMapping("/shownewBugForm")
    public String shownewBugForm(Model model){

        //create model attribute to bind form data
        Bug bug = new Bug();
        List<Label> listLabel2 = labelService.getAllLabel();
        model.addAttribute("listLabel2", listLabel2);

        List<Priority> priorityList = priorityService.getAllPriority();
        model.addAttribute("listPriority2", priorityList);

        model.addAttribute("bug", bug);


        return "new_bug";
    }


    @PostMapping("/saveBug")
    public String saveBug(@Valid @ModelAttribute("bug") Bug bug, BindingResult bindingResult, Model model){

        List<Label> listLAbel2 = labelService.getAllLabel();
        model.addAttribute("listLabel2", listLAbel2);

        List<Priority> priorityList = priorityService.getAllPriority();
        model.addAttribute("listPriority2", priorityList);

        if (bindingResult.hasErrors()){

            return "new_bug";
        }
        //save bug to database
        bugService.saveBug(bug);
        return "redirect:/shownewBugForm?successadd";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@Valid @PathVariable (value = "id") long id, Model model){


        //get bug from the service
        Bug bug = bugService.getBugById(id);
        bug.setActive(!bug.isActive());

        //set bug as a model attribute to pre-populate the form
        model.addAttribute("bug", bug);
        List<Label> listLabel2 = labelService.getAllLabel();
        model.addAttribute("listLabel2", listLabel2);

        List<Priority> priorityList = priorityService.getAllPriority();
        model.addAttribute("listPriority2", priorityList);

        List<Status> statusList = statusService.getAllStatus();
        model.addAttribute("statusList2", statusList);
        return "update_bug";
    }


    @PostMapping("/updateBug")
    public String updateBug(@Valid  @ModelAttribute("bug") Bug bug, BindingResult bindingResult, Model model){
        List<Label> listLabel2 = labelService.getAllLabel();
        model.addAttribute("listLabel2", listLabel2);

        List<Priority> priorityList = priorityService.getAllPriority();
        model.addAttribute("listPriority2", priorityList);

        List<Status> statusList = statusService.getAllStatus();
        model.addAttribute("statusList2", statusList);

        if (bindingResult.hasErrors()){

            return "update_bug";
        }
        bugService.updateBug(bug);
        return "update_bug";

    }

    @GetMapping("/deleteBug/{id}")
    public String deleteBug(@PathVariable (value = "id") long id){

        // call delete employee

        this.bugService.deleteBugById(id);
        return "redirect:/BugList";
    }
}
