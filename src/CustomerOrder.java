import java.util.*;


public class CustomerOrder {
	
		//Attributes
		private int custOrderID = 0; //public for now
		private float totalOrderValue = 0;//public for now
		private String orderStatus;//public for now
		private boolean beingWorkedOn;//for checking whether an order is being worked on
		private String whichEmployee;//public for now
		
		private enum orderState {
			Pending, Picked, Packed, AwaitingDelivery, Delivered
		}
		
		//temporary attributes
		private ArrayList<CustomerOrder> listOfOrders = new ArrayList<CustomerOrder>();
		//Methods
		
		public List<CustomerOrder> listOrders(){
			//ArrayList<Order> listOfOrders = new ArrayList<Order>();
			/*
			Access database, for each entry
			listOfOrders.add(order) 
			*/
			return listOfOrders;
		}
		
		/*public CustomerOrder getOrderInfo(int orderID){
			CustomerOrder orderSelected = new CustomerOrder();
			for(int counter=0; counter < listOfOrders.size(); counter++){
				
				if(listOfOrders.get(counter).custOrderID == orderID){
				//get acts like array index-basically this will get the order for the current iteration and from that order get the order id
					
				}
			}
			return orderSelected;
		}*/
		
		
		CustomerOrder (int custOrderID, float totalOrderValue, String orderStatus, boolean beingWorkedOn, String whichEmployee){
			this.custOrderID = custOrderID;
			this.totalOrderValue = totalOrderValue;
			this.orderStatus = orderStatus;
			this.beingWorkedOn = beingWorkedOn;
			this.whichEmployee = whichEmployee;
		}
		
		
		//temporary methods
		/*public void createOrder(){
			
		}
			
		public void addToOrder*/

		CustomerOrder() {
			// TODO Auto-generated constructor stub
		}

		public int getCustOrderID() {
			return custOrderID;
		}

		public void setCustOrderID(int custOrderID) {
			this.custOrderID = custOrderID;
		}

		public float getTotalOrderValue() {
			return totalOrderValue;
		}

		public void setTotalOrderValue(float totalOrderValue) {
			this.totalOrderValue = totalOrderValue;
		}

		public String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}

		public boolean isBeingWorkedOn() {
			return beingWorkedOn;
		}

		public void setBeingWorkedOn(boolean beingWorkedOn) {
			this.beingWorkedOn = beingWorkedOn;
		}

		public String getWhichEmployee() {
			return whichEmployee;
		}

		public void setWhichEmployee(String whichEmployee) {
			this.whichEmployee = whichEmployee;
		}
		
		


}
