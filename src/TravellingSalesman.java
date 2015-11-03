//this class will handle the travelling salesman algorithm 
//different points in the warehouse: A, B, C, D and Origin(the terminal)
//Possible routes:
/*
 Routes for a product from 1 section
 origin a origin = 10+10 = 20
 origin b origin = 21+21 = 42
 origin c origin = 32+32 = 64
 
 Routes for a product from 2 sections
 origin a b origin = 10+5+21 = 36
 origin a c origin = 10+15+32 = 57
 origin b a origin = 21+5+10 = 36
 origin b c origin = 21+9+32 = 62
 origin c a origin = 32+15+10 = 57
 origin c b origin = 32+9+21 = 62
 
 Routes for a product from all 3 sections
 origin a b c origin = 10+5+9+32 = 56
 origin a c b origin = 10+15+9+21 = 55
 origin b a c origin = 21+5+15+32 = 73
 origin b c a origin = 21+9+15+10 = 55
 origin c a b origin = 32+15+5+21 = 73
 origin c b a origin = 32+9+5+10 = 56
*/
 
public class TravellingSalesman {
	//distances from origin
	int originToA = 10;
	int originToB = 21;
	int originToC = 32;
	
	//distances from A
	int aToOrigin = originToA;
	int aToB = 5;
	int aToC = 15;
	
	//distances from B
	int bToOrigin = originToB;
	int bToA = aToB;
	int bToC = 9;
	
	//distances from C
	int cToOrigin = originToC;
	int cToA = aToC;
	int cToB = bToC;
	
	public String getWarehouseRoute(String productLocation){
		String fastestRoute = null;
		switch(productLocation){
			case "A":
				fastestRoute = "Origin A Origin";
				break;
			case "B":
				fastestRoute = "Origin B Origin";
				break;
			case "C":
				fastestRoute = "Origin C Origin";
				break;
			default :
		}
		return fastestRoute;
		
	}
	public String getWarehouseRoute(String product1stLocation, String product2ndLocation){
		String fastestRoute = null;
		if(product1stLocation.equals("A") == true && product2ndLocation.equals("B") == true){
			fastestRoute = "Origin A B Origin";
		}else if(product1stLocation.equals("A") == true && product2ndLocation.equals("C") == true){
			fastestRoute = "Origin A C Origin";
		}else if(product1stLocation.equals("B") == true && product2ndLocation.equals("A") == true){
			fastestRoute = "Origin B A Origin";
		}else if(product1stLocation.equals("B") == true && product2ndLocation.equals("C") == true){
			fastestRoute = "Origin B C Origin";
		}else if(product1stLocation.equals("C") == true && product2ndLocation.equals("A") == true){
			fastestRoute = "Origin C A Origin";
		}else if(product1stLocation.equals("C") == true && product2ndLocation.equals("B") == true){
			fastestRoute = "Origin C B Origin";
		}
		return fastestRoute;
		
	}
	public String getWarehouseRoute(String product1stLocation, String product2ndLocation, String product3rdLocation){
		String fastestRoute = null;
		if(product1stLocation.equals("A") == true && product2ndLocation.equals("B") == true && product3rdLocation.equals("C") == true){
			fastestRoute = "Origin A C B Origin";
		}else if(product1stLocation.equals("A") == true && product2ndLocation.equals("C") == true && product3rdLocation.equals("C") == true){
			fastestRoute = "Origin A C B Origin";
		}else if(product1stLocation.equals("B") == true && product2ndLocation.equals("A") == true && product3rdLocation.equals("C") == true){
			fastestRoute = "Origin A C B Origin";
		}else if(product1stLocation.equals("B") == true && product2ndLocation.equals("C") == true && product3rdLocation.equals("A") == true){
			fastestRoute = "Origin A C B Origin";
		}else if(product1stLocation.equals("C") == true && product2ndLocation.equals("A") == true && product3rdLocation.equals("B") == true){
			fastestRoute = "Origin A C B Origin";
		}else if(product1stLocation.equals("C") == true && product2ndLocation.equals("B") == true && product3rdLocation.equals("A") == true){
			fastestRoute = "Origin A C B Origin";
		}
		return fastestRoute;
	}
	
	/*public String getWarehouseRoute(int noOfLocations){
		String fastestRoute = null;
		switch(noOfLocations){
			case 1:
				
				break;
			case 2:
				fastestRoute = "Origin A B Origin";
				break;
			case 3:
				fastestRoute = "Origin A C B Origin";
				break;
			default :
		}
		return fastestRoute;
		
	}*/
	
	

}

