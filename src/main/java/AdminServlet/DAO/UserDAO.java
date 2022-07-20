package AdminServlet.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AdminServlet.Connection.Connect;
import AdminServlet.Model.User;

 
	public class UserDAO {
 

	    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, role ,password) VALUES "
	            + " (?, ?, ?, ?);";

	    private static final String SELECT_USER_BY_ID = "select id,name,email,role,password from users where id =?";
	    private static final String SELECT_ALL_USERS = "select * from users";
	    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, role =?, password=? where id = ?;";

	    public UserDAO() {
	    }

 
	    public void insertUser(User user) throws SQLException {
	        System.out.println(INSERT_USERS_SQL);
	        Connection connection=null;
	        
	        connection =Connect.ConnectDb();
	        try (
	             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
	            preparedStatement.setString(1, user.getName());
	            preparedStatement.setString(2, user.getEmail());
	            preparedStatement.setString(3, user.getRole());
	            preparedStatement.setString(4, user.getPassword());
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }

	    public User selectUser(int id) {
	        User user = null;
	        Connection connection=null;
	        
	        connection =Connect.ConnectDb();
	        try ( 
	             
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	         
	            ResultSet rs = preparedStatement.executeQuery(); 
	            
	            while (rs.next()) {
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String role = rs.getString("role");
	                String password = rs.getString("password");
	                user = new User(id, name, email, role,password);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return user;
	    }

	    public List<User> selectAllUsers() { 
	        List<User> users = new ArrayList<>();
	        Connection connection=null;
	        
	        connection =Connect.ConnectDb();
	        try ( 
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
	            System.out.println(preparedStatement);  
	            ResultSet rs = preparedStatement.executeQuery(); 
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String role = rs.getString("role");
	                String password = rs.getString("password");
	                users.add(new User(id, name, email, role,password));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return users;
	    }

	    public boolean deleteUser(int id) throws SQLException {
	        boolean rowDeleted;
	        Connection connection=null;
	        
	        connection =Connect.ConnectDb();
	        try ( 
	             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
	            statement.setInt(1, id);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }

	    public boolean updateUser(User user) throws SQLException {
	        boolean rowUpdated;
	        Connection connection=null;
	        
	        connection =Connect.ConnectDb();
	        try ( 
	             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
	            statement.setString(1, user.getName());
	            statement.setString(2, user.getEmail());
	            statement.setString(3, user.getRole());
	            statement.setString(4, user.getPassword());
	            statement.setInt(5, user.getId());

	            rowUpdated = statement.executeUpdate() > 0;
	        }
	        return rowUpdated;
	    }

	    private void printSQLException(SQLException ex) {
	        for (Throwable e : ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
}
