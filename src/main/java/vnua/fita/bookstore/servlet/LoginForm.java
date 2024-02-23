package vnua.fita.bookstore.servlet;

import java.util.ArrayList;
import java.util.List;

public class LoginForm{
	private String userName;
    private String password;
    private String rememberMe;
	public LoginForm() {
	}
	
	public LoginForm(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public LoginForm(String userName, String password, String rememberMe) {
		super();
		this.userName = userName;
		this.password = password;
		this.rememberMe = rememberMe != null ? "checked" : "";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	public List<String> validate(){
		List<String> erros = new ArrayList<String>();
		if(userName == null || userName.trim().isEmpty()) {
			erros.add("Nhập tài khoản.");
		}
		if(password == null || password.trim().isEmpty()) {
			erros.add("Nhập mật khẩu.");
		}
		return erros;
	}
	
    
}
