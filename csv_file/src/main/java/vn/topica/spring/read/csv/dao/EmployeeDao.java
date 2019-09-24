package vn.topica.spring.read.csv.dao;

import vn.topica.spring.read.csv.entity.Employee;

import java.util.List;

public interface EmployeeDao {
     void insert(Employee student);

     List<Employee> selectAll();
}
