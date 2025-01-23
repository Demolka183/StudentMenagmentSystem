import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentManagerImpl implements StudentManager {

    private final StudentManagementGUI gui;

    public StudentManagerImpl(StudentManagementGUI gui) {
        this.gui = gui; // Referencja do GUI, aby wyświetlać komunikaty
    }

    // Dodaje nowego studenta
    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students(studentID, name, age, grade) VALUES(?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Walidacja danych wejściowych
            if (student.getStudentID() == null || student.getStudentID().trim().isEmpty()) {
                gui.showErrorMessage("Numer indeksu (Student ID) nie może być pusty.");
                return;
            }
            try {
                int parsedID = Integer.parseInt(student.getStudentID());
                if (parsedID <= 0) {
                    gui.showErrorMessage("Numer indeksu musi być liczbą dodatnią.");
                    return;
                }
            } catch (NumberFormatException e) {
                gui.showErrorMessage("Numer indeksu musi być liczbą.");
                return;
            }

            if (student.getAge() <= 0) {
                gui.showErrorMessage("Wiek musi być większy od 0.");
                return;
            }
            if (student.getGrade() < 0 || student.getGrade() > 100) {
                gui.showErrorMessage("Ocena musi być w zakresie 0-100.");
                return;
            }

            // Jeśli wszystkie dane są poprawne, wykonujemy zapytanie SQL
            statement.setString(1, student.getStudentID());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setDouble(4, student.getGrade());
            statement.executeUpdate();
            gui.showSuccessMessage("Dodano studenta o numerze indeksu " + student.getStudentID());

        } catch (SQLException e) {
            if (e.getMessage().contains("constraint failed")) {
                gui.showErrorMessage("Student o numerze indeksu " + student.getStudentID() + " już istnieje.");
            } else {
                gui.showErrorMessage("Błąd podczas dodawania studenta: " + e.getMessage());
            }
        }
    }

    // Usuwa studenta na podstawie ID
    @Override
    public void removeStudent(String studentID) {
        String sql = "DELETE FROM students WHERE studentID = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            if (studentID == null || studentID.trim().isEmpty()) {
                gui.showErrorMessage("Numer indeksu (Student ID) nie może być pusty.");
                return;
            }

            statement.setString(1, studentID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                gui.showSuccessMessage("Usunięto studenta o numerze indeksu " + studentID);
            } else {
                gui.showErrorMessage("Nie znaleziono studenta o numerze indeksu " + studentID);
            }
        } catch (SQLException e) {
            gui.showErrorMessage("Błąd podczas usuwania studenta: " + e.getMessage());
        }
    }

    // Aktualizuje dane studenta
    @Override
    public void updateStudent(String studentID, Student updatedStudent) {
        String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Walidacja numeru indeksu
            if (studentID == null || studentID.trim().isEmpty()) {
                gui.showErrorMessage("Numer indeksu (Student ID) nie może być pusty.");
                return;
            }
            try {
                int parsedID = Integer.parseInt(studentID);
                if (parsedID <= 0) {
                    gui.showErrorMessage("Numer indeksu musi być liczbą dodatnią.");
                    return;
                }
            } catch (NumberFormatException e) {
                gui.showErrorMessage("Numer indeksu musi być liczbą.");
                return;
            }

            // Walidacja danych studenta
            if (updatedStudent.getAge() <= 0) {
                gui.showErrorMessage("Wiek musi być większy od 0.");
                return;
            }
            if (updatedStudent.getGrade() < 0 || updatedStudent.getGrade() > 100) {
                gui.showErrorMessage("Ocena musi być w zakresie 0-100.");
                return;
            }

            statement.setString(1, updatedStudent.getName());
            statement.setInt(2, updatedStudent.getAge());
            statement.setDouble(3, updatedStudent.getGrade());
            statement.setString(4, studentID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                gui.showSuccessMessage("Zaktualizowano dane studenta o numerze indeksu " + studentID);
            } else {
                gui.showErrorMessage("Student o numerze indeksu " + studentID + " nie istnieje.");
            }
        } catch (SQLException e) {
            gui.showErrorMessage("Błąd podczas aktualizacji studenta: " + e.getMessage());
        }
    }

    // Wyświetla wszystkich studentów
    @Override
    public ArrayList<Student> displayAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String studentID = resultSet.getString("studentID");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double grade = resultSet.getDouble("grade");
                students.add(new Student(name, age, grade, studentID));
            }

            if (students.isEmpty()) {
                gui.showErrorMessage("Brak studentów w bazie danych.");
            } else {
                gui.showSuccessMessage("Lista studentów została załadowana.");
            }
        } catch (SQLException e) {
            gui.showErrorMessage("Błąd podczas pobierania studentów: " + e.getMessage());
        }
        return students;
    }

    // Oblicza średnią ocen
    @Override
    public double calculateAverageGrade() {
        String sql = "SELECT AVG(grade) AS average FROM students";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble("average");
            }
        } catch (SQLException e) {
            gui.showErrorMessage("Błąd podczas obliczania średniej ocen: " + e.getMessage());
        }
        return 0.0;
    }
}