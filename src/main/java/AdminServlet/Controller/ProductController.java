package AdminServlet.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AdminServlet.DAO.CategoryDAO;
import AdminServlet.DAO.ProductDAO;
import AdminServlet.DAO.UserDAO;
import AdminServlet.Model.Category;
import AdminServlet.Model.Product;
import AdminServlet.Model.User;

@WebServlet(urlPatterns = { "/" })
public class ProductController extends HttpServlet {
	private ProductDAO productDAO;
	private CategoryDAO categoryDAO;

	public ProductController() {
		super();

	}

	public void init() {
		productDAO = new ProductDAO();
		categoryDAO = new CategoryDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/newProduct":
				showNewFormProduct(request, response);
				break;
			case "/insertProduct":
				insertProduct(request, response);
				break;
			case "/deleteProduct":
				deleteProduct(request, response);
				break;
			case "/editProduct":
				showEditForm(request, response);
				break;
			case "/updateProduct":
				updateProduct(request, response);
				break;
			default:
				listProduct(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<Category> listCategory = categoryDAO.selectAllCategory();
		request.setAttribute("listCategory", listCategory);

		List<Product> listProduct = productDAO.selectAllProduct();
		request.setAttribute("listProduct", listProduct);

		RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/list-product.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewFormProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Category> listCategory = categoryDAO.selectAllCategory();
		request.setAttribute("listCategory", listCategory);

		RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/product-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		Product prd = productDAO.selectProduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/product-form.jsp");
		request.setAttribute("prd", prd);
		dispatcher.forward(request, response);

	}

	private void insertProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String name = request.getParameter("name");
		Float price = Float.parseFloat(request.getParameter("price"));
		String image = request.getParameter("image");
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		Product newProduct = new Product(name, price, image, category_id);
		productDAO.insertProduct(newProduct);
		response.sendRedirect("/Pro-Admin/ProductController");
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Float price = Float.parseFloat(request.getParameter("price"));
		String image = request.getParameter("image");
		int category_id = Integer.parseInt(request.getParameter("category_id"));

		Product book = new Product(id, name, price, image, category_id);
		productDAO.updateProduct(book);
		response.sendRedirect("/Pro-Admin/ProductController");
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		productDAO.deleteProduct(id);
		response.sendRedirect("/Pro-Admin/ProductController");

	}
}
