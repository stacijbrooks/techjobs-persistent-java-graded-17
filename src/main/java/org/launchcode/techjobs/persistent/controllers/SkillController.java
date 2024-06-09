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
    @GetMapping
    public String index(Model model) {
        // Add all skills to the model and render the skills index template
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
        // Validate the submitted skill object
        if (errors.hasErrors()) {
            // If there are errors, render the add skill form template again with error messages
            return "skills/add";
        }

        // Save the valid skill object to the database
        skillRepository.save(newSkill);

        // Redirect to the skills index page after adding the skill
        return "redirect:/skills";
    }

    // Display details of a specific skill
    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {
        // Find the skill by its ID
        Optional<Skill> optSkill = skillRepository.findById(skillId);
        if (optSkill.isPresent()) {
            // If the skill is found, add it to the model and render the view skill template
            model.addAttribute("skill", optSkill.get());
            return "skills/view";
        } else {
            // If the skill is not found, redirect to the skills index page
            return "redirect:/skills";
        }
    }

    // No-arg constructor
    public SkillController() {
    }
}

