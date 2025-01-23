import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementGUI {
    private JFrame frame;
    private JTextField studentIDField, nameField, ageField, gradeField;
    private JTextArea outputArea;
    private StudentManagerImpl studentManager;

    public StudentManagementGUI() {
        // Inicjalizacja menedżera studentów
        studentManager = new StudentManagerImpl(this);

        // Tworzenie głównego okna
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Panel wejściowy (input) z polami tekstowymi
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("Student ID:"));
        studentIDField = new JTextField();
        inputPanel.add(studentIDField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        // Panel z przyciskami
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton updateButton = new JButton("Update Student");
        JButton displayButton = new JButton("Display All Students");
        JButton averageButton = new JButton("Calculate Average");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(averageButton);

        // Panel wyjściowy (output)
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Dodanie paneli do głównego okna
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Obsługa zdarzeń
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });

        averageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAverageGrade();
            }
        });

        // Wyświetlanie okna
        frame.setVisible(true);
    }

    private void addStudent() {
        try {
            String studentID = studentIDField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double grade = Double.parseDouble(gradeField.getText());

            // Walidacja pola Name
            if (!name.matches("[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ ]+")) {
                showErrorMessage("Błąd: Imię i nazwisko mogą zawierać tylko litery i spacje.");
                return;
            }

            // Tworzenie nowego obiektu Student i dodanie do systemu
            Student student = new Student(name, age, grade, studentID);
            studentManager.addStudent(student);
        } catch (NumberFormatException e) {
            showErrorMessage("Błąd: Niepoprawne dane dla wieku lub oceny.");
        }
    }

    private void removeStudent() {
        String studentID = studentIDField.getText();
        studentManager.removeStudent(studentID);
    }

    private void updateStudent() {
        try {
            String studentID = studentIDField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double grade = Double.parseDouble(gradeField.getText());

            // Walidacja pola Name
            if (!name.matches("[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ ]+")) {
                showErrorMessage("Błąd: Imię i nazwisko mogą zawierać tylko litery i spacje.");
                return;
            }

            Student student = new Student(name, age, grade, studentID);
            studentManager.updateStudent(studentID, student);
        } catch (NumberFormatException e) {
            showErrorMessage("Błąd: Niepoprawne dane dla wieku lub oceny.");
        }
    }

    private void displayAllStudents() {
        outputArea.setText("Lista wszystkich studentów:\n");
        for (Student student : studentManager.displayAllStudents()) {
            outputArea.append(student.getStudentID() + " - " + student.getName() + " - Age: " + student.getAge() + " - Grade: " + student.getGrade() + "\n");
        }
    }

    private void calculateAverageGrade() {
        double average = studentManager.calculateAverageGrade();
        outputArea.append("Średnia ocen: " + average + "\n");
    }

    // Metody do wyświetlania komunikatów w GUI
    public void showErrorMessage(String message) {
        outputArea.append("Błąd: " + message + "\n");
    }

    public void showSuccessMessage(String message) {
        outputArea.append("Sukces: " + message + "\n");
    }

    public static void main(String[] args) {
        new StudentManagementGUI();
    }
}