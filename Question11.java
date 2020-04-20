import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



public class Question11 {
	
	private String line;
	private long startTime;
	private long stopTime;
	private int iterations;
	private String [] fieldValues;

	public Question11() throws IOException {
		try {

			HashMap<String,String> directorsHashMap = new HashMap<String, String>();
			
			BufferedReader bufferedCrewReader = new BufferedReader(new FileReader("sortedFiles/title.crew.tsv"));
			BufferedReader bufferedMoviesReader = new BufferedReader(new FileReader("sortedFiles/title.basics.tsv"));
			
			bufferedCrewReader.readLine();
			bufferedMoviesReader.readLine();
			
			FileWriter fileWriter = new FileWriter("output1.1.tsv");
			
			fileWriter.write("primaryTitle \t directors \n");
			
			startTime = System.nanoTime();
			
			while(true) {
					
					
				iterations = 0;
				while((line = bufferedCrewReader.readLine())!=null && iterations < 10000) {
					fieldValues = line.split("\t");
					if (fieldValues[1].contains(",")) {
						directorsHashMap.put(fieldValues[0], fieldValues[1]);
					}
					iterations++;
				}
				
				iterations = 0;
				
				while((line = bufferedMoviesReader.readLine())!=null && iterations < 10000) {
					fieldValues = line.split("\t");
					if(directorsHashMap.containsKey(fieldValues[0])) {
						String outputLine = fieldValues[2] + "\t" + directorsHashMap.get(fieldValues[0]) + "\n";
						fileWriter.write(outputLine);
					}
					iterations++;
				}
				
				if (line == null) {
					break;
				}
				directorsHashMap.clear();
			}
			
			fileWriter.close();
			bufferedCrewReader.close();
			bufferedMoviesReader.close();
			
			stopTime = System.nanoTime();
			System.out.println("for Question 1.1 the Execution time is:" +"\t" + (stopTime - startTime) + "ns");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		Question11 question11 = new Question11();
	}
}
