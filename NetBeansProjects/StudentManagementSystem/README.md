Student Management System

1. Instrukcje dotyczące kompilacji i uruchamiania aplikacji

    Wymagania wstępne:

  -Zainstalowana platforma Java Development Kit (JDK) w wersji 11 lub nowszej.

  -Program NetBeans lub dowolne inne środowisko IDE obsługujące Maven.

  -Zainstalowany Maven (jeśli używasz linii poleceń).

2. Kroki kompilacji i uruchamiania aplikacji

  1 Klonowanie projektu

  Pobierz projekt z repozytorium lub otwórz folder projektu w NetBeans.

  2 Kompilacja projektu

  W NetBeans: Kliknij prawym przyciskiem myszy na projekt i wybierz opcję Clean and Build.

  Za pomocą Mavena: Uruchom następujące polecenie w terminalu w folderze projektu:

    mvn clean package

  3 Uruchomienie aplikacji

  W NetBeans: Kliknij prawym przyciskiem myszy na projekt i wybierz opcję Run.

  Za pomocą terminala: Uruchom plik jar wygenerowany w folderze target:

    java -jar target/StudentManagementSystem-1.0.jar

  3. Przegląd funkcjonalności

System zarządzania studentami pozwala na wykonywanie następujących operacji:

1 Dodawanie studenta:

Użytkownik może dodać studenta, podając unikalny numer ID, imię, wiek i ocenę.

2 Usuwanie studenta:

Użytkownik może usunąć studenta na podstawie numeru ID.

3 Aktualizowanie danych studenta:

Użytkownik może zmienić dane istniejącego studenta, takie jak imię, wiek lub ocena.

4 Wyświetlanie wszystkich studentów:

System pozwala na wyświetlenie listy wszystkich studentów w bazie danych.

5 Obliczanie średniej ocen:

System oblicza i wyświetla średnią ocen wszystkich studentów w bazie danych.

Instrukcje dotyczące konfiguracji bazy danych

  4. Wymagania bazy danych

Aplikacja korzysta z bazy danych SQLite. Plik bazy danych students.db znajduje się w katalogu głównym projektu.

  5. Automatyczne tworzenie tabeli

Aplikacja automatycznie utworzy tabelę students, jeśli ta nie istnieje, przy pierwszym uruchomieniu.

 6. Testowanie połączenia z bazą danych

    1 Otwórz plik DatabaseConnection.java.

    2 Sprawdź, czy ścieżka do bazy danych (jdbc:sqlite:students.db) jest poprawna.

    3 Uruchom metodę connect() z klasy DatabaseConnection, aby upewnić się, że połączenie z bazą danych działa prawidłowo.

  7. Debugowanie problemów z bazą danych

  Jeśli napotkasz problemy z połączeniem lub działaniem bazy danych:

  -Sprawdź, czy plik students.db istnieje w katalogu głównym projektu.

  -Sprawdź dzienniki konsoli pod kątem komunikatów o błędach z klasy DatabaseConnection.

  -Zweryfikuj, czy masz uprawnienia do zapisu w katalogu projektu.
