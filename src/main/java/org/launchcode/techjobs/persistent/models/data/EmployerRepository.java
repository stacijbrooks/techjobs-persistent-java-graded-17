package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


//Data Layer in Task 2
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
//    Optional<Employer> findById(int employerId);

}
