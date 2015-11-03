import java.sql.*;
import java.util.*;
import java.util.Scanner;

//class used for communicating with the SQL WOTS database
//each method will contain its own open and close connection code
public class ManageDatabase {
	
	//attributes
	
	//database connection attributes
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/wotsdatabase";
	
	static final String USER = "root";
	static final String PASS = "netbuilder";
	
	//system attributes
	
	
	//methods
	public void accessDB(){//method which opens and closes connection
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException sqle) {//closing connection
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt !=null){
					stmt.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null){
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}
	
	//method for reading list of orders from database
	public ArrayList<CustomerOrder> readCustOrderList(){//method which opens and closes connection
		ArrayList<CustomerOrder> listOfOrders = new ArrayList<CustomerOrder>();//arraylist will be filled with orders--commented out for vector test with jlist
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//code for reading
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sq12 = "SELECT CustomerOrderID, TotalOrderValue, OrderStatus, BeingWorkedOn, WhichEmployee FROM customerorder";
			ResultSet rs = stmt.executeQuery(sq12);
			while(rs.next()) {
				int newCustOrderID = rs.getInt("CustomerOrderID");
				float newCustOrderTotal = rs.getFloat("TotalOrderValue");
				String newOrderStatus = rs.getString("OrderStatus");
				boolean newBeingWorkedOn = rs.getBoolean("BeingWorkedOn");
				String newEmployee = rs.getString("WhichEmployee");
				CustomerOrder newCustOrder = new CustomerOrder(newCustOrderID, newCustOrderTotal, newOrderStatus, newBeingWorkedOn, newEmployee);
				listOfOrders.add(newCustOrder);
				System.out.println("");
			}
			rs.close();
		
		
		//closing connection
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt !=null){
					stmt.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null){
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
		return listOfOrders;
		
		
	}
	
	public ArrayList<CustOrderLines> readCustOrderLines(){//method which opens and closes connection
		ArrayList<CustOrderLines> listOfOrderLines = new ArrayList<CustOrderLines>();//arraylist will be filled with orders--commented out for vector test with jlist
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//code for reading
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sq12 = "SELECT CustOrderLineID, CustOrderID, ProductID, Quantity, WarehouseLocation FROM customerorderlines";
			ResultSet rs = stmt.executeQuery(sq12);
			while(rs.next()) {
				int newCustOrderLineID = rs.getInt("CustOrderLineID");
				int newcustOrderID = rs.getInt("CustOrderID");
				int newProductID = rs.getInt("ProductID");
				int newQuantity = rs.getInt("Quantity");
				String newWarehouseLocation = rs.getString("WarehouseLocation");
				CustOrderLines newCustOrderLine = new CustOrderLines(newCustOrderLineID, newcustOrderID, newProductID, newQuantity, newWarehouseLocation);
				listOfOrderLines.add(newCustOrderLine);
				System.out.println("");
			}
			rs.close();
		
		
		//closing connection
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt !=null){
					stmt.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null){
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
		return listOfOrderLines;
		
		
	}
	
	//method for updating order status from database
	public void updateCustOrderStatus(int custOrderID, String newOrderStatus, String newEmployee){//method which opens and closes connection
		//code which decides whether the order is being worked on depending on the orders status
		int beingWorkedOn;//has to be int cus sql is stupid; boolean in sql set to tiny int which can be either 0 or 1
		if((newOrderStatus.equals("Pending") == true) || (newOrderStatus.equals("Delivered") == true)){//basically if the order status is pending or delivered then its not being worked on
			beingWorkedOn = 0;
			newEmployee = "Nobody";
		}else{
			beingWorkedOn = 1;
		}
				
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//code for updating
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sq13 = "UPDATE customerorder " + "SET OrderStatus = '"+newOrderStatus+ "', BeingWorkedOn = '"+beingWorkedOn+ "', WhichEmployee = '"+newEmployee+ "' WHERE CustomerOrderID = '"+custOrderID+"'";
			stmt.executeUpdate(sq13);
			
			
		//closing connection
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt !=null){
					stmt.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null){
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
			
			
	}
	public void createStockOrder(){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		
			//code for inserting a new stock order
			System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();
			String sql = "INSERT INTO stockorder (TotalOrderValue)" + "VALUES ('0')";//inserts a 0 total just to auto generate a new stock order id
			stmt.executeUpdate(sql);//create new order by inserting blank total into stockOrder then take the newly created ID by reading from the tablethen insert new orderline with ID read
			System.out.println("Inserting records into the table");//or have it so you create new order which inserts new id into stockOrder then an add order line button with the stock order ID
		
		//closing connection
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt !=null){
					stmt.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null){
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
		
	}
	
