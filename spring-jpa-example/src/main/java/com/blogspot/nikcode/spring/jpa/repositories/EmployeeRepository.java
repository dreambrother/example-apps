package com.blogspot.nikcode.spring.jpa.repositories;

import com.blogspot.nikcode.spring.jpa.domain.Employee;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    
    List<Employee> findByName(String name);
    List<Employee> findByNameAndSalary(String name, Integer salary);
}
