package antelopes.kinoxp.repositories.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final static String USERNAME = "kinoxp";
    private final static String PASSWORD = "Tu267J-Uf-7B";
    private final static String CONNSTRING = "jdbc:mysql://den1.mysql5.gear.host/kinoxp?useSSL=false";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNSTRING, USERNAME, PASSWORD);
    }
}
