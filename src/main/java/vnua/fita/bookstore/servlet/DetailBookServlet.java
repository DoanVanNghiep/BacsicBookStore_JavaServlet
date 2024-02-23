package vnua.fita.bookstore.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.fita.bookstore.bean.Book;
import vnua.fita.bookstore.model.BookDAO;

/**
 * Servlet implementation class DetailBookServlet
 */
@WebServlet("/detailBook")
public class DetailBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailBookServlet() {
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
						.getRequestDispatcher("/Views/detailBookView.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
