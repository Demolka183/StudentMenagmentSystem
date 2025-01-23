import java.util.ArrayList;

public interface StudentManager {
    // Dodaje nowego studenta do systemu
    void addStudent(Student student);

    // Usuwa studenta na podstawie jego ID
    void removeStudent(String studentID);

    // Aktualizuje dane istniejącego studenta
    void updateStudent(String studentID, Student updatedStudent);

    // Wyświetla wszystkich studentów
    ArrayList<Student> displayAllStudents();

    // Oblicza i zwraca średnią ocen wszystkich studentów
    double calculateAverageGrade();
}

