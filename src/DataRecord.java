import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;




/**
 * @author Peter
 * A DataRecord class that handles file reading.
 * It tracks each loaded transaction, and also a list of all spotted attribute values.
 */
public class DataRecord {
	
	ArrayList<String> attList;
	ArrayList<ArrayList<String>> tList;
	String targetAttribute, targetValue;
	public static final int LINE_LIMIT = 100;
	
	
	/**
	 * 
	 */
	public DataRecord(){
		attList = new ArrayList<String>();

	}
	
	
	
	/**
	 * Reads a data file (as per provided with assignment)
	 * Extracts all transactions from it.
	 * @param f File to be read as data file.
	 * @return False if file can't be found, otherwise true.
	 */
	public boolean readTwo(File f1, File f2, File fOut){
		Scanner sc1, sc2;
		FileWriter fr;
		try {
			if (!fOut.exists()){
				fOut.createNewFile();
			}
			
			sc1 = new Scanner(f1);
			sc2 = new Scanner(f2);
			fr = new FileWriter(fOut);
			String attributeLine1, contentLine1;
			String attributeLine2, contentLine2;
			attributeLine1 = sc1.nextLine();
			attributeLine2 = sc2.nextLine();
			
			Iterator<String>attIter1 = Arrays.asList(attributeLine1.split(",")).iterator();
			Iterator<String>attIter2 = Arrays.asList(attributeLine1.split(",")).iterator();
			while(attIter1.hasNext()){
				attList.add(attIter1.next());
			}
			while(attIter2.hasNext()){
				attList.add(attIter2.next());
			}
			
			tList = new ArrayList<ArrayList<String>>();
			LinkedHashMap<String,String> m;
			int lines = 0;
			Transaction t;
			System.out.println(attList);
			while(sc1.hasNextLine() && sc2.hasNextLine() && (lines < LINE_LIMIT)){
				contentLine1 = sc1.nextLine();
				contentLine2 = sc2.nextLine();
				
				
				List<String> itemsList1 = Arrays.asList(contentLine1.split(","));
				List<String> itemsList2 = Arrays.asList(contentLine1.split(","));
				
				Iterator<String> itemsIter1 = itemsList1.iterator();
				Iterator<String> itemsIter2 = itemsList2.iterator();
				
				int i = 0;
				m = new LinkedHashMap<String,String>();
				while(itemsIter1.hasNext()){
					m.put(attList.get(i++), itemsIter1.next());
				}
				while(itemsIter2.hasNext()){
					m.put(attList.get(i++), itemsIter2.next());
				}
				t = new Transaction(m);
				fr.write(t.toString()+"\r\n");
				lines++;
			}
			sc1.close();
			sc2.close();
			fr.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	


public String toString(){
	return attList.toString()+"\r\n"+tList.toString();
}



public ArrayList<ArrayList<String>> getTList() {
	return tList;
}



public ArrayList<String> getAttList() {
	return attList;
}



}