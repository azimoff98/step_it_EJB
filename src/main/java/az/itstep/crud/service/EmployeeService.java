package az.itstep.crud.service;

import az.itstep.crud.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(long id);
    void save(Employee employee);
    void deleteById(long id);
    void update(Employee employee);
}
