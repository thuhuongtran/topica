package vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.execute_file;

import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity.Teacher;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadTeacherExcelFile extends ReadExcelFile {
    public List<?> getStudents(String filename) {
        // do nothing
        return null;
    }

    /**
     * Get teacher-list from inputted excel file
     * Use apache-poi library
     * @param filename
     * @return teachers
     */
    public List<?> getTeachers(String filename) {
        List<Teacher> teachers = new ArrayList<Teacher>();
        String path = "D:/topica/src/main/resources/";
        try {
            FileInputStream file = new FileInputStream(path+filename);
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            sheet.removeRow(sheet.getRow(0));
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Teacher teacher = (Teacher) setPeopleSimpleInformation(row, new Teacher());
                String subjects = row.getCell(4).getStringCellValue(); // fifth column is subjects
                teacher.setSubjects(subjects);
                teachers.add(teacher);
            }
        } catch (Exception e) {
            System.out.printf("Can't get info from excel file. Error: %s", e.getMessage());
            e.printStackTrace();
        }
        return teachers;
    }

    public List<?> getSubjects(String filename) {
        // do nothing
        return null;
    }
}
