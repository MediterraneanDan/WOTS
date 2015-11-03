import java.util.*;

public class CustOrderLines {
	//attributes
	private int custOrderLineID;
	private int custOrderID;
	private int productID;
	private int quantity;
	private String warehouseLocation;
	
	public CustOrderLines(int custOrderLineID, int custOrderID, int productID, int quantity, String warehouseLocation) {
		this.custOrderLineID = custOrderLineID;
		this.custOrderID = custOrderID;
		this.productID = productID;
		this.quantity = quantity;
		this.warehouseLocation = warehouseLocation;
	}

	public int getCustOrderLineID() {
		return custOrderLineID;
	}

	public void setCustOrderLineID(int custOrderLineID) {
		this.custOrderLineID = custOrderLineID;
	}

	public int getCustOrderID() {
		return custOrderID;
	}

	public void setCustOrderID(int custOrderID) {
		this.custOrderID = custOrderID;
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

	public String getWarehouseLocation() {
		return warehouseLocation;
	}

	public void setWarehouseLocation(String warehouseLocation) {
		this.warehouseLocation = warehouseLocation;
	}
	
	

}
