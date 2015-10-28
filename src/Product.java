
public class Product {
	
	//attributes
	private int productID = 0;
	private String productName = "";
	private float sellingPrice = 0;
	
	Product(int productID, String productName, float sellingPrice){
		this.productID = productID;
		this.productName = productName;
		this.sellingPrice = sellingPrice;
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


}
