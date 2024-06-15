package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    //Task 3. Add a job class to employer. Needs name parameter
    @OneToMany
    @JoinColumn(name = "employer_id")
    private List<Job> jobs = new ArrayList<>();

    //Models in Task 2
    @NotBlank(message = "Location is required")
    @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters")
    private String location;

//    //Mapping to job entities. Task 2, Controller #4
//    @OneToMany(mappedBy = "employer")
//    //join id field
//    private List<Job> jobs;

    //No-arg constructor
    public Employer() {

    }

    //Getters and Setters

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }


}
