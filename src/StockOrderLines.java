
public class StockOrderLines {
	//Attributes
	private int stockOrderLineID = 0; //public for now
	private int stockOrderID = 0;//public for now
	private int productID;//public for now
	private int quantity;//for checking whether an order is being worked on
	private boolean porousware;
	
	
	StockOrderLines(int stockOrderLineID, int stockOrderID, int productID, int quantity){
		this.stockOrderLineID = stockOrderLineID;
		this.stockOrderID = stockOrderID;
		this.productID = productID;
		this.quantity = quantity;
	}
	

	public int getStockOrderLineID() {
		return stockOrderLineID;
	}

	public void setStockOrderLineID(int stockOrderLineID) {
		this.stockOrderLineID = stockOrderLineID;
	}

	public int getStockOrderID() {
		return stockOrderID;
	}

	public void setStockOrderID(int stockOrderID) {
		this.stockOrderID = stockOrderID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public boolean isPorousware() {
		return porousware;
	}


	public void setPorousware(boolean porousware) {
		this.porousware = porousware;
	}



}
