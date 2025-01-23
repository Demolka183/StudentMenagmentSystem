public class Student {
    // Atrybuty klasy Student
    private String name;       // Imię studenta
    private int age;           // Wiek studenta
    private double grade;      // Ocena studenta (0.0 - 100.0)
    private String studentID;  // Unikalny identyfikator studenta

    // Konstruktor do inicjalizacji atrybutów
    public Student(String name, int age, double grade, String studentID) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.studentID = studentID;
    }

    // Getter i Setter dla imienia
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter i Setter dla wieku
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) { // Wiek musi być liczbą dodatnią
            this.age = age;
        } else {
            System.out.println("Wiek musi być liczbą dodatnią.");
        }
    }

    // Getter i Setter dla oceny
    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.0) { // Ocena w zakresie 0.0 - 100.0
            this.grade = grade;
        } else {
            System.out.println("Ocena musi być w zakresie 0.0 - 100.0.");
        }
    }

    // Getter i Setter dla ID studenta
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    // Metoda do wyświetlania informacji o studencie
    public void displayInfo() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Grade: " + grade);
    }
}
