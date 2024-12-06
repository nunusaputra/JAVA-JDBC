package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_jobPortal2", "root",
                    "Nuu@saputra17");

            System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println("Connection aborted");
            e.printStackTrace();
        }

        return connection;
    }
}
