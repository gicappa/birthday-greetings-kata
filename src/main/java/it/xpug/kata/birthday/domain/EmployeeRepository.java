package it.xpug.kata.birthday.domain;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAllEmployees();
}
