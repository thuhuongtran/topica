package vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.execute_file;

import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity.Subject;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ReadSubjectExcelFile extends ReadExcelFile {
    public List<?> getStudents(String filename) {
        // do nothing
        return null;
    }

    public List<?> getTeachers(String filename) {
        // do nothing
        return null;
    }

    /**
     * Get subject-list from inputted excel file
     * Use apache-poi library
     * @param filename
     * @return subjects
     */
    public List<?> getSubjects(String filename) {
        List<Subject> subjects = new ArrayList<Subject>();
        String path = "D:/topica/src/main/resources/";
        try {
            FileInputStream file = new FileInputStream(path+filename);
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            sheet.removeRow(sheet.getRow(0));
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String subjectName = row.getCell(0).getStringCellValue(); // first column is subject-name
                Date startDate = row.getCell(1).getDateCellValue(); // second column is start-date
                Date endDate = row.getCell(2).getDateCellValue(); // third column is end-date
                Subject subject = new Subject(subjectName, startDate, endDate);

                subjects.add(subject);
            }
        } catch (Exception e) {
            System.out.printf("Can't get info from excel file. Error: %s", e.getMessage());
        }
        return subjects;
    }
}
