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
	fare(src);
	payment_type(src);
	tip_amount(src);
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
	
	//Groups of approximately one standard deviation, with plenty of room for outliers
	private void fare(Map<String,String> src){
		Double fare = Double.parseDouble((String) src.get(" fare_amount"));
		if(fare >= 50){
			contents.put("fare", "50$+");
		}
		else if(fare > 40){
			contents.put("fare", "40-50$");
		}
		else if(fare > 30){
			contents.put("fare", "30-40$");
		}
		else if(fare > 20){
			contents.put("fare", "20-30$");
		}
		else if(fare > 10){
			contents.put("fare", "10-20$");
		}
		else if(fare > 0){
			contents.put("fare", "0-10$");
		}
		else{
			surprise("fare",src);
		}
	}
	
	private void trip_time(Map<String,String> src){
		int trip_time_in_secs = Integer.parseInt(src.get("trip_time_in_secs"));	
	}
	
	//Categories here are kinda arbitrary, some stats would be nice
	private void tip_amount(Map<String,String> src){
		Double tip = Double.parseDouble(src.get(" tip_amount"));
		if(tip >= 10){
			contents.put("tip", "10$+");
		}
		else if(tip > 5){
			contents.put("tip", "5-10$");
		}
		else if(tip > 3){
			contents.put("tip", "3-5$");
		}
		else if(tip > 1){
			contents.put("tip", "1-3$");
		}
		else if(tip > 0){
			contents.put("tip", "0-1$");
		}
		else if(tip == 0){
			contents.put("tip", "0$");
		}
		else{
			surprise("tip",src);
		}
	}
	
	//CSH or CRD, we can output this right through
	private void payment_type(Map<String,String> src){
		contents.put("payment_type", src.get(" payment_type"));
	}
	
	
	
	private void surprise(String surprisingThing, Map src){
		System.out.println("SURPRISE ALERT in "+surprisingThing);
		System.out.println(src.toString());
	}

	public String toString() {
		return contents.toString();
	}
}
