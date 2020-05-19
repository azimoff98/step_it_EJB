package az.itstep.crud.service.impl;

import az.itstep.crud.dao.EmployeeDao;
import az.itstep.crud.exception.DomainUpdateException;
import az.itstep.crud.model.Employee;
import az.itstep.crud.service.EmployeeService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeDao employeeDao;

    public List<Employee> findAll() {
        employeeDao.connect();
        List<Employee> employees = employeeDao.findAll();
        employeeDao.disconnect();
        return employees;
    }

    public Employee findById(long id) {
        employeeDao.connect();
        Employee employee = employeeDao.findById(id);
        employeeDao.disconnect();
        return employee;
    }

    public void save(Employee employee) {
        employeeDao.connect();
        employeeDao.save(employee);
        employeeDao.disconnect();
    }

    public void deleteById(long id) {
        employeeDao.connect();
        employeeDao.deleteById(id);
        employeeDao.disconnect();
    }

    public void update(Employee employee) {
        if(employee.getId() != 0){
            employeeDao.connect();
            employeeDao.update(employee);
            employeeDao.disconnect();
        }else {
            throw new DomainUpdateException("No Id found please provide id");
        }
    }
}
