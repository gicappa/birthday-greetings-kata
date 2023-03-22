package it.xpug.kata.birthday.infrastructure;

import it.xpug.kata.birthday.domain.Employee;
import it.xpug.kata.birthday.domain.EmployeeRepository;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvEmployeeRepository implements EmployeeRepository {

    private final BufferedReader in;

    public CsvEmployeeRepository(String fileName) throws FileNotFoundException {
        this.in = new BufferedReader(new FileReader(fileName));
    }

    @Override
    public List<Employee> findAllEmployees() {
        try {
            var allEmployees = new ArrayList<Employee>();

            skipLine();

            for (var line = in.readLine(); line != null; line = in.readLine()) {
                var employee = parseEmployee(line);
                allEmployees.add(employee);
            }

            return allEmployees;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void skipLine() throws IOException {
        in.readLine();
    }

    private static Employee parseEmployee(String str) {
        var employeeData = str.split(", ");

        return new Employee(
            employeeData[1],
            employeeData[0],
            employeeData[2],
            employeeData[3]);
    }

}
