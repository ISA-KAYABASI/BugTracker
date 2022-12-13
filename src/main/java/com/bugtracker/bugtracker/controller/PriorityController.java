package com.bugtracker.bugtracker.controller;

import com.bugtracker.bugtracker.model.Priority;
import com.bugtracker.bugtracker.service.PriorityService;
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
public class PriorityController {

    @Autowired
    private PriorityService priorityService;

    //display list of priority
    @GetMapping("/PriorityList")
    public String viewLabel(Model model){
        List<Priority> priorityList = priorityService.getAllPriority();

        model.addAttribute("listPriority", priorityList);
        return "indexPriority";
    }
    //display list of priority
    @GetMapping("/PriorityList2")
    public String viewLabel2(Model model){
        List<Priority> priorityList = priorityService.getAllPriority();
        model.addAttribute("listPriority2", priorityList);
        return "new_bug";
    }


    @GetMapping("/shownewPriorityForm")
    public String shownewPriorityForm(Model model){
        //create model attribute to bind form data
        Priority priority = new Priority();
        model.addAttribute("priority", priority);
        return "new_priority";

    }

    @PostMapping("/savePriority")
    public String savePriority(@Valid @ModelAttribute("priority") Priority priority, BindingResult bindingResult){
        //save Priority to database
        try {
            if (bindingResult.hasErrors()){
                return "new_priority";
            }else{
                priorityService.savePriority(priority);
                return "redirect:/shownewPriorityForm?success";
            }
        }catch (Exception e){
            return "redirect:/shownewPriorityForm?error";
        }
    }

    @GetMapping("/showFormForUpdatePriority/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){

        Priority priority = priorityService.getPriorityById(id);

        model.addAttribute("priority", priority);
        return "update_priority";
    }
    @PostMapping("/update_priority")
    public String updatePriority(@Valid @ModelAttribute("priority") Priority priority, BindingResult bindingResult){
        //save priority to database
        try {
            if (bindingResult.hasErrors()){
                return "update_priority";
            }else{
                priorityService.updatePriority(priority);
                return "update_priority";
            }
        }catch (Exception e){
            return "redirect:/shownewPriorityForm?error";
        }
    }


    @GetMapping("/deletePriority/{id}")
    public String deletePriority(@PathVariable (value = "id") long id){

        // call delete employee

        this.priorityService.deletePriorityById(id);
        return "redirect:/PriorityList";
    }
}
