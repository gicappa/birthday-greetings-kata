package it.xpug.kata.birthday.domain;

import java.util.List;

/**
 * The interface that hides the possible implementation
 * on how to retrieve the employee list
 */
public interface EmployeeRepository {

    /**
     * @return all the employees of a company
     */
    List<Employee> findAllEmployees();
}
