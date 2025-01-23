import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Ścieżka do bazy danych SQLite
    private static final String URL = "jdbc:sqlite:students.db";

    // Metoda do nawiązania połączenia z bazą danych
    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Połączono z bazą danych.");
        } catch (SQLException e) {
            System.out.println("Błąd podczas łączenia z bazą danych: " + e.getMessage());
        }
        return connection;
    }

    // Metoda do tworzenia tabeli 'students', jeśli nie istnieje
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                     "studentID TEXT PRIMARY KEY, " +
                     "name TEXT NOT NULL, " +
                     "age INTEGER NOT NULL, " +
                     "grade REAL NOT NULL)";
        try (Connection connection = connect();
             var statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Tabela 'students' została utworzona.");
        } catch (SQLException e) {
            System.out.println("Błąd podczas tworzenia tabeli: " + e.getMessage());
        }
    }
}

