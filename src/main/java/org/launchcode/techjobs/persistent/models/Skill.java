package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


//Models Task 2 Continued
@Entity
public class Skill extends AbstractEntity {

    @NotBlank(message = "Description is required")
    @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters")
    private String description;

    //No-arg Constructor required by Hibernate
    public Skill() {
    }

    //Getters and Setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
}