	public ArrayList<StockOrder> readStockOrderList(){//method which opens and closes connection
		ArrayList<StockOrder> listOfStockOrders = new ArrayList<StockOrder>();//arraylist will be filled with orders--commented out for vector test with jlist
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//code for reading
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sq12 = "SELECT StockOrderID, OrderStatus FROM stockorder";
			ResultSet rs = stmt.executeQuery(sq12);
			while(rs.next()) {
				int stockOrderID = rs.getInt("StockOrderID");
				String orderStatus = rs.getString("OrderStatus");
				StockOrder newStockOrder = new StockOrder(stockOrderID, orderStatus);
				listOfStockOrders.add(newStockOrder);
				System.out.println("");
			}
			rs.close();
		
		
		//closing connection
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt !=null){
					stmt.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null){
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
		return listOfStockOrders;
		
		
	}
	
	public void addStockOrderLine(int stockOrderID, int productID, int quantity, boolean porousware){
		int porouswareChoice;
		if(porousware == true){
			porouswareChoice = 1;
		}else{
			porouswareChoice = 0;
		}
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		
			//code for inserting a new stock order
			System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();
			String sql = "INSERT INTO stockorderlines (StockOrderID, ProductID, Quantity, Porousware)" + "VALUES ('" +stockOrderID+"', '"+productID+"', '"+quantity+"', '"+porouswareChoice+"')";//inserts a 0 total just to auto generate a new stock order id
			stmt.executeUpdate(sql);//create new order by inserting blank total into stockOrder then take the newly created ID by reading from the tablethen insert new orderline with ID read
			System.out.println("Inserting records into the table");//or have it so you create new order which inserts new id into stockOrder then an add order line button with the stock order ID
		
		//closing connection
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt !=null){
					stmt.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null){
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
		
	}
	public void updateStockOrderPrice(){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//code for updating
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			//String sq13 = "UPDATE stockorder " + "SET OrderStatus = '"+newOrderStatus+ "', BeingWorkedOn = '"+beingWorkedOn+ "' WHERE CustomerOrderID = '"+custOrderID+"'";
			//stmt.executeUpdate(sq13);
		
		
		//closing connection
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt !=null){
					stmt.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null){
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}
	
	//method for updating order status from database
	public void updateStockOrderStatus(int stockOrderID, String newOrderStatus){//method which opens and closes connection
		
					
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//code for updating
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sq13 = "UPDATE stockorder " + "SET OrderStatus = '"+newOrderStatus+"' WHERE StockOrderID = '"+stockOrderID+"'";
			stmt.executeUpdate(sq13);
				
				
		//closing connection
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt !=null){
					stmt.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null){
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
				
				
	}
	
	public ArrayList<Product> readProductList(){//method which opens and closes connection
		ArrayList<Product> listOfProducts = new ArrayList<Product>();//arraylist will be filled with orders--commented out for vector test with jlist
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//code for reading
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sq12 = "SELECT ProductID, ProductName, SellingPrice FROM product";
			ResultSet rs = stmt.executeQuery(sq12);
			while(rs.next()) {
				int productID = rs.getInt("ProductID");
				String productName = rs.getString("ProductName");
				int sellingPrice = rs.getInt("SellingPrice");
				Product newProduct = new Product(productID, productName, sellingPrice);
				listOfProducts.add(newProduct);
				System.out.println("");
			}
			rs.close();
		
		
		//closing connection
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt !=null){
					stmt.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null){
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
		return listOfProducts;
		
		
	}
	
	
	
	public static void main(String args[]){
		//code for reading from database
		ManageDatabase newConnection = new ManageDatabase();
		ArrayList<CustomerOrder> listOfOrders = newConnection.readCustOrderList();
		for(int counter=0; counter < listOfOrders.size(); counter++){
			System.out.println("Order id:" + listOfOrders.get(counter).getCustOrderID());
		}
		System.out.println("Pick order for more info, enter id: ");
		int choice;
		Scanner scanner = new Scanner(System.in);
		choice = Integer.parseInt(scanner.nextLine());

		for(int counter=0; counter < listOfOrders.size(); counter++){
			
			if(listOfOrders.get(counter).getCustOrderID() == choice){
				System.out.println("Order id:" + listOfOrders.get(counter).getCustOrderID() + " Total Of order:" + listOfOrders.get(counter).getTotalOrderValue() + " Order Status:" + listOfOrders.get(counter).getOrderStatus() + " Is the order being work on:" + listOfOrders.get(counter).isBeingWorkedOn());
				
			}
		}
		
		//code for updating database
		System.out.println("Pick order to change status, enter id: ");
		int choice2;
		Scanner scanner2 = new Scanner(System.in);
		choice2 = Integer.parseInt(scanner2.nextLine());
		System.out.println("Enter new state: ");
		String newState;
		Scanner scanner3 = new Scanner(System.in);
		newState = scanner3.nextLine();
		//newConnection.updateCustOrderStatus(choice2, newState);
		/*ArrayList<CustomerOrder> newListOfOrders = newConnection.readCustOrderList();
		for(int counter=0; counter < newListOfOrders.size(); counter++){
			System.out.println("Order id:" + newListOfOrders.get(counter).getCustOrderID() + " Total Of order:" + newListOfOrders.get(counter).getTotalOrderValue() + " Order Status:" + newListOfOrders.get(counter).getOrderStatus() + " Is the order being work on:" + newListOfOrders.get(counter).isBeingWorkedOn());
		}*/
	}
	
	

}
