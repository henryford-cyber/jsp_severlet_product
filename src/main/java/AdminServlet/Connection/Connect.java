package AdminServlet.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
public class Connect { 
 
public static String userName = "root";
	public static Connection connection=null;
    public static String password = "";
    public static String dbURL = "jdbc:mysql://localhost:3306/javaweb";
    public static Connection ConnectDb(){ 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("Ket noi thanh cong");
        } catch (Exception e) {
            System.out.println("ket noi khong thanh cong");
        }
        return connection;
    }
    public static void main(String[] args) {
        ConnectDb();
    }
}