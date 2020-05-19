package az.itstep.crud.dao;

import az.itstep.crud.model.Employee;

import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EmployeeDao {

    private final String username = "postgres";
    private final String password = "admin";
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String driverName = "org.postgresql.Driver";

    private Connection connection;


    public void connect(){
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void disconnect(){
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void save(Employee employee){
        String sql = "insert into employees (name, surname, companyName, salary) values(?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setString(3, employee.getCompanyName());
            statement.setDouble(4, employee.getSalary());

            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Employee> findAll(){
        String sql = "select * from employees";

        List<Employee> employees = new ArrayList<>();

        try(Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery(sql)){
                while (resultSet.next()){
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String companyName = resultSet.getString("companyName");
                    double salary = resultSet.getDouble("salary");

                    Employee employee = new Employee(id, name, surname, companyName, salary);
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee findById(long id){
        String sql = "select * from employees where id = ?";
        Employee employee = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    long comingId = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String companyName = resultSet.getString("companyName");
                    double salary = resultSet.getDouble("salary");

                    employee = new Employee(comingId, name, surname, companyName, salary);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employee;
    }

    public void deleteById(long id){
        String sql = "delete from employees where id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            int resultCount = statement.executeUpdate();
            if(resultCount <= 0)
                System.err.println("No rows found to be deleted");
            else
                System.out.println("Deleted successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void update(Employee employee){
        String sql = "update employees set name = ?, surname = ?, companyName = ?, salary = ? where id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setString(3, employee.getCompanyName());
            statement.setDouble(4, employee.getSalary());
            statement.setLong(5, employee.getId());

            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
