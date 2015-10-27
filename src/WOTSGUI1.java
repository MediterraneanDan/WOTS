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
	
	//aggregate classes
	ManageDatabase newDbConnection = new ManageDatabase();
	
	WOTSGUI1(){
		mainFrame.setTitle("Warehouse Tracking System");
		mainFrame.setSize(1500, 1000);
		mainFrame.setResizable(false);
		startMenu();
		mainFrame.setVisible(true);
	}
	
	//Attributes
	
	//Methods
	public void startMenu() {
		
		//panels
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(2,1));
		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(new GridLayout(1,2));
		
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
		
		startMenu.add(titlePanel);
		startMenu.add(choicePanel);
		startMenu.setLayout(new GridLayout(2,1));
		//setTitle("Warehouse Tracking System");
		//setSize(500, 500);
		startMenu.setVisible(true);
		mainFrame.add(startMenu);
	}
	
	//int listChoice =0;//for the list of orders
	//ArrayList<CustomerOrder> listOfOrders = newDbConnection.readCustOrderList();//need these outside, commented on 27/10/15 to try variable locally
	//int orderSelected;//

	
	public void custOrderMenu(){
		//make the main menu vanish
		startMenu.setVisible(false);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,1));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2));
		JPanel bottomLeftPanel = new JPanel();
		bottomLeftPanel.setLayout(new GridLayout(2,1));
		JPanel bottomRightPanel = new JPanel();
		bottomRightPanel.setLayout(new GridLayout(3,1));
		
		JLabel moreInfoLabel = new JLabel("Click an order for more info!");
    	topPanel.add(moreInfoLabel);
		
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
		
		JLabel specificOrder = new JLabel();
		custOrderList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				//orderSelected = custOrderList.getSelectedIndex() + 1;
				//listOfOrders = newDbConnection.readCustOrderList(); commented 27/10/15 for locally
				ArrayList<CustomerOrder> listOfOrders = newDbConnection.readCustOrderList();
				for(int counter=0; counter < listOfOrders.size(); counter++){
							
					if(listOfOrders.get(counter).getCustOrderID() == (custOrderList.getSelectedIndex() + 1)){
						specificOrder.setText("--Order ID: " + listOfOrders.get(counter).getCustOrderID() + " /Total Of order: £" + listOfOrders.get(counter).getTotalOrderValue() + " /Order Status: " + listOfOrders.get(counter).getOrderStatus() + " /Is the order being work on: " + listOfOrders.get(counter).isBeingWorkedOn() + "--");
					}
				}
				//custOrderMenu.add(new JLabel());
			}
		});
		topPanel.add(custOrderList);
		specificOrder.setFont(new Font("Serif", Font.PLAIN, 18));
		bottomLeftPanel.add(specificOrder);
		
		//code for changing the state of the selected order
		JLabel stateChangeLabel = new JLabel("Would you like to change the status of the order selected:");
		JComboBox statusDropDown = new JComboBox();
		statusDropDown.addItem("Picked");
		statusDropDown.addItem("Packed");
		statusDropDown.addItem("AwaitingDelivery");
		statusDropDown.addItem("Delivered");
		JButton submitButton = new JButton("Submit changes");
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(custOrderList.getSelectedIndex() != -1){
					
					switch((statusDropDown.getSelectedIndex() + 1)){
						case 1:	newDbConnection.updateOrderStatus((custOrderList.getSelectedIndex() + 1), "Picked");
								break;
						case 2:	newDbConnection.updateOrderStatus((custOrderList.getSelectedIndex() + 1), "Packed");
								break;
						case 3:	newDbConnection.updateOrderStatus((custOrderList.getSelectedIndex() + 1), "AwaitingDelivery");
								break;
						case 4:	newDbConnection.updateOrderStatus((custOrderList.getSelectedIndex() + 1), "Delivered");
								break;
					}
					
					
					
					//listOfOrders = newDbConnection.readCustOrderList(); commented 27/10/15 for locally
					ArrayList<CustomerOrder> listOfOrders = newDbConnection.readCustOrderList();
					for(int counter=0; counter < listOfOrders.size(); counter++){
						
						if(listOfOrders.get(counter).getCustOrderID() == (custOrderList.getSelectedIndex() + 1)){
							System.out.println("Hello");
							specificOrder.setText("--Order ID: " + listOfOrders.get(counter).getCustOrderID() + " /Total Of order: £" + listOfOrders.get(counter).getTotalOrderValue() + " /Order Status: " + listOfOrders.get(counter).getOrderStatus() + " /Is the order being work on: " + listOfOrders.get(counter).isBeingWorkedOn() + "--");
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
		bottomRightPanel.add(stateChangeLabel);
		statusDropDown.setFont(new Font("Serif", Font.PLAIN, 40));
		bottomRightPanel.add(statusDropDown);
		submitButton.setFont(new Font("Serif", Font.PLAIN, 40));
		bottomRightPanel.add(submitButton);
		
		//code for table commented out!!!!
		/*Vector<String> tableFieldNames = new Vector();
		tableFieldNames.addElement("Order ID");
		tableFieldNames.addElement("Order Total");
		tableFieldNames.addElement("Order Status");
		tableFieldNames.addElement("Is the order being worked on");
		
		Vector<String> specificInfo = new Vector();
		Vector<Vector> rowData = new Vector<Vector>();
		
		JTable specificOrder = new JTable(2, 4);
		specificOrder.setValueAt("Order ID", 1, 1);
		specificOrder.setValueAt("Order Total", 1, 2);
		specificOrder.setValueAt("Status", 1, 3);
		specificOrder.setValueAt("Is the order being worked on", 1, 4);
		
		//JTable specificOrder = new JTable();    also link to important shizzle: http://www.java2s.com/Tutorial/Java/0240__Swing/publicJTableVectorrowDataVectorcolumnNames.htm
		custOrderList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				int listChoice = custOrderList.getSelectedIndex() + 1;
				for(int counter=0; counter < listOfOrders.size(); counter++){
					
					if(listOfOrders.get(counter).getCustOrderID() == listChoice){
						specificOrder.setValueAt(Integer.toString(listOfOrders.get(counter).getCustOrderID()), 2, 1);
						specificOrder.setValueAt(Float.toString(listOfOrders.get(counter).getTotalOrderValue()), 2, 2);
						specificOrder.setValueAt(listOfOrders.get(counter).getOrderStatus(), 2, 3);
						specificOrder.setValueAt(Boolean.toString(listOfOrders.get(counter).isBeingWorkedOn()), 2, 4);
						/*specificInfo.addElement(Integer.toString(listOfOrders.get(counter).getCustOrderID()));
						specificInfo.addElement(Float.toString(listOfOrders.get(counter).getTotalOrderValue()));
						specificInfo.addElement(listOfOrders.get(counter).getOrderStatus());
						specificInfo.addElement(Boolean.toString(listOfOrders.get(counter).isBeingWorkedOn()));
						//Vector<Vector> rowData = new Vector<Vector>();
						//rowData.add(specificInfo);
						//JTable specificOrder = new JTable(rowData, tableFieldNames);
						//bottomLeftPanel.add(specificOrder); 
						/*SwingUtilities.updateComponentTreeUI(mainFrame);
						mainFrame.invalidate();
						mainFrame.validate();
						mainFrame.repaint();
					}
				}
				//custOrderMenu.add(new JLabel());
			}
		});
		topPanel.add(custOrderList);
		bottomLeftPanel.add(specificOrder); 
		//JTable specificOrder = new JTable(specificInfo, tableFieldNames);
		//bottomLeftPanel.add(specificOrder);    //commented out testing for table alternative
		
		
		
		//list which will contain all current orders
		//notes could display object of customer orders then if one is clicked search orderlines by the id of the order clicked
		//ArrayList<CustomerOrder> listOfOrders = newDbConnection.readCustOrderList();
		//Vector<String> custOrderIDs = new Vector();
		//for(int counter=0; counter < listOfOrders.size(); counter++){
			//custOrderIDs.add("Order ID: " + Integer.toString(listOfOrders.get(counter).getCustOrderID()) + " OrderStatus: " + listOfOrders.get(counter).getOrderStatus());
		//}
		//JTable custOrderList = new JTable(custOrderIDs);
		*/
		
		//code for printing specific order
		
		
		
		
		custOrderMenu.add(topPanel);
		bottomPanel.add(bottomLeftPanel);
		bottomPanel.add(bottomRightPanel);
		custOrderMenu.add(bottomPanel);
		custOrderMenu.setLayout(new GridLayout(2,1));//1 row, 2 columns, first hold buttons, second will hold listing of orders
		custOrderMenu.setVisible(true);
		mainFrame.add(custOrderMenu);
		mainFrame.setVisible(true);
	}
	
	public void stockOrderMenu(){
		startMenu.setVisible(false);
		//panels
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,2));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2));
		JPanel bottomLeftPanel = new JPanel();
		bottomLeftPanel.setLayout(new GridLayout(2,1));
		JPanel bottomRightPanel = new JPanel();
		bottomRightPanel.setLayout(new GridLayout(5,1));
		
		JLabel currentOrdersLabel = new JLabel("Current Orders");
		topPanel.add(currentOrdersLabel);
		JButton newOrderButton = new JButton("Create new Order");
		topPanel.add(newOrderButton);
		
		ArrayList<StockOrder> listOfOrders = newDbConnection.readStockOrderList();
		DefaultListModel stockOrders = new DefaultListModel();
		for(int counter=0; counter < listOfOrders.size(); counter++){
			stockOrders.addElement("Order ID: " + Integer.toString(listOfOrders.get(counter).getStockOrderID()) + " Total order value: " + listOfOrders.get(counter).getTotalOrderValue());
		}
		JList currOrdersList = new JList(stockOrders);
		topPanel.add(currOrdersList);
		
		newOrderButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				newDbConnection.createStockOrder();
				ArrayList<StockOrder> listOfOrders = newDbConnection.readStockOrderList();
				stockOrders.clear();
				for(int counter=0; counter < listOfOrders.size(); counter++){
					stockOrders.addElement("Order ID: " + Integer.toString(listOfOrders.get(counter).getStockOrderID()) + " Total order value: " + listOfOrders.get(counter).getTotalOrderValue());
				}
			}	
		});
		
		JLabel selectOrderLabel = new JLabel("Select order to add products");
		topPanel.add(selectOrderLabel);
		
		JLabel selectProductLabel = new JLabel("Select Product");
		bottomLeftPanel.add(selectProductLabel);
		
		ArrayList<Product> listOfProducts = newDbConnection.readProductList();
		DefaultListModel products = new DefaultListModel();
		for(int counter=0; counter < listOfProducts.size(); counter++){
			products.addElement("Product ID: " + Integer.toString(listOfProducts.get(counter).getProductID()) + " Product Name: " + listOfProducts.get(counter).getProductName() + " Selling Price: " + listOfProducts.get(counter).getSellingPrice() + " Stock Price: " + listOfProducts.get(counter).getStockPrice());
		}
		JList productList = new JList(products);
		topPanel.add(productList);
		
		stockOrderMenu.add(topPanel);
		bottomPanel.add(bottomLeftPanel);
		bottomPanel.add(bottomRightPanel);
		stockOrderMenu.add(bottomPanel);
		stockOrderMenu.setVisible(true);
		stockOrderMenu.setLayout(new GridLayout(2,1));
		mainFrame.add(stockOrderMenu);
		mainFrame.setVisible(true);
	}
	public static void main(String args[]) {
		WOTSGUI1 guiTest1 = new WOTSGUI1();
		//guiTest1.startMenu();
	}
}
