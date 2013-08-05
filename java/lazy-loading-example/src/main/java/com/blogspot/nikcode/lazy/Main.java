package com.blogspot.nikcode.lazy;

import com.blogspot.nikcode.lazy.domain.Department;

/**
 *
 * @author nik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Department department = Department.findById(1);
        department.getEmployees().size(); // first attempt will load list
        department.getEmployees().size(); // already loaded
    }
}
