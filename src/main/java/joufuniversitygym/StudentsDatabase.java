package joufuniversitygym;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsDatabase {
    private List<Student> students;
    private static final String DATABASE_FILE = "students_database.dat";

    public StudentsDatabase() {
        students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public boolean updateStudent(String universityId, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getUniversityId().equals(universityId)) {
                students.set(i, updatedStudent);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String universityId) {
        boolean removed = students.removeIf(s -> s.getUniversityId().equals(universityId));
        if (removed) {
            saveToFile();
        }
        return removed;
    }

    public Student findStudent(String universityId) {
        for (Student student : students) {
            if (student.getUniversityId().equals(universityId)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public List<Student> getStudentsByClub(String club) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getClub().equals(club)) {
                result.add(student);
            }
        }
        return result;
    }

    public int getTotalStudents() {
        return students.size();
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATABASE_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(DATABASE_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATABASE_FILE))) {
                students = (List<Student>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading from file: " + e.getMessage());
                students = new ArrayList<>();
            }
        }
    }

    public void clearDatabase() {
        students.clear();
        saveToFile();
    }
}
