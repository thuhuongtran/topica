package vn.topica.spring.read.csv.utils;

import vn.topica.spring.read.csv.entity.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IoUtil {
    public final static IoUtil INSTANCE = new IoUtil();
    private final String FILE_NAME = "spring_csv.csv";

    private IoUtil() {
    }

    /**
     * Read from file
     * Return list of line string
     *
     * @param fileName
     * @return string list
     */
    private List<String> readLines(String fileName) {
        Path file = Paths.get("src", "main", "resources", fileName);
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                list.add(line);
            }
        } catch (IOException ex) {
            System.out.println(String.format("Error while reading from txt file: %s", ex.getMessage()));
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * employeeStr is format of id,name,birth,gender
     *
     * @param empStr
     * @return machine object
     */
    private Employee getEmployee(String empStr) {
        String[] strs = empStr.split(",");
        Employee emp = new Employee(Integer.parseInt(strs[0].trim()), // id
                strs[1].trim(), // name
                strs[2].trim(), // date
                strs[3].trim()); // gender
        return emp;
    }

    public List<Employee> getEmployees() {
        List<Employee> emps = new ArrayList<>();
        List<String> strs = readLines(FILE_NAME);
        strs.remove(0);
        strs.forEach(s -> emps.add(getEmployee(s)));
        return emps;
    }
}
