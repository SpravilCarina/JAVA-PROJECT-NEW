package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/bd_lab8"; // înlocuiește cu datele tale
    private static final String USER = "root"; // înlocuiește cu utilizatorul tău
    private static final String PASSWORD = "Ingeras1"; // înlocuiește cu parola ta

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
