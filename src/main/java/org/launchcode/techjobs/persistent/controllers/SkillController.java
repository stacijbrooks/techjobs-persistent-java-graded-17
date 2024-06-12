package org.launchcode.techjobs.persistent.controllers;


import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//Task 2, Controller #5

@Controller
@RequestMapping("skills")
public class SkillController {

    // Autowire the SkillRepository to perform database operations
    @Autowired
    private SkillRepository skillRepository;

    // Index method to list all skills
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("title", "All Skills");
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }

    // Display form to add a new skill
    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        // Add a new empty Skill object to the model and render the add skill form template
        model.addAttribute(new Skill());
        return "skills/add";
    }

    // Process form submission to add a new skill
    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                      Errors errors, Model model) {

        //Setting up the model attributes for displaying the 'Add Skill' form, including handling validation errors.E
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Skill");
            model.addAttribute(newSkill);
            return "skills/add";
        }

        // Save the valid skill object to the database
        skillRepository.save(newSkill);

        // Redirect
        return "redirect:";
    }

    // Display details of a specific skill
    // Mapping HTTP GET requests to "/view/{skillId}" to this method
    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {
        // Find the skill by its ID using the repository
        Optional<Skill> optSkill = skillRepository.findById(skillId);

        // Check if the skill is present in the optional
        if (optSkill.isPresent()) {
            // If present, get the skill from the optional
            Skill skill = optSkill.get();

            // Add the skill to the model to pass it to the view
            model.addAttribute("skill", skill);

            // Return the view name "skills/view" to display the skill
            return "skills/view";
        } else {
            // If the skill is not found, redirect to the skills index page
            return "redirect:../";
        }
    }

}

//    // No-arg constructor
//    public SkillController() {
//    }


