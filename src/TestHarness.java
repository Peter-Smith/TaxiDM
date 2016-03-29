import java.io.File;



public class TestHarness {
	
	// for testing purposes
	public final static String DATA_LOCATION1 = "D:\\Scrap\\nycTaxiTripData2013\\trip_data\\trip_data_1.csv";
	public final static String DATA_LOCATION2 = "D:\\Scrap\\nycTaxiTripData2013\\trip_fare\\trip_fare_1.csv";
	public final static String OUTPUT_LOCATION = "D:\\Scrap\\nycTaxiTripData2013\\output.csv";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File dataFile1 = new File(DATA_LOCATION1);
		File dataFile2 = new File(DATA_LOCATION2);
		File outFile = new File(OUTPUT_LOCATION);
		DataRecord dr = new DataRecord();
		dr.readTwo(dataFile1, dataFile2, outFile);
	}

}
