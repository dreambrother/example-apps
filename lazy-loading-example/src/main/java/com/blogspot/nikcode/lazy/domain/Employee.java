package com.blogspot.nikcode.lazy.domain;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author nik
 */
public class Employee {
    
    private Integer id;
    private String name;

    public Employee() {
    }

    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static List<Employee> findByDepartmentId(Integer id) {
        return Collections.singletonList(new Employee(id, "Employee name"));
    }
}
