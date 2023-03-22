package it.xpug.kata.birthday.domain;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAllEmployees() throws IOException, ParseException;
}
