package db;

import java.sql.*;
import java.util.Scanner;

public class DatabaseOperations {

    // 1. Adăugarea unei persoane
    public void adaugaPersoana() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți numele persoanei: ");
        String nume = scanner.nextLine();
        System.out.println("Introduceți vârsta persoanei: ");
        int varsta = scanner.nextInt();
        scanner.nextLine(); // consumă linia goală

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO persoane (nume, varsta) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nume);
                stmt.setInt(2, varsta);
                stmt.executeUpdate();
                System.out.println("Persoana a fost adăugată cu succes.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Adăugarea unei excursii
    public void adaugaExcursie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți ID-ul persoanei: ");
        int idPersoana = scanner.nextInt();
        scanner.nextLine(); // consumă linia goală
        System.out.println("Introduceți destinația excursiei: ");
        String destinatie = scanner.nextLine();
        System.out.println("Introduceți anul excursiei: ");
        int an = scanner.nextInt();

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Verifică dacă persoana există
            String checkQuery = "SELECT id FROM persoane WHERE id = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, idPersoana);
                ResultSet rs = checkStmt.executeQuery();
                if (!rs.next()) {
                    System.out.println("Persoana nu există.");
                    return;
                }
            }

            String query = "INSERT INTO excursii (id_persoana, destinatia, anul) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, idPersoana);
                stmt.setString(2, destinatie);
                stmt.setInt(3, an);
                stmt.executeUpdate();
                System.out.println("Excursia a fost adăugată cu succes.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Afișarea tuturor persoanelor și excursiilor
    public void afiseazaPersoaneSiExcursii() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT p.nume, p.varsta, e.destinatia, e.anul FROM persoane p "
                    + "JOIN excursii e ON p.id = e.id_persoana";
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String nume = rs.getString("nume");
                    int varsta = rs.getInt("varsta");
                    String destinatie = rs.getString("destinatia");
                    int an = rs.getInt("anul");
                    System.out.println("Persoana: " + nume + ", Vârsta: " + varsta + ", Excursia: " + destinatie + ", Anul: " + an);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Afișarea excursiilor unei persoane
    public void afiseazaExcursiiPersoana() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți numele persoanei: ");
        String nume = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT e.destinatia, e.anul FROM excursii e "
                    + "JOIN persoane p ON e.id_persoana = p.id WHERE p.nume = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nume);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        do {
                            String destinatie = rs.getString("destinatia");
                            int an = rs.getInt("anul");
                            System.out.println("Destinația: " + destinatie + ", Anul: " + an);
                        } while (rs.next());
                    } else {
                        System.out.println("Persoana nu a efectuat nici o excursie.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. Afișarea persoanelor care au vizitat o destinație
    public void afiseazaPersoaneDestinatie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți destinația: ");
        String destinatie = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT p.nume FROM persoane p "
                    + "JOIN excursii e ON p.id = e.id_persoana WHERE e.destinatia = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, destinatie);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        do {
                            String nume = rs.getString("nume");
                            System.out.println("Persoana: " + nume);
                        } while (rs.next());
                    } else {
                        System.out.println("Nu există persoane care au vizitat această destinație.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 6. Afișarea persoanelor care au făcut excursii într-un an
    public void afiseazaPersoaneAn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți anul excursiei: ");
        int an = scanner.nextInt();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT p.nume FROM persoane p "
                    + "JOIN excursii e ON p.id = e.id_persoana WHERE e.anul = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, an);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        do {
                            String nume = rs.getString("nume");
                            System.out.println("Persoana: " + nume);
                        } while (rs.next());
                    } else {
                        System.out.println("Nu există persoane care au făcut excursii în acest an.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 7. Ștergerea unei excursii
    public void stergeExcursie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți ID-ul excursiei pe care doriți să o ștergeți: ");
        int idExcursie = scanner.nextInt();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM excursii WHERE id_excursie = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, idExcursie);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Excursia a fost ștearsă.");
                } else {
                    System.out.println("Nu s-a găsit excursia cu acest ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 8. Ștergerea unei persoane (și a excursiilor sale)
    public void stergePersoana() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți ID-ul persoanei pe care doriți să o ștergeți: ");
        int idPersoana = scanner.nextInt();

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Șterge excursiile asociate persoanei
            String deleteExcursiiQuery = "DELETE FROM excursii WHERE id_persoana = ?";
            try (PreparedStatement stmt = connection.prepareStatement(deleteExcursiiQuery)) {
                stmt.setInt(1, idPersoana);
                stmt.executeUpdate();
            }

            // Șterge persoana
            String deletePersoanaQuery = "DELETE FROM persoane WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(deletePersoanaQuery)) {
                stmt.setInt(1, idPersoana);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Persoana și excursiile sale au fost șterse.");
                } else {
                    System.out.println("Nu s-a găsit persoana cu acest ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
