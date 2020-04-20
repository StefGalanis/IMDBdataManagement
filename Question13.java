import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question13 {
	
	private String ratingsLine;
	private String titlesLine;
	private long startTime;
	private long stopTime;
	private int iterations;
	private String [] ratingsValues;
	private String [] titlesValues;
	
	public Question13() throws IOException {
		
		try {

			
			BufferedReader bufferedRatingsReader = new BufferedReader(new FileReader("sortedFiles/title.ratings.tsv"));
			BufferedReader bufferedMoviesReader = new BufferedReader(new FileReader("sortedFiles/title.basics.tsv"));
			
			bufferedRatingsReader.readLine();
			bufferedMoviesReader.readLine();
			
			FileWriter fileWriter = new FileWriter("output1.3.tsv");
			
			fileWriter.write("primaryTitle \n");
			

			HashMap<String,String> ratingsHashMap = new HashMap<String,String>();
			
			startTime = System.nanoTime();
						
			while(true) {
				
				iterations = 0;
				
				while((ratingsLine = bufferedRatingsReader.readLine())!=null ) {

					ratingsValues = ratingsLine.split("\t");
					ratingsHashMap.put(ratingsValues[0],ratingsValues[1]);
					iterations = iterations + 1;
					if (iterations>99) {
						break;
					}
					
				}
				
				
				iterations = 0;
				
				while((titlesLine = bufferedMoviesReader.readLine())!=null) {
					titlesValues = titlesLine.split("\t");
					if(ratingsHashMap.containsKey(titlesValues[0])) {
						ratingsHashMap.remove(titlesValues[0]);
					}
					else {
						fileWriter.write(titlesValues[2] + "\n");
					}
					iterations = iterations + 1;
					if (iterations>99) {
						break;
					}
				}
				
				if (ratingsLine == null ) {
					break;
				}
				
			}
	
			ratingsHashMap.clear();
			fileWriter.close();
			bufferedRatingsReader.close();
			bufferedMoviesReader.close();
			
			stopTime = System.nanoTime();
			System.out.println("for Question 1.3 the Execution time is:" +"\t" + (stopTime - startTime) + "ns");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		Question13 question13 = new Question13();
	}

}
