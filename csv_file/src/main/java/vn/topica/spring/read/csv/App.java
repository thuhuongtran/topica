package vn.topica.spring.read.csv;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vn.topica.spring.read.csv.dao.EmployeeDao;
import vn.topica.spring.read.csv.entity.Employee;
import vn.topica.spring.read.csv.utils.IoUtil;

import java.util.List;

public class App {
    private static IoUtil io = IoUtil.INSTANCE;
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringConfig.xml");
        EmployeeDao empDao = (EmployeeDao) context.getBean("employeeDao");
        List<Employee> emps = io.getEmployees();
        emps.forEach(e -> empDao.insert(e));
    }
}
