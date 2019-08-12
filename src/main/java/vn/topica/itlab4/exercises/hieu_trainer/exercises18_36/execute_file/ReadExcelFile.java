package vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.execute_file;

import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity.People;
import org.apache.poi.ss.usermodel.Row;

import java.util.Date;
import java.util.List;

/**
 * Exercise 22-23-24-26:
 * Viết chương trình cho phép nhập thông tin các đối tượng Subject
 * Viết chương trình cho phép nhập thông tin các đối tượng Student
 * Viết chương trình cho phép nhập thông tin các đối tượng Teacher
 * Viết chương trình cho phép lấy dữ liệu từ file trên đưa vào các đối tượng Subject,
 *  Student, Teacher sau khi sửa dữ liệu trên file qua một công cụ khác như Notepad++.
 *
 * Input: Excel file which is typed by user
 * Output: List of Student or teacher or subject which has simple information
 * get list of student and list of teacher from excel file
 */
public abstract class ReadExcelFile {

    public abstract List<?> getStudents(String filename);

    public abstract List<?> getTeachers(String filename);

    public abstract List<?> getSubjects(String filename);

    /**
     * read excel file then get list data of student or teacher or subject
     * @param fileName
     * @return List<?> - students | teachers | subjects
     */
    public final List<?> readFile(String fileName) {
        fileName = "D:/topica/src/main/resources/"+fileName;
        if (fileName.contains("student")) {
            return getStudents(fileName);
        } else if (fileName.contains("teacher")) {
            return getTeachers(fileName);
        } else if (fileName.contains("subject")) {
            return getSubjects(fileName);
        }
        return null;
    }

    /**
     * Set simple people information - firstName, lastName, birth, place
     * @param row
     * @param people
     * @return people
     */
    public People setPeopleSimpleInformation(Row row, People people) {
        String firstName = row.getCell(0).getStringCellValue(); // first column is firstName
        String lastName = row.getCell(1).getStringCellValue(); // second column is lastName
        Date birth = row.getCell(2).getDateCellValue(); // third column is date-of-birth
        String address = row.getCell(3).getStringCellValue(); // forth column is place
        people.setSimpleInformation(firstName, lastName, birth, address);
        return people;
    }
}
