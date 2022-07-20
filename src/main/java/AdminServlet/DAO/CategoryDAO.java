package AdminServlet.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AdminServlet.Connection.Connect;
import AdminServlet.Model.Category;
import AdminServlet.Model.User;

public class CategoryDAO {
	
	private static final String INSERT_CATEGORY_SQL = "INSERT INTO category" + "(name) VALUES "+ " (?);";  
    private static final String SELECT_CATEGORY_BY_ID = "select id,namefrom category where id =?";
    private static final String SELECT_ALL_CATEGORY = "select * from category";
    private static final String DELETE_CATEGORY_SQL = "delete from category where id = ?;";
    private static final String UPDATE_CATEGORY_SQL = "update category set name = ? where id = ?;";
    
    public void CategoryDao() {}
    
    public void insertCategory(Category category) throws SQLException {
        System.out.println(INSERT_CATEGORY_SQL);
        Connection connection=null;
        
        connection =Connect.ConnectDb();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL)) {
            preparedStatement.setString(1, category.getName()); 
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Category selectCategory(int id) {
    	Category category = null;
        Connection connection=null;
        
        connection =Connect.ConnectDb();
        try ( 
             
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
         
            ResultSet rs = preparedStatement.executeQuery(); 
            
            while (rs.next()) {
                String name = rs.getString("name"); 
                 
                category = new Category(id, name  );
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return category;
    }

    public List<Category> selectAllCategory() { 
        List<Category> category = new ArrayList<>();
        Connection connection=null;
        
        connection =Connect.ConnectDb();
        try ( 
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);) {
            System.out.println(preparedStatement);  
            ResultSet rs = preparedStatement.executeQuery(); 
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                  
                category.add(new Category(id, name )); 
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return category;
    }

    public boolean deleteCategory(int id) throws SQLException {
        boolean rowDeleted;
        Connection connection=null;
        
        connection =Connect.ConnectDb();
        try ( 
             PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateCategory(Category category) throws SQLException {
        boolean rowUpdated;
        Connection connection=null;
        
        connection =Connect.ConnectDb();
        try ( 
             PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY_SQL);) {
            statement.setString(1, category.getName());
             
            statement.setInt(3, category.getId());

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
