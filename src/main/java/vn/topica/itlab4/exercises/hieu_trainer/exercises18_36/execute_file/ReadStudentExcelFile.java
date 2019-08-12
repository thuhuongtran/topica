package vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.execute_file;

import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ReadStudentExcelFile extends ReadExcelFile {
    /**
     * Get student-list from inputted excel file
     * Use apache-poi library
     * @param filename
     * @return students
     */
    public List<?> getStudents(String filename) {
        List<Student> students = new ArrayList<Student>();
        String path = "D:/topica/src/main/resources/";
        try {
            FileInputStream file = new FileInputStream(path+filename);
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            sheet.removeRow(sheet.getRow(0));
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Student st = (Student) setPeopleSimpleInformation(row, new Student());
                String subjects = row.getCell(4).getStringCellValue(); // fifth column is subject with score
                st.setScores(subjects);
                String[] subj = subjects.split(","); // subject-name:score split by space
                Map<String, Integer> scores = new HashMap<String, Integer>();
                for (String s : subj) {
                    String[] sc = s.split(":"); // subject-name: score
                    scores.put(sc[0].trim(), Integer.parseInt(sc[1].trim()));
                }
                st.setSubjectScore(scores);
                // maybe set subjects
                students.add(st);
            }
        } catch (Exception e) {
            System.out.printf("Can't get info from excel file. Error: %s", e.getMessage());
            e.printStackTrace();
        }
        return students;
    }

    public List<?> getTeachers(String filename) {
        // do nothing
        return null;
    }

    public List<?> getSubjects(String filename) {
        // do nothing
        return null;
    }
}
