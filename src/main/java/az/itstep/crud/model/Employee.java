package az.itstep.crud.model;

public class Employee {
    private long id;
    private String name;
    private String surname;
    private String companyName;
    private double salary;


    public Employee() {
    }

    public Employee(long id, String name, String surname, String companyName, double salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.companyName = companyName;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
