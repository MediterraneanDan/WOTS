
public class Product {
	
	//attributes
	private int productID = 0;
	private String productName = "";
	private float sellingPrice = 0;
	private float stockPrice = 0;
	
	Product(int productID, String productName, float sellingPrice, float stockPrice){
		this.productID = productID;
		this.productName = productName;
		this.sellingPrice = sellingPrice;
		this.stockPrice = stockPrice;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public float getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(float stockPrice) {
		this.stockPrice = stockPrice;
	}

}
