package com.blogspot.nikcode.spring.jpa.repositories;

import com.blogspot.nikcode.spring.jpa.domain.Employee;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-jpa-context.xml"})
@Transactional
public class EmployeeRepositoryTest {
    
    @Autowired
    private EmployeeRepository repository;
    
    @Test
    public void testSaveAndGet() {
        // create employees
        final int employeesCount = 10;
        List<Employee> employees = new ArrayList<Employee>(employeesCount);
        for (int i = 0; i < employeesCount; i++) {
            employees.add(new Employee("Employee-" + i, i * 10000));
        }
        
        // save employees to db
        for (Employee employee : employees) {
            repository.save(employee);
        }
        
        // check db data
        Iterable<Employee> actualEmployees = repository.findAll();
        int idx = 0;
        for (Employee actualEmployee : actualEmployees) {
            assertEquals(employees.get(idx), actualEmployee);
            idx++;
        }
    }
    
    @Test
    public void testCustomFinders() {
        // save employees
        int employeesCount = 4;
        String name = "Employee Name";
        Integer salary = 10000;
        for (int i = 0; i < employeesCount; i++) {
            repository.save(new Employee(name, i * salary));
        }
        
        // check that repository works correctly
        List<Employee> actualEmployees = repository.findByName(name);
        assertEquals(employeesCount, actualEmployees.size());
        for (Employee employee : actualEmployees) {
            assertEquals(name, employee.getName());
        }
            
        actualEmployees = repository.findByNameAndSalary(name, salary);
        assertEquals(1, actualEmployees.size());
        for (Employee employee : actualEmployees) {
            assertEquals(name, employee.getName());
            assertEquals(salary, employee.getSalary());
        }
    }
}
