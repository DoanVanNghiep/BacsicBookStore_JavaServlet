package vnua.fita.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vnua.fita.bookstore.bean.MyUtils;
import vnua.fita.bookstore.bean.User;
import vnua.fita.bookstore.model.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		userDAO = new UserDAO("jdbc:mysql://localhost:3306/bookshop", "root", "0945057018");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext()
				.getRequestDispatcher("/Views/loginView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		LoginForm loginForm = new LoginForm(username, password);

		// Kiểm tra tính hợp lệ của dữ liệu nhập vào
		List<String> errors = loginForm.validate();

		// Nếu không có lỗi validate
		if (errors.isEmpty()) {
			// Tìm user trong DB
			User user = userDAO.findUser(username, password);
			// Nếu sai thông tin trong db thì bổ sung vào danh sách lỗi
			if (user == null) {
				errors.add("Sai thong tin tai khoan");
			} else { // Đăng nhập thành công
				HttpSession session = request.getSession();
				MyUtils.storeLoginedUser(session, user);
				if (user.getRole() == 0) {
					RequestDispatcher rd = this.getServletContext()
							.getRequestDispatcher("/ClientHomeServlet");
					rd.forward(request, response);
				} else if (user.getRole() == 1) {
					response.sendRedirect(request.getContextPath()+ "/AdminHomeServlet");
				}
			}
		}
		// có lỗi validate hoặc sai thông tin tài khoản
		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			request.setAttribute("loginForm", loginForm);

			RequestDispatcher rd = this.getServletContext()
					.getRequestDispatcher("/Views/loginView.jsp");
			rd.forward(request, response);
		}
	}

}
