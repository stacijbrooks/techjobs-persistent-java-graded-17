package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Skill;
import org.springframework.data.repository.CrudRepository;


//Data Layer Task 2
public interface SkillRepository extends CrudRepository<Skill, Integer> {
}
