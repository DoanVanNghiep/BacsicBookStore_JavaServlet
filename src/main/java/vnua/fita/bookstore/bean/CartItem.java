package vnua.fita.bookstore.bean;

public class CartItem {
	private Book selectedBook;//cuốn sách được chọn mua
	private int quantity;// số lượng
	public Book getSelectedBook() {
		return selectedBook;
	}
	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CartItem(Book selectedBook, int quantity) {
		this.selectedBook = selectedBook;
		this.quantity = quantity;
	}
}
