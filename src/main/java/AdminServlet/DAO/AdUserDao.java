package AdminServlet.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 

import AdminServlet.Connection.Connect;
import AdminServlet.Model.AdUser;

public class AdUserDao {
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
	public void insertUser(AdUser user) throws SQLException {   
		try (Connection connection = Connect.ConnectDb();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getRole());
			preparedStatement.setString(3, user.getPassword()); 
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public AdUser selectUser(int id) {
		AdUser user = null;
		 
		try (Connection connection = Connect.ConnectDb();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String role = rs.getString("role");
				String password = rs.getString("password");
				user = new AdUser(id, name, email, role,password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<AdUser> selectAllUsers() {  
		List<AdUser> users = new ArrayList<>(); 
		try (Connection connection = Connect.ConnectDb();  
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement); 
			ResultSet rs = preparedStatement.executeQuery(); 
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String role = rs.getString("role");
				String password=rs.getString("password");
				users.add(new AdUser(id, name, email, role,password));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection =Connect.ConnectDb();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(AdUser user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = Connect.ConnectDb();
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
	public AdUser checkLogin(String name, String password) { 
		Connection conn=null;
		try {
			conn = new Connect().ConnectDb();
			String sql="select * from users where name =? and password =?";
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setString(2,name);
			ps.setString(5,password);
			ResultSet rs=ps.executeQuery();
			 while(rs.next()) {
				 AdUser a= new AdUser(rs.getString(2),rs.getString(5));
				 return a;
			 }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
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
