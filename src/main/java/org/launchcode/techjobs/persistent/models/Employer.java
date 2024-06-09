package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {

    //Models in Task 2

    @NotBlank(message = "Location is required")
    @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters")
    private String location;

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
}
