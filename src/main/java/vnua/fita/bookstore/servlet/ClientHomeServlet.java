package vnua.fita.bookstore.servlet;

import java.io.IOException;
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
 * Servlet implementation class ClientHomeServlet
 */
@WebServlet("/ClientHomeServlet")
public class ClientHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientHomeServlet() {
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
		String errors = null;
		List<Book> list = null;
		
		// phân trang sản phẩm
		String keyword = request.getParameter("keyword");// lấy keyword nếu có
		int page = 1;
		int recordsPerPage = 2;
		if(request.getParameter("page") != null ) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		list = bookDAO.listAllBooks((page-1)*recordsPerPage,recordsPerPage, keyword);
		int noOfRecords = bookDAO.getNoRecords(keyword);
		int noOfPage = (int) Math.ceil(noOfRecords * 1.0/recordsPerPage);
		
		request.setAttribute("keyword", keyword);
		request.setAttribute("currentPage", page);
		request.setAttribute("bookList", list);
		request.setAttribute("noOfPages", noOfPage);
		
		// tìm kiếm sản phẩm
		if(keyword != null && !keyword.isEmpty()) { // người dùng tìm kiếm
			list = bookDAO.listAllBooks(keyword);
		}else {
			list = bookDAO.listAllBooks();
		}
		if(list.isEmpty()) {
			errors = "Lỗi danh sách";
		}
		request.setAttribute("errors", errors);
		request.setAttribute("bookList", list);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/Views/clientHomeServlet.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
