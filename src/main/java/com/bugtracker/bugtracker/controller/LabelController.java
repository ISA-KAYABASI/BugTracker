package com.bugtracker.bugtracker.controller;

import com.bugtracker.bugtracker.model.Label;
import com.bugtracker.bugtracker.service.LabelService;
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
public class LabelController {

    @Autowired
    private LabelService labelService;

    //display list of departments
    @GetMapping("/LabelList")
    public String viewLabel(Model model){
        List<Label> labelList = labelService.getAllLabel();

        model.addAttribute("listLabel", labelList);
        return "indexLabel";
    }
    //display list of departments
    @GetMapping("/LabelList2")
    public String viewLabel2(Model model){
        List<Label> labelList = labelService.getAllLabel();
        model.addAttribute("listLabel2", labelList);
        return "new_bug";
    }


    @GetMapping("/shownewLabelForm")
    public String shownewLabelForm(Model model){
        //create model attribute to bind form data
        Label label = new Label();
        model.addAttribute("label", label);
        return "new_label";

    }

    @PostMapping("/saveLabel")
    public String saveLabel(@Valid @ModelAttribute("label") Label label, BindingResult bindingResult){
        //save label to database
        try {
            if (bindingResult.hasErrors()){
                return "new_label";
            }else{
                labelService.saveLabel(label);
                return "redirect:/shownewLabelForm?success";
            }
        }catch (Exception e){
            return "redirect:/shownewLabelForm?error";
        }
    }

    @GetMapping("/showFormForUpdateLabel/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){

        //get bug from the service
        Label label = labelService.getLabelById(id);

        //set employee as a model attribute to pre-populate the form
        model.addAttribute("label", label);
        return "update_label";
    }
    @PostMapping("/update_label")
    public String updateLabel(@Valid @ModelAttribute("label") Label label, BindingResult bindingResult){
        //save label to database
        try {
            if (bindingResult.hasErrors()){
                return "update_label";
            }else{
                labelService.updateLabel(label);
                return "update_label";
            }
        }catch (Exception e){
            return "redirect:/shownewLabelForm?error";
        }
    }


    @GetMapping("/deleteLabel/{id}")
    public String deleteLabel(@PathVariable (value = "id") long id){

        // call delete employee

        this.labelService.deleteLabelById(id);
        return "redirect:/LabelList";
    }
}
