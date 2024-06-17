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
    @GetMapping("/")
    public String index(Model model) {
        //model.addAttribute("title", "All Skills");
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }

    // Display form to add a new skill
    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        // Add a new empty Skill object to the model and render the add skill form template
        model.addAttribute(new Skill());
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/add";
    }

    // Process form submission to add a new skill
    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                      Errors errors, Model model) {

        //Setting up the model attributes for displaying the 'Add Skill' form, including handling validation errors.E
        if (errors.hasErrors()) {
            return "skills/add";
        }

            // Save the valid skill object to the database
            skillRepository.save(newSkill);
            return "redirect:";
        }

        // Display details of a specific skill
        // Mapping HTTP GET requests to "/view/{skillId}" to this method
        @GetMapping("view/{skillId}")
        public String displayViewSkill (Model model,@PathVariable int skillId) {

            // Retrieve the skill from the database using the skillId parameter
            Optional<Skill> optSkill = skillRepository.findById(skillId);

            // Check if the skill exists in the database
            if (optSkill.isPresent()) {
                // If the skill is found, retrieve it from the Optional
                Skill skill = (Skill) optSkill.get();

                // Add the retrieved skill to the model attribute "skill"
                model.addAttribute("skill", skill);

                // Return the name of the view template to display the skill details
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


