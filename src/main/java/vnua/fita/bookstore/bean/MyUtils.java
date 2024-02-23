package vnua.fita.bookstore.bean;

import java.util.Map;

import javax.servlet.http.HttpSession;

public class MyUtils {
	public static void storeLoginedUser(HttpSession session, User loginedUser) {
		session.setAttribute("loginedUser", loginedUser);
	}
	public static String getPathInfoFromServletPath(String path) {
	    if (path == null || path.isEmpty()) {
	        return ""; // Hoặc có thể ném một ngoại lệ
	    }

	    String[] result = path.split("/");
	    if (result.length == 0) {
	        return "";
	    }

	    return result[result.length - 1];
	}

	//lưu trữ thông tin giỏ hàng vào Session
	public static void storeCart(HttpSession session, Cart cart) {
		//trên JSP có thể truy cập thông qua ${loginedUser}
		session.setAttribute("cartOfCustomer", cart);
	}
	
	//lấy thông tin giỏ hàng lưu trữ trong Session
	public static Cart getCartOfCustomer(HttpSession session) {
		Cart cartOfCustomer = (Cart) session.getAttribute("cartOfCustomer");
		return cartOfCustomer;
	}
	public static void updateCartOfCustomer(HttpSession session,Map<Integer, CartItem> map) {
		Cart cart = getCartOfCustomer(session);
		cart.setCartItemList(map);
		session.setAttribute("cart", cart);
	}
	public static void deleteCart(HttpSession session) {
		session.removeAttribute("cart");
	}
	
	
}
