package pl.matshead.examapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.matshead.examapp.model.Department;

/**
 * JpaRepository instead of CRUD because after updating row in table,
 * then iterating through them, sorting is missing.
 * So in findAll method now there is a parameter new Sort()
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}

