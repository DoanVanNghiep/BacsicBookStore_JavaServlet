package vnua.fita.bookstore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vnua.fita.bookstore.bean.Book;

public class BookDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	private Statement statement;
	

	public BookDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public List<Book> listAllBooks(){
		List<Book> books = new ArrayList<Book>();
		String sql = "SELECT * FROM book";
		jdbcConnection = DBConnection.createConnection(jdbcURL,jdbcUsername,jdbcPassword);
		try {
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				int id = resultSet.getInt("book_id");
				String title = resultSet.getString("title");
				String  author = resultSet.getString("author");
				int price = resultSet.getInt("price");
				int quantityInStock = resultSet.getInt("quantity_in_stock");
				String detail = resultSet.getString("detail");
				String imagePath = resultSet.getString("image_path");
				Book book = new Book(id, title, author, price, quantityInStock, detail, imagePath);
				book.setDetail(detail);
				book.setImagePath(imagePath);
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return books;
	}
	
	public boolean deleteBook(int bookid) {
		boolean deleteResult = false;
		String sql = "DELETE FROM book WHERE book_id = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL,jdbcUsername,jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setInt(1, bookid);
			deleteResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return deleteResult;
	}
	
	public Book getBook(int id ) {
		Book book =  null;
		String sql = "SELECT * FROM book WHERE book_id = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL,jdbcUsername,jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setInt(1, id);
			resultSet = preStatement.executeQuery();
			if(resultSet.next()) {
				String title = resultSet.getString("title");
				String  author = resultSet.getString("author");
				int price = resultSet.getInt("price");
				int quantityInStock = resultSet.getInt("quantity_in_stock");
				String detail = resultSet.getString("detail");
				String imagePath = resultSet.getString("image_path");
				book = new Book(id, title, author, price, quantityInStock, detail, imagePath);
				book.setDetail(detail);
				book.setImagePath(imagePath);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return book;
	}
	
	public boolean insertBook(Book book) {
		boolean insertResult = false;
		
		String sql = "INSERT INTO book (title, author, price, quantity_in_stock) VALUES (?,?,?,?)";
		jdbcConnection = DBConnection.createConnection(jdbcURL,jdbcUsername,jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, book.getTitle());
			preStatement.setString(2, book.getAuthor());
			preStatement.setInt(3, book.getPrice());
			preStatement.setInt(4, book.getQuantityInStock());
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return insertResult;
	}
	public boolean updateBook(Book book) {
		boolean updateResult = false;
		String sql = "UPDATE book SET title = ?, author = ?, price = ?" 
				+ " WHERE book_id = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL,jdbcUsername,jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, book.getTitle());
			preStatement.setString(2, book.getAuthor());
			preStatement.setInt(3, book.getPrice());
			preStatement.setInt(4, book.getBookId());
			updateResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return updateResult;
	}
	
	// phương thức tìm kiếm sản phẩm
	
	public List<Book> listAllBooks(String keyword){
		List<Book> sreachBook = new ArrayList<Book>();
		String sql = "SELECT * FROM book WHERE title LIKE ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL,jdbcUsername,jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, "%" + keyword + "%");
			resultSet = preStatement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("book_id");
				String title = resultSet.getString("title");
				String  author = resultSet.getString("author");
				int price = resultSet.getInt("price");
				Book book = new Book(id, title, author, price);
				sreachBook.add(book);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return sreachBook;
	}
	
	public List<Book> listAllBooks(int offset,int noOfRecords, String keyword){
		List<Book> listBook = new ArrayList<Book>();
		String sql = "select * from book";
		if(keyword != null && !keyword.isEmpty()) {
			sql += "where title like ?";
		}
		sql += "order by created_date DESC";
		sql += "LIMIT ?, ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL,jdbcUsername,jdbcPassword);
		try {
			int index = 0;
			preStatement = jdbcConnection.prepareStatement(sql);
			if(keyword != null && !keyword.isEmpty()) {
				preStatement.setString(++index, "%" + keyword + "%");
			}
			preStatement.setInt(++index, offset);
			preStatement.setInt(++index, noOfRecords);
			resultSet = preStatement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("book_id");
				String title = resultSet.getString("title");
				String  author = resultSet.getString("author");
				int price = resultSet.getInt("price");
				int quantityInStock = resultSet.getInt("quantity_in_stock");
				String detail = resultSet.getString("detail");
				String imagePath = resultSet.getString("image_path");
				Book book = new Book(id, title, author, price, quantityInStock);
				book.setDetail(detail);
				book.setImagePath(imagePath);
				listBook.add(book);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}	
		return listBook;
	}
	
	public int getNoRecords(String keyword) {
		String sql = "select count(book_id) from book";
		if(keyword != null && !keyword.isEmpty()) {
			sql += "where title like ?";
		}
		sql += "order by created_date DESC";
		jdbcConnection = DBConnection.createConnection(jdbcURL,jdbcUsername,jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			if(keyword != null && !keyword.isEmpty()) {
				preStatement.setString(1, "%" + keyword + "%");
			}	
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}	
		return 0;
	}
	
}
