package vnua.fita.bookstore.bean;

public class User {
	private String userName;
	private String password;
	private int role;
	private String fullName;
	private String email;
	private String mobile;
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
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public User() {
		
	}
	public User(String userName, String password, int role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	public User(String userName, String password, int role, String fullName, String email, String mobile) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.fullName = fullName;
		this.email = email;
		this.mobile = mobile;
	}
	
	
}
