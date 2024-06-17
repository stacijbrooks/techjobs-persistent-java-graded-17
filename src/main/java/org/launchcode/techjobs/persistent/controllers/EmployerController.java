package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    // Autowire the EmployerRepository to handle data operations
    @Autowired
    private EmployerRepository employerRepository;

    // Handle GET requests to /employers/add to display the form for adding a new employer
    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        // Add an empty Employer object to the model to bind form data
        model.addAttribute(new Employer());
        // Return the employers/add view
        return "employers/add";
    }

    // Handle POST requests to /employers/add to process the form submission
    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                         Errors errors, Model model) {
        // Check if there are validation errors
        if (errors.hasErrors()) {
            // If there are errors, return to the add form view
            return "employers/add";
        }
        // Save the new employer to the repository
        employerRepository.save(newEmployer);
        // Redirect to the list of employers
        return "redirect:/employers";
    }

    // Handle GET requests to /employers/view/{employerId} to display a specific employer
    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {
        // Find the employer by ID
        Optional<Employer> optEmployer = employerRepository.findById(employerId);
        // Check if the employer was found
        if (optEmployer.isPresent()) {
            // If found, add the employer to the model
            Employer employer = optEmployer.get();
            model.addAttribute("employer", employer);
            // Return the employers/view view
            return "employers/view";
        } else {
            // If not found, redirect to the list of employers
            return "redirect:/employers";
        }
    }

    // Handle GET requests to /employers to display the list of all employers
    @GetMapping("")
    public String index(Model model) {
        // Add the list of all employers to the model
        model.addAttribute("employers", employerRepository.findAll());
        // Return the employers/index view
        return "employers/index";
    }
}
