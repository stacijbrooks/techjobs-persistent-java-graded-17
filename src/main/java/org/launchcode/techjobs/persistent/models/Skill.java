package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;


//Models Task 2 Continued
@Entity
public class Skill extends AbstractEntity {

    private String description;

    //No-arg Constructor
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
