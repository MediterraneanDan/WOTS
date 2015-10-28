import java.util.ArrayList;
import java.util.List;


public class StockOrder {
			//Attributes
			private int stockOrderID = 0; //public for now
			private String orderStatus;//unplaced/placed/delivered
			
			
			
			

			StockOrder (int stockOrderID, String orderStatus){
				this.stockOrderID = stockOrderID;
				this.orderStatus = orderStatus;
			}


			StockOrder() {
				// TODO Auto-generated constructor stub
			}

			public int getStockOrderID() {
				return stockOrderID;
			}

			public void setStockOrderID(int stockOrderID) {
				this.stockOrderID = stockOrderID;
			}

			public String getOrderStatus() {
				return orderStatus;
			}


			public void setOrderStatus(String orderStatus) {
				this.orderStatus = orderStatus;
			}


}
