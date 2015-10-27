import java.util.ArrayList;
import java.util.List;


public class StockOrder {
			//Attributes
			private int stockOrderID = 0; //public for now
			private float totalOrderValue = 0;//public for now
			
			
			
			StockOrder (int stockOrderID, float totalOrderValue){
				this.stockOrderID = stockOrderID;
				this.totalOrderValue = totalOrderValue;
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

			public float getTotalOrderValue() {
				return totalOrderValue;
			}

			public void setTotalOrderValue(float totalOrderValue) {
				this.totalOrderValue = totalOrderValue;
			}

	
	

}
