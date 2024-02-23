package vnua.fita.bookstore.crud;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
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
 * Servlet implementation class EditBookServlet
 */
@WebServlet("/editBook")
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;
	private ResultSet resultSet;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBookServlet() {
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
List<String> errors = new ArrayList<String>();
		
		String bookIdStr = request.getParameter("bookId");
		int bookId = -1;
		try {
			bookId = Integer.parseInt(bookIdStr);
		} catch (Exception e) {
			// TODO: handle exception
			errors.add("Lỗi");
		}
		
		if(errors.isEmpty()) {
			Book book = bookDAO.getBook(bookId);
			if(book == null) {
				errors.add("Lỗi book id");
			}else {
				request.setAttribute("book", book);
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/Views/editBookView.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		if(!errors.isEmpty()) {
			// Lưu thông tin vào request atttribute trước khi forward sang views.
			request.setAttribute("errors", String.join(", ", errors));
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/ClientHomeServlet");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookIdStr = request.getParameter("bookId");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String priceStr = request.getParameter("price");
		String quantityInStockStr = request.getParameter("quantityInStock");
		
		BookForm bookForm = new BookForm(bookIdStr, title, author, priceStr, quantityInStockStr);
		List<String> errors = bookForm.validateEditBookForm();
		
		if(errors.isEmpty()) {
			int bookId = Integer.parseInt(bookIdStr);
			int price = Integer.parseInt(priceStr);
			int quantityInStock = Integer.parseInt(quantityInStockStr);
			
			Book book = new Book(bookId, title, author, price, quantityInStock);
			boolean updateResult = bookDAO.updateBook(book);
			if(!updateResult) {
				errors.add("sửa lỗi");
			}else {
				response.sendRedirect(request.getContextPath() + "/AdminHomeServlet");
			}
		}
		if(!errors.isEmpty()) {
			// Lưu thông tin vào request atttribute trước khi forward sang views.
			request.setAttribute("errors", String.join(", ", errors));
			request.setAttribute("book", bookForm);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/Views/editBookView.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}
