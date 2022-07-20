package AdminServlet.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AdminServlet.Connection.Connect;
import AdminServlet.Model.Product;
import AdminServlet.Model.User;

public class ProductDAO { 

	    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product"+ "(name, price, image ,category_id) VALUES " + " (?, ?, ?, ?);";
	    private static final String SELECT_PRODUCT_BY_ID = "select id,name, price, image ,category_id from product where id =?";
	    private static final String SELECT_ALL_PRODUCT = "select * from product";
	    private static final String DELETE_PRODUCT_SQL = "delete from product where id = ?;";
	    private static final String UPDATE_PRODUCT_SQL = "update product set name = ?,price= ?, image =?, category_id=? where id = ?;";

	    public ProductDAO() {
	    } 

	    public void insertProduct(Product product) throws SQLException {
	        System.out.println(INSERT_PRODUCT_SQL);
	        Connection connection=null;
	        
	        connection =Connect.ConnectDb();
	        try (
	             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
	        	System.out.println(preparedStatement);  
	            preparedStatement.setString(1, product.getName());
	            preparedStatement.setFloat(2, product.getPrice());
	            preparedStatement.setString(3, product.getImage());
	            preparedStatement.setInt(4, product.getCategory_id()); 
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }

	    public Product selectProduct(int id) {
	    	Product product = null;
	        Connection connection=null;
	        
	        connection =Connect.ConnectDb();
	        try ( 
	             
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
	        	System.out.println(preparedStatement);  
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	         
	            ResultSet rs = preparedStatement.executeQuery(); 
	            
	            while (rs.next()) {
	            	String name = rs.getString("name");
	                Float price= rs.getFloat("price");
	                String image = rs.getString("image");
	                int category_id = rs.getInt("category_id");
	                product = new Product(id, name, price, image,category_id);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return product;
	    }

	    public List<Product> selectAllProduct() { 
	        List<Product> product = new ArrayList<>();
	        Connection connection=null;
	        
	        connection =Connect.ConnectDb();
	        try ( 
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);) {
	            System.out.println(preparedStatement);  
	            ResultSet rs = preparedStatement.executeQuery(); 
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                Float price= rs.getFloat("price");
	                String image = rs.getString("image");
	                int category_id = rs.getInt("category_id");
	                product.add(new Product(id, name, price, image,category_id));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return product;
	    }

	    public boolean deleteProduct(int id) throws SQLException {
	        boolean rowDeleted;
	        Connection connection=null;
	        
	        connection =Connect.ConnectDb();
	        try ( 
	             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
	        	System.out.println(statement);  
	            statement.setInt(1, id);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }

	    public boolean updateProduct(Product product) throws SQLException {
	        boolean rowUpdated;
	        Connection connection=null;
	        
	        connection =Connect.ConnectDb();
	        try ( 
	             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
	        	System.out.println(statement);  
	        	statement.setString(1, product.getName());
	            statement.setFloat(2, product.getPrice());
	            statement.setString(3, product.getImage());
	            statement.setInt(4, product.getCategory_id());
	            statement.setInt(5, product.getId());

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
