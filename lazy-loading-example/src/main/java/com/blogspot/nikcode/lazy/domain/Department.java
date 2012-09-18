package com.blogspot.nikcode.lazy.domain;

import com.blogspot.nikcode.lazy.proxy.ListLoader;
import com.blogspot.nikcode.lazy.proxy.VirtualList;
import java.util.List;

/**
 *
 * @author nik
 */
public class Department {
    
    private Integer id;
    private String name;
    private List<Employee> employees;

    public Department() {
    }

    public Department(Integer id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
    
    public static Department findById(final Integer id) {
        return new Department(id, "Department name", new VirtualList<Employee>(new ListLoader<Employee>() {

            public List<Employee> load() {
                return Employee.findByDepartmentId(id);
            }
        }));
    }
}
