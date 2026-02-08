package joufuniversitygym;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileManager {
    private static final String FILE_PATH = "students.txt";

    public static void saveStudent(String id, String name, String year, String major, String club) throws IOException {
        String data = id + "," + name + "," + year + "," + major + "," + club;
        Files.write(Paths.get(FILE_PATH), (data + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static List<String> readAllStudents() throws IOException {
        if (!Files.exists(Paths.get(FILE_PATH))) {
            return new ArrayList<>();
        }
        return Files.readAllLines(Paths.get(FILE_PATH));
    }

    public static boolean updateStudent(String id, String name, String year, String major, String club) throws IOException {
        List<String> lines = readAllStudents();
        boolean found = false;
        
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith(id + ",")) {
                lines.set(i, id + "," + name + "," + year + "," + major + "," + club);
                found = true;
                break;
            }
        }
        
        if (found) {
            Files.write(Paths.get(FILE_PATH), lines);
        }
        return found;
    }

    public static boolean deleteStudent(String id) throws IOException {
        List<String> lines = readAllStudents();
        boolean removed = lines.removeIf(line -> line.startsWith(id + ","));
        
        if (removed) {
            Files.write(Paths.get(FILE_PATH), lines);
        }
        return removed;
    }
}
