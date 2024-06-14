package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    //Task 4: Update HomeController skillRepository
    @Autowired
    private SkillRepository skillRepository;



    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");
        return "index";
    }

    //Updated & Modified for Task 3
    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }


    //Updated & Modified for Task 3 and updated for Task 4.
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model,
                                    @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }

        // Retrieve the selected employer using the employerId. Updated during Task 4.
        Optional<Employer> employerResult = employerRepository.findById(employerId);
        if (employerResult.isEmpty()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            model.addAttribute("employerError", "Invalid employer ID");
            return "add";

//        Optional<Employer> employerResult = employerRepository.findById(employerId);
//        if (employerResult.isPresent()) {
//            Employer employer = employerResult.get();
//            // Assign the retrieved employer to the newJob object
//            newJob.setEmployer(employer);
//        } else {
//            model.addAttribute("title", "Add Job");
//            model.addAttribute("employers", employerRepository.findAll());
//            model.addAttribute("skills", skillRepository.findAll());
//            return "add";
        }

        //Task 4: Added this to HomeController
        Employer employer = employerResult.get();
        newJob.setEmployer(employer);

        // Retrieve the selected skills using the list of skill IDs
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        // Assign the retrieved skills to the newJob object
        newJob.setSkills(skillObjs);

        // Save the new job to the database
        jobRepository.save(newJob);
        return "redirect:";
    }

    //Updated for Task 4.
    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> jobResult = jobRepository.findById(jobId);
        if (jobResult.isEmpty()) {
            model.addAttribute("title", "Invalid Job ID");
            return "redirect:/";
        }

        Job job = jobResult.get();
        model.addAttribute("job", job);
        return "view";
    }
}
