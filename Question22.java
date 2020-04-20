import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


public class Question22 {

	public Question22() throws IOException {
		
		try {
			BufferedReader bufferedRatingsReader = new BufferedReader(new FileReader("sortedFiles/title.ratings.tsv"));
			BufferedReader bufferedMoviesReader = new BufferedReader(new FileReader("sortedFiles/title.basics.tsv"));
			
			bufferedRatingsReader.readLine();
			bufferedMoviesReader.readLine();
			
			HashMap<String, Double> ratingsHashMap = new HashMap<String, Double>();
			HashMap<String, String> startYearHashMap = new HashMap<String, String>();
			
			String ratingsLine;
			String [] ratingsLineFields;
			String tConst;
			double rating;
			int iterations = 0;
			
			while(true) {
			
			iterations = 0;
			
			while((ratingsLine = bufferedRatingsReader.readLine()) != null && iterations < 1000) {
				ratingsLineFields = ratingsLine.split("\t");
				tConst = ratingsLineFields[0];
				rating = Double.valueOf(ratingsLineFields[1]);
				ratingsHashMap.put(tConst, rating);
				iterations++;
			}
			
			iterations =0;
			String moviesLine;
			String [] moviesLineFields;
			String startYear;
			String [] values;
			String newValues;
			int count;
			double sum;
			double avg;
			
			while((moviesLine = bufferedMoviesReader.readLine()) != null && iterations < 1000) {
				moviesLineFields = moviesLine.split("\t");
				tConst = moviesLineFields[0];
				startYear = moviesLineFields[5];
				

				
				if(ratingsHashMap.containsKey(tConst)) {
					rating = ratingsHashMap.get(tConst);
					if(startYearHashMap.containsKey(startYear)) {
						values = startYearHashMap.get(startYear).split("\t");
						
						count = Integer.valueOf(values[0]);
						sum = Double.valueOf(values[1]);
						avg = Double.valueOf(values[2]);
						
						count++;
						sum += rating;
						avg = sum / count;
						
						
						newValues = count + "\t" + sum + "\t" + avg;
						startYearHashMap.put(startYear, newValues);
						ratingsHashMap.remove(tConst);
					}
					else {
						count = 1;
						sum = rating;
						avg = sum / count ;
						
						newValues = count + "\t" + sum + "\t" + avg;
						startYearHashMap.put(startYear, newValues);
						ratingsHashMap.remove(tConst);
					}
				}
				iterations++;
			}
			
			ArrayList<String> sortedKeys = 
                    new ArrayList<String>(startYearHashMap.keySet()); 
          
			Collections.sort(sortedKeys);  
			DecimalFormat df = new DecimalFormat("#.#");
  
   			for (String x : sortedKeys)  {
				values = startYearHashMap.get(x).split("\t");
				System.out.println("Year = " + x + ", avarage rating = " + df.format(Double.valueOf(values[2]))); 
			}
			
			if(moviesLine == null ) {
				break;
			}
			
			
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public static void main(String[] args) throws IOException {
		Question22 question22 = new Question22();
	}

}
