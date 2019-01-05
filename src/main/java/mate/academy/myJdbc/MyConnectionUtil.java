package mate.academy.myJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnectionUtil {
    private static Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/developing?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
