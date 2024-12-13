package packagee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/insurance?serverTimezone=UTC&useSSL=false";
            String username = "";
            String password = "";
            return DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            System.out.println("Connection failed"+e.getMessage());
            return null;
        }
    }
}
