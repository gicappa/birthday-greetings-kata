package it.xpug.kata.birthday.infrastructure;

import it.xpug.kata.birthday.domain.Employee;
import it.xpug.kata.birthday.domain.EmployeeRepository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CsvEmployeeRepository implements EmployeeRepository {

    private final String fileName;

    public CsvEmployeeRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Employee> findAllEmployees() throws IOException, ParseException {
        var allEmployees = new ArrayList<Employee>();

        var in = new BufferedReader(new FileReader(fileName));

        String line = in.readLine();
        while ((line = in.readLine()) != null) {
            var employee = parseEmployee(line);
            allEmployees.add(employee);
        }
        return allEmployees;
    }

    private static Employee parseEmployee(String str) throws ParseException {
        var employeeData = str.split(", ");

        return new Employee(
            employeeData[1],
            employeeData[0],
            employeeData[2],
            employeeData[3]);
    }

}
