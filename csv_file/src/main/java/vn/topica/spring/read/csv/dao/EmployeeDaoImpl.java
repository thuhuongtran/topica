package vn.topica.spring.read.csv.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import vn.topica.spring.read.csv.entity.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Employee emp) {
        String sql = "INSERT INTO employee (id, name, birth, gender) VALUES (?,?,?,?) ";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getBirth());
            ps.setString(4, emp.getGender());
            ps.executeUpdate();
            System.out.println(String.format("Insert employee id=%d, name=%s,birth=%s,gender=%s into DB successfully.",
                    emp.getId(), emp.getName(), emp.getBirth(), emp.getGender()));
        } catch (Exception exception) {
            System.out.println("Error happened might be caused by duplicate key in DB. Id = "+emp.getId());
        }
    }

    @Override
    public List<Employee> selectAll() {
        String sql = "SELECT * FROM employee";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
