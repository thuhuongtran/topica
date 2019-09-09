package vn.topica.itlab4.exercises.trung_trainer.network_io.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private final static FileUtil instance = new FileUtil();

    private FileUtil() {

    }

    public static FileUtil getInstance() {
        return instance;
    }
    /**
     *  Read from file
     *  Return list of line string
     * @param fileName
     * @return string list
     */
    protected List<String> readLines(String fileName) {
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
     * write each string line to text file
     * @param fileName
     * @param lines
     */
    protected void writeLines(String fileName, List<String> lines) {
        String file = Paths.get("src", "main", "resources", fileName).toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            System.out.println(String.format("Error while writing to text file: %s", ex.getMessage()));
            ex.printStackTrace();
        }
    }
}
