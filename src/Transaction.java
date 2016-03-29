import java.util.LinkedHashMap;
import java.util.Map;


public class Transaction {

	LinkedHashMap<String,String> contents;
	
	public Transaction(Map m){
		contents = new LinkedHashMap();
		fillContents(m);
	}
	
	private void fillContents(Map<String,String> src){
	passengerCount(src);
	//add other fns as necessary for each desired category, massaging as needed
	}
	
	//Categories for passenger count are 1, 2, 3-4, and 5-7.
	//More than 7 is considered "surprising", so is "0 or fewer".
	private void passengerCount(Map<String,String> src){
		String count = (String) src.get("passenger_count");
		Integer numPassengers = Integer.parseInt(count);
		
		if(numPassengers > 7){
			surprise("passenger_count",src);
		}
		else if(numPassengers > 4){
			contents.put("passenger_count", "5-7");
		}
		else if(numPassengers > 2){
			contents.put("passenger_count", "3-4");
		}
		else if(numPassengers > 0){
			contents.put("passenger_count", numPassengers.toString());
		}
		else{
			surprise("passenger_count",src);
		}
		
	}
	
	private void surprise(String surprisingThing, Map src){
		System.out.println("SURPRISE ALERT in "+surprisingThing);
		System.out.println(src.toString());
	}

	public String toString() {
		return contents.toString();
	}
}
