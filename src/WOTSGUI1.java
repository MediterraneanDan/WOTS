//gui 1 for WOTS
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class WOTSGUI1 extends JFrame {
	//master frame
	JFrame mainFrame = new JFrame();
	//containers for pages
	Container startMenu = new Container();
	Container custOrderMenu = new Container();
	Container stockOrderMenu = new Container();
	Container travellingSalesmanMenu = new Container();
	
	//back to main menu button
	JButton backToMenu = new JButton("Back to Menu");


	
	//aggregate classes
	ManageDatabase newDbConnection = new ManageDatabase();
	TravellingSalesman travellingSalesman = new TravellingSalesman();
	
	WOTSGUI1(){
		mainFrame.setSize(1500, 1000);
		mainFrame.setResizable(false);
		//listener for back to menu button
		backToMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				custOrderMenu.setVisible(false);
				stockOrderMenu.setVisible(false);
				travellingSalesmanMenu.setVisible(false);
				startMenu();
			}
		});
		startMenu();

		mainFrame.setVisible(true);
	}
	
	//Attributes
	
	//Methods
	public void startMenu() {
		mainFrame.setTitle("Warehouse Tracking System");
		mainFrame.remove(custOrderMenu);
		mainFrame.remove(stockOrderMenu);
		startMenu.removeAll();
		
		
		//panels
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(2,1));
		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(new GridLayout(1,3));
		
		JLabel welcomeMessage = new JLabel("WOTS Main Menu", SwingConstants.CENTER);
		welcomeMessage.setFont(new Font("Serif", Font.PLAIN, 100));
		titlePanel.add(welcomeMessage);
		
		
		//try-catch for producing image
        try{
        	JLabel logo = new JLabel(new ImageIcon(ImageIO.read(new File("images/nbgardenslogo.jpg"))));
        	titlePanel.add(logo);
        }catch(IOException e){
        	e.printStackTrace();
        	System.out.println("image hasnt printed");
        }
        

		
		//buttons
		JButton custOrder = new JButton("Customer Orders");
		custOrder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				custOrderMenu();
			}
		});
		custOrder.setFont(new Font("Serif", Font.PLAIN, 50));
		custOrder.setPreferredSize(new Dimension(400, 100));//occurs twice could be in superclass or interface
		choicePanel.add(custOrder);
		
		JButton stockOrder = new JButton("Stock Orders");
		stockOrder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				stockOrderMenu();
			}
		});
		stockOrder.setFont(new Font("Serif", Font.PLAIN, 50));
		stockOrder.setPreferredSize(new Dimension(400, 100));//occurs twice could be in superclass or interface
		choicePanel.add(stockOrder);
		
		JButton travellingSalesman = new JButton("Travelling Salesman");
		travellingSalesman.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				travellingSalesman();
			}
		});
		travellingSalesman.setFont(new Font("Serif", Font.PLAIN, 50));
		travellingSalesman.setPreferredSize(new Dimension(400, 100));//occurs twice could be in superclass or interface
		choicePanel.add(travellingSalesman);
		
		startMenu.add(titlePanel);
		startMenu.add(choicePanel);
		startMenu.setLayout(new GridLayout(2,1));
		startMenu.setVisible(true);
		mainFrame.add(startMenu);
		mainFrame.revalidate();

	}
	
	//int listChoice =0;//for the list of orders
	//ArrayList<CustomerOrder> listOfOrders = newDbConnection.readCustOrderList();//need these outside, commented on 27/10/15 to try variable locally
	//int orderSelected;//

	
	public void custOrderMenu(){
		mainFrame.setTitle("Customer orders");
		//make the main menu vanish
		startMenu.setVisible(false);
		mainFrame.remove(startMenu);
		custOrderMenu.removeAll();//this line will clear the page if revisited
		
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2,1));
		JPanel topLeftPanel = new JPanel();
		topLeftPanel.setLayout(new GridLayout(2,1));
		JPanel topRightPanel = new JPanel();
		topRightPanel.setLayout(new GridLayout(3,1));
		
		topLeftPanel.add(backToMenu);
		JLabel errorField = new JLabel("Any errors will display here:");
		topLeftPanel.add(errorField);
		
		JLabel moreInfoLabel = new JLabel("Click an order for more info!");
    	topRightPanel.add(moreInfoLabel);
		
		//list which will contain all current orders
		//notes could display object of customer orders then if one is clicked search orderlines by the id of the order clicked
		//ArrayList<CustomerOrder> listOfOrders = newDbConnection.readCustOrderList(); commented out as it must be used throughout whole class not just in method
		//Vector<String> custOrderIDs = new Vector(); changed to a listmodel as it can be dynamically changed
    	ArrayList<CustomerOrder> listOfOrders = newDbConnection.readCustOrderList();
		DefaultListModel custOrderIDs = new DefaultListModel();
		for(int counter=0; counter < listOfOrders.size(); counter++){
			custOrderIDs.addElement("Order ID: " + Integer.toString(listOfOrders.get(counter).getCustOrderID()) + " Is the order being work on: " + listOfOrders.get(counter).isBeingWorkedOn());
		}
		JList custOrderList = new JList(custOrderIDs);
		
		JTextArea specificOrder = new JTextArea();
		custOrderList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				//orderSelected = custOrderList.getSelectedIndex() + 1;
				//listOfOrders = newDbConnection.readCustOrderList(); commented 27/10/15 for locally
				ArrayList<CustomerOrder> listOfOrders = newDbConnection.readCustOrderList();
				for(int counter=0; counter < listOfOrders.size(); counter++){
							
					if(listOfOrders.get(counter).getCustOrderID() == (custOrderList.getSelectedIndex() + 1)){
						specificOrder.setText("--Order ID: " + listOfOrders.get(counter).getCustOrderID() + " /Total Of order: " + listOfOrders.get(counter).getTotalOrderValue() + " /Order Status: " + listOfOrders.get(counter).getOrderStatus() + " \n/Is the order being work on: " + listOfOrders.get(counter).isBeingWorkedOn() + " /Which Employee: " + listOfOrders.get(counter).getWhichEmployee() + "--");
					}
				}
				//custOrderMenu.add(new JLabel());
			}
		});
		topRightPanel.add(custOrderList);
		specificOrder.setFont(new Font("Serif", Font.PLAIN, 18));
		topRightPanel.add(specificOrder);
		
		//code for changing the state of the selected order
		JPanel stateChangePanel = new JPanel();
		stateChangePanel.setLayout(new GridLayout(2,2));
		JLabel stateChangeLabel = new JLabel("Would you like to change the status of the order selected:");
		JComboBox statusDropDown = new JComboBox();
		statusDropDown.addItem("Picked");
		statusDropDown.addItem("Packed");
		statusDropDown.addItem("AwaitingDelivery");
		statusDropDown.addItem("Delivered");
		JLabel whichEmployeeLabel = new JLabel("Enter employee name:");
		JTextField whichEmployeeTF = new JTextField("Employee name");
		JButton submitButton = new JButton("Submit changes");
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(custOrderList.getSelectedIndex() != -1){
					
					switch((statusDropDown.getSelectedIndex() + 1)){
						case 1:	newDbConnection.updateCustOrderStatus((custOrderList.getSelectedIndex() + 1), "Picked", whichEmployeeTF.getText());
								break;
						case 2:	newDbConnection.updateCustOrderStatus((custOrderList.getSelectedIndex() + 1), "Packed", whichEmployeeTF.getText());
								break;
						case 3:	newDbConnection.updateCustOrderStatus((custOrderList.getSelectedIndex() + 1), "AwaitingDelivery", whichEmployeeTF.getText());
								break;
						case 4:	newDbConnection.updateCustOrderStatus((custOrderList.getSelectedIndex() + 1), "Delivered", whichEmployeeTF.getText());
								break;
					}
					
					
					
					//listOfOrders = newDbConnection.readCustOrderList(); commented 27/10/15 for locally
					ArrayList<CustomerOrder> listOfOrders = newDbConnection.readCustOrderList();
					for(int counter=0; counter < listOfOrders.size(); counter++){
						
						if(listOfOrders.get(counter).getCustOrderID() == (custOrderList.getSelectedIndex() + 1)){
							System.out.println("Hello");
							specificOrder.setText("--Order ID: " + listOfOrders.get(counter).getCustOrderID() + " /Total Of order: " + listOfOrders.get(counter).getTotalOrderValue() + " /Order Status: " + listOfOrders.get(counter).getOrderStatus() + " \n/Is the order being work on: " + listOfOrders.get(counter).isBeingWorkedOn() + " /Which Employee: " + listOfOrders.get(counter).getWhichEmployee() + "--");
						}
					}
					
					custOrderIDs.clear();//clear listmodel
					for(int counter=0; counter < listOfOrders.size(); counter++){
						custOrderIDs.addElement("Order ID: " + Integer.toString(listOfOrders.get(counter).getCustOrderID()) + " Is the order being work on: " + listOfOrders.get(counter).isBeingWorkedOn());
					}
					//System.out.println(orderSelected);
					
				}else{
					specificOrder.setText("No order has been selected!");
				}
			}
			
		});
		
		stateChangeLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		stateChangePanel.add(stateChangeLabel);
		statusDropDown.setFont(new Font("Serif", Font.PLAIN, 40));
		stateChangePanel.add(statusDropDown);
		whichEmployeeLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		stateChangePanel.add(whichEmployeeLabel);
		whichEmployeeTF.setFont(new Font("Serif", Font.PLAIN, 40));
		stateChangePanel.add(whichEmployeeTF);
		bottomPanel.add(stateChangePanel);
		submitButton.setFont(new Font("Serif", Font.PLAIN, 40));
		bottomPanel.add(submitButton);
		
		
		
		
		
		topPanel.add(topLeftPanel);
		topPanel.add(topRightPanel);
		custOrderMenu.add(topPanel);
		custOrderMenu.add(bottomPanel);
		custOrderMenu.setLayout(new GridLayout(2,1));//1 row, 2 columns, first hold buttons, second will hold listing of orders
		custOrderMenu.setVisible(true);
		mainFrame.add(custOrderMenu);
		mainFrame.setVisible(true);
	}
	
	public void stockOrderMenu(){
		mainFrame.setTitle("Stock orders");
		//make menu vanish
		startMenu.setVisible(false);
		mainFrame.remove(startMenu);
		stockOrderMenu.removeAll();//this line will clear the page if revisited
		
		//panels
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2));
		JPanel topLeftPanel = new JPanel();
		topLeftPanel.setLayout(new GridLayout(2,1));
		JPanel topRightPanel = new JPanel();
		topRightPanel.setLayout(new GridLayout(5,1));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2));
		JPanel bottomLeftPanel = new JPanel();
		bottomLeftPanel.setLayout(new GridLayout(2,1));
		JPanel bottomRightPanel = new JPanel();
		bottomRightPanel.setLayout(new GridLayout(3,1));
		
		topLeftPanel.add(backToMenu);
		JLabel errorField = new JLabel("Any errors will display here:");
		topLeftPanel.add(errorField);
		
		JLabel currentOrdersLabel = new JLabel("Current Orders:");
		topRightPanel.add(currentOrdersLabel);
		JLabel selectOrderLabel = new JLabel("Select unplaced order from list to add products to");
		topRightPanel.add(selectOrderLabel);
		
		
		ArrayList<StockOrder> listOfOrders = newDbConnection.readStockOrderList();
		DefaultListModel stockOrders = new DefaultListModel();
		for(int counter=0; counter < listOfOrders.size(); counter++){
			stockOrders.addElement("Order ID: " + Integer.toString(listOfOrders.get(counter).getStockOrderID()) + " Order status: " + listOfOrders.get(counter).getOrderStatus());
		}
		JList currOrdersList = new JList(stockOrders);
		topRightPanel.add(currOrdersList);
		
		JButton newOrderButton = new JButton("Create new Order");
		
		newOrderButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				newDbConnection.createStockOrder();
				ArrayList<StockOrder> listOfOrders = newDbConnection.readStockOrderList();
				stockOrders.clear();
				for(int counter=0; counter < listOfOrders.size(); counter++){
					stockOrders.addElement("Order ID: " + Integer.toString(listOfOrders.get(counter).getStockOrderID()) + " Order status: " + listOfOrders.get(counter).getOrderStatus());
				}
			}	
		});
		
		topRightPanel.add(newOrderButton);
		
		JPanel statePanel = new JPanel();
		statePanel.setLayout(new GridLayout(1,3));
		JLabel stateChangeLabel = new JLabel("Change the status of the order selected:");
		JComboBox statusDropDown = new JComboBox();
		statusDropDown.addItem("Placed");
		statusDropDown.addItem("Delivered");
		JButton submitButton = new JButton("Submit changes");
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(currOrdersList.getSelectedIndex() != -1){
					
					switch((statusDropDown.getSelectedIndex() + 1)){
						case 1:	newDbConnection.updateStockOrderStatus((currOrdersList.getSelectedIndex() + 1), "Placed");
								break;
						case 2:	newDbConnection.updateStockOrderStatus((currOrdersList.getSelectedIndex() + 1), "Delivered");
								break;
					}
					
					
					
					//listOfOrders = newDbConnection.readCustOrderList(); commented 27/10/15 for locally
					ArrayList<StockOrder> listOfOrders = newDbConnection.readStockOrderList();
					
					stockOrders.clear();//clear listmodel
					for(int counter=0; counter < listOfOrders.size(); counter++){
						stockOrders.addElement("Order ID: " + Integer.toString(listOfOrders.get(counter).getStockOrderID()) + " Order status: " + listOfOrders.get(counter).getOrderStatus());
					}
					//System.out.println(orderSelected);
					
				}else{
					errorField.setText("No order has been selected!");
				}
			}
			
		});
		statePanel.add(stateChangeLabel);
		statePanel.add(statusDropDown);
		statePanel.add(submitButton);
		topRightPanel.add(statePanel);
		
		
		JLabel selectProductLabel = new JLabel("Choose Product from list:");
		bottomLeftPanel.add(selectProductLabel);
		
		ArrayList<Product> listOfProducts = newDbConnection.readProductList();
		DefaultListModel products = new DefaultListModel();
		for(int counter=0; counter < listOfProducts.size(); counter++){
			products.addElement("Product ID: " + Integer.toString(listOfProducts.get(counter).getProductID()) + " Product Name: " + listOfProducts.get(counter).getProductName());
		}
		JList productList = new JList(products);
		bottomLeftPanel.add(productList);
		
		JPanel quantityPanel = new JPanel();
		quantityPanel.setLayout(new GridLayout(1,2));
		JLabel enterQuantityLabel = new JLabel("Select quantity:");
		quantityPanel.add(enterQuantityLabel);
		JTextField quantityField = new JTextField("0");
		quantityPanel.add(quantityField);
		bottomRightPanel.add(quantityPanel);
		
		JPanel porouswarePanel = new JPanel();
		porouswarePanel.setLayout(new GridLayout(1,2));
		ButtonGroup porouswareOptions = new ButtonGroup();
		JRadioButton yesPorousware = new JRadioButton("Porousware");
		porouswareOptions.add(yesPorousware);
		JRadioButton noPorousware = new JRadioButton("No Porousware");
		porouswareOptions.add(noPorousware);
		porouswarePanel.add(yesPorousware);
		porouswarePanel.add(noPorousware);
		bottomRightPanel.add(porouswarePanel);
		
		JButton addProductsButton = new JButton("Add products to order");
		addProductsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				newDbConnection.addStockOrderLine((currOrdersList.getSelectedIndex() + 1), (productList.getSelectedIndex() + 1), Integer.parseInt(quantityField.getText()), yesPorousware.isSelected());
			}	
		});
		bottomRightPanel.add(addProductsButton);
		
		
		
		topPanel.add(topLeftPanel);
		topPanel.add(topRightPanel);
		stockOrderMenu.add(topPanel);
		bottomPanel.add(bottomLeftPanel);
		bottomPanel.add(bottomRightPanel);
		stockOrderMenu.add(bottomPanel);
		stockOrderMenu.setVisible(true);
		stockOrderMenu.setLayout(new GridLayout(2,1));
		mainFrame.add(stockOrderMenu);
		mainFrame.setVisible(true);
	}
	
	public void travellingSalesman(){
		mainFrame.setTitle("Travelling Salesman");
		//make menu vanish
		startMenu.setVisible(false);
		mainFrame.remove(startMenu);
		travellingSalesmanMenu.removeAll();//this line will clear the page if revisited
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2));
		JPanel topLeftPanel = new JPanel();
		topLeftPanel.setLayout(new GridLayout(2,1));
		JPanel topRightPanel = new JPanel();
		topRightPanel.setLayout(new GridLayout(8,1));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1));
		
		
		topLeftPanel.add(backToMenu);
		JLabel errorField = new JLabel("Any errors will display here:");
		topLeftPanel.add(errorField);
		
		JLabel moreInfoLabel = new JLabel("Click an order for more info!");
		bottomPanel.add(moreInfoLabel);
		
		//list which will contain all current orders
    	ArrayList<CustomerOrder> listOfOrders = newDbConnection.readCustOrderList();
		DefaultListModel custOrderIDs = new DefaultListModel();
		for(int counter=0; counter < listOfOrders.size(); counter++){
			custOrderIDs.addElement("Order ID: " + Integer.toString(listOfOrders.get(counter).getCustOrderID()) + " Is the order being work on: " + listOfOrders.get(counter).isBeingWorkedOn());
		}
		JList custOrderList = new JList(custOrderIDs);
		bottomPanel.add(custOrderList);
		
		JLabel orderLinesLabel = new JLabel("Customer Order Lines for selected order");
		bottomPanel.add(orderLinesLabel);
		
		DefaultListModel custOrderLines = new DefaultListModel();
		JList custOrderLinesList = new JList(custOrderLines);
		custOrderList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				ArrayList<CustOrderLines> listOfCustOrderLines = newDbConnection.readCustOrderLines();
				custOrderLines.clear();//clear listmodel
				for(int counter=0; counter < listOfCustOrderLines.size(); counter++){
							
					if(listOfCustOrderLines.get(counter).getCustOrderID() == (custOrderList.getSelectedIndex() + 1)){
						custOrderLines.addElement("Order Line ID: " + Integer.toString(listOfCustOrderLines.get(counter).getCustOrderLineID()) + " Customer Order ID: " + Integer.toString(listOfCustOrderLines.get(counter).getCustOrderID()) + " Product ID: " + Integer.toString(listOfCustOrderLines.get(counter).getProductID()) + " Quantity: " + Integer.toString(listOfCustOrderLines.get(counter).getQuantity()) + " Warehouse Location: " + listOfCustOrderLines.get(counter).getWarehouseLocation());
					}
				}
			}
		});
		bottomPanel.add(custOrderLinesList);
		
		JLabel noOfLocationsLabel = new JLabel("Enter number of product locations from order");
		JLabel enterLocationLabel = new JLabel("Enter product locations from order");
		JTextField firstProdLocation = new JTextField("Enter 1st location");
		firstProdLocation.setEditable(false);
		JTextField secondProdLocation = new JTextField("Enter 2nd location");
		secondProdLocation.setEditable(false);
		JTextField thirdProdLocation = new JTextField("Enter 3rd location");
		thirdProdLocation.setEditable(false);
		
		JComboBox noOfProducts = new JComboBox();
		noOfProducts.addItem("1");
		noOfProducts.addItem("2");
		noOfProducts.addItem("3");
		noOfProducts.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(noOfProducts.getSelectedItem().equals("1") == true){
					firstProdLocation.setEditable(true);
					secondProdLocation.setEditable(false);
					thirdProdLocation.setEditable(false);
				} else if(noOfProducts.getSelectedItem().equals("2") == true){
					firstProdLocation.setEditable(true);
					secondProdLocation.setEditable(true);
					thirdProdLocation.setEditable(false);
				} else if(noOfProducts.getSelectedItem().equals("3") == true){
					firstProdLocation.setEditable(true);
					secondProdLocation.setEditable(true);
					thirdProdLocation.setEditable(true);
				}
			}	
		});
		topRightPanel.add(noOfLocationsLabel);
		topRightPanel.add(noOfProducts);
		topRightPanel.add(enterLocationLabel);
		topRightPanel.add(firstProdLocation);
		topRightPanel.add(secondProdLocation);
		topRightPanel.add(thirdProdLocation);
		
		JButton generateRoute = new JButton("Get fastest route:");
		JLabel fastestRouteLabel = new JLabel();
		generateRoute.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(firstProdLocation.getText().equals("Enter 1st location") == false){
					fastestRouteLabel.setText(travellingSalesman.getWarehouseRoute(firstProdLocation.getText()));
				}
				if((firstProdLocation.getText().equals("Enter 1st location") == false) && (secondProdLocation.getText().equals("Enter 2nd location") == false)){
					fastestRouteLabel.setText(travellingSalesman.getWarehouseRoute(firstProdLocation.getText(), secondProdLocation.getText()));
				}
				if((firstProdLocation.getText().equals("Enter 1st location") == false) && (secondProdLocation.getText().equals("Enter 2nd location") == false) && (thirdProdLocation.getText().equals("Enter 3rd location") == false)){
					fastestRouteLabel.setText(travellingSalesman.getWarehouseRoute(firstProdLocation.getText(), secondProdLocation.getText(), thirdProdLocation.getText()));
				}
			}	
		});
		topRightPanel.add(generateRoute);
		topRightPanel.add(fastestRouteLabel);
		
		topPanel.add(topLeftPanel);
		topPanel.add(topRightPanel);
		travellingSalesmanMenu.add(topPanel);
		travellingSalesmanMenu.add(bottomPanel);
		travellingSalesmanMenu.setVisible(true);
		travellingSalesmanMenu.setLayout(new GridLayout(2,1));
		mainFrame.add(travellingSalesmanMenu);
		mainFrame.setVisible(true);
	}
	public static void main(String args[]) {
		WOTSGUI1 guiTest1 = new WOTSGUI1();
	}
}
