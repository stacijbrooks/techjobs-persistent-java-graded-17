package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


//Models Task 2 Continued
@Entity
public class Skill extends AbstractEntity {

    @NotBlank(message = "Description is required")
    @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters")
    private String description;


    //Task 4. Creating Job within Skill class (Skill.jobs)
    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();

    //No-arg Constructor required by Hibernate
    public Skill() {
    }

    //Getters and Setters

    public String getDescription() {

        return description;
    }

    public List<Job> getJobs() {

        return jobs;
    }

    public void setJobs(List<Job> jobs) {

        this.jobs = jobs;
    }

    public void setDescription(String description) {

        this.description = description;


    }
}
