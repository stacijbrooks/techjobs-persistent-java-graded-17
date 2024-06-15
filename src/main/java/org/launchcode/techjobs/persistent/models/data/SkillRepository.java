package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//Data Layer Task 2
@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {
}
