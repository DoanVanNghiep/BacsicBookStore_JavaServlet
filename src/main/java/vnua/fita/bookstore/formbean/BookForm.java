package vnua.fita.bookstore.formbean;

import java.util.ArrayList;
import java.util.List;

public class BookForm {
	private String bookId;
	private String title;
	private String author;
	private String price;
	private String quantityInStock;
	private String detail;
	private String imagePath;
	
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(String quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	

	public BookForm(String bookId, String title, String author, String price, String quantityInStock) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantityInStock = quantityInStock;
	}
	

	public BookForm(String title, String author, String price, String quantityInStock) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantityInStock = quantityInStock;
	}

	// Kiểm tra tính hợp lệ của chỉnh sửa sách
	public List<String> validateEditBookForm(){
		List<String> errors = new ArrayList<String>();
		if(title == null || title.trim().isEmpty()) {
			errors.add("title is null!");
		}
		
		if(author == null || author.trim().isEmpty()) {
			errors.add("author is null!");
		}
		
		if(bookId == null || bookId.trim().isEmpty()) {
			errors.add("bookId is null");
		}else {
			try {
				Integer.parseInt(bookId);// Kiểm tra kiểu nguyên
			} catch (NumberFormatException e) {
				// TODO: handle exception
				errors.add(" Lỗi BookID");
			}
		}
		if(price == null || price.trim().isEmpty()) {
			errors.add("price is null");
		}else {
			try {
				Integer.parseInt(price);// Kiểm tra kiểu nguyên
			} catch (NumberFormatException e) {
				// TODO: handle exception
				errors.add(" Lỗi price");
			}
		}
		if(quantityInStock == null || quantityInStock.trim().isEmpty()) {
			errors.add("quantityInStock is null");
		}else {
			try {
				Integer.parseInt(quantityInStock);// Kiểm tra kiểu nguyên
			} catch (NumberFormatException e) {
				// TODO: handle exception
				errors.add(" Lỗi quantityInStock");
			}
		}
		return errors;
	}
	public List<String> validateCreateBookForm(){
		List<String> errors = new ArrayList<String>();
		if(title == null || title.trim().isEmpty()) {
			errors.add("title is null");
		}
		
		if(author == null || author.trim().isEmpty()) {
			errors.add("author is null");
		}if(price == null || price.trim().isEmpty()) {
			errors.add("price is null");
		}else {
			try {
				Integer.parseInt(price);// Kiểm tra kiểu nguyên
			} catch (NumberFormatException e) {
				// TODO: handle exception
				errors.add(" Lỗi price");
			}
		}
		if(quantityInStock == null || quantityInStock.trim().isEmpty()) {
			errors.add("quantityInStock is null");
		}else {
			try {
				Integer.parseInt(quantityInStock);// Kiểm tra kiểu nguyên
			} catch (NumberFormatException e) {
				// TODO: handle exception
				errors.add("Lỗi quantityInStock");
			}
		}
		return errors;
	}
}
