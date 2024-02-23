package vnua.fita.bookstore.crud;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.fita.bookstore.bean.Book;
import vnua.fita.bookstore.formbean.BookForm;
import vnua.fita.bookstore.model.BookDAO;

/**
 * Servlet implementation class CreateBookServlet
 */
@WebServlet(urlPatterns = "/createBook")
public class CreateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    BookDAO bookDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	public void init(){
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		bookDAO = new BookDAO("jdbc:mysql://localhost:3306/bookshop", "root", "0945057018");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Views/createBookView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String priceStr = request.getParameter("price");
		String quantityInStockStr = request.getParameter("quantityInStock");
		
		BookForm bookForm = new BookForm(title, author, priceStr, quantityInStockStr);
		List<String> errors = bookForm.validateCreateBookForm();
		
		if(errors.isEmpty()) {
			int price = Integer.parseInt(priceStr);
			int quantityInStock = Integer.parseInt(quantityInStockStr);
			
			Book book = new Book(title, author, price, quantityInStock);
			
			boolean insertResult = bookDAO.insertBook(book);
			if (!insertResult) {
				errors.add("thêm sách lỗi");
			}else {
				response.sendRedirect(request.getContextPath() + "/AdminHomeServlet");
			}	
		}if(!errors.isEmpty()) {
			// Lưu thông tin vào request atttribute trước khi forward sang views.
			request.setAttribute("errors", String.join(", ", errors));
			request.setAttribute("book", bookForm);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/Views/createBookView.jsp");
			dispatcher.forward(request, response);
		}
	}

}
