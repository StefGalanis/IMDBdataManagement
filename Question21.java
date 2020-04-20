import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;





public class Question21 {
	
	

	public Question21() {
		
		try {
			BufferedReader bufferedRatingsFileReader = new BufferedReader(new FileReader("sortedFiles/title.ratings.tsv"));
			bufferedRatingsFileReader.readLine();
			
			String ratingsFileLine;
			
			int [] counter = new int[10];
			ArrayList<Double> ratingsArrayList = new ArrayList<Double>();
			HashMap<String, Double> ratingHashMap = new HashMap<String, Double>();
			Arrays.fill(counter, 0);
			
			long startingTime = System.nanoTime();
			
			while((ratingsFileLine = bufferedRatingsFileReader.readLine()) != null) {
				String [] lineValues = ratingsFileLine.split("\t");
				double movieRating = Double.valueOf(lineValues[1]);
				ratingsArrayList.add(movieRating);
				ratingHashMap.put(lineValues[0], movieRating);
				if (movieRating >= 0.1 && movieRating <=1.0) {
					counter[0]++;
				}
				else if (movieRating >= 1.1 && movieRating <= 2.0) {
					counter[1]++;
				}
				else if (movieRating >= 2.1 && movieRating <= 3.0) {
					counter[2]++;
				}
				else if (movieRating >= 3.1 && movieRating <= 4.0) {
					counter[3]++;
				}
				else if (movieRating >= 4.1 && movieRating <= 5.0) {
					counter[4]++;
				}
				else if (movieRating >= 5.1 && movieRating <= 6.0) {
					counter[5]++;
				}
				else if (movieRating >= 6.1 && movieRating <= 7.0) {
					counter[6]++;
				}
				else if (movieRating >= 7.1 && movieRating <= 8.0) {
					counter[7]++;
				}
				else if (movieRating >= 8.1 && movieRating <= 9.0) {
					counter[8]++;
				}else {
					counter[9]++;
				}
			}
			for(int counterValue : counter) {
				System.out.println(counterValue);
			}
			
			long stopingTime = System.nanoTime();
			
			long hashingTime = stopingTime - startingTime;
			
			System.out.println("The Execution Time of question 2.1 (HASHING):" + "\t" + (stopingTime - startingTime) + "ns");
			
			Arrays.fill(counter, 0);
			
			long startTime = System.nanoTime();
			
			Collections.sort(ratingsArrayList);

			for (double movieRating : ratingsArrayList) {
				if (movieRating >= 0.1 && movieRating <=1.0) {
					counter[0]++;
				}
				else if (movieRating >= 1.1 && movieRating <= 2.0) {
					counter[1]++;
				}
				else if (movieRating >= 2.1 && movieRating <= 3.0) {
					counter[2]++;
				}
				else if (movieRating >= 3.1 && movieRating <= 4.0) {
					counter[3]++;
				}
				else if (movieRating >= 4.1 && movieRating <= 5.0) {
					counter[4]++;
				}
				else if (movieRating >= 5.1 && movieRating <= 6.0) {
					counter[5]++;
				}
				else if (movieRating >= 6.1 && movieRating <= 7.0) {
					counter[6]++;
				}
				else if (movieRating >= 7.1 && movieRating <= 8.0) {
					counter[7]++;
				}
				else if (movieRating >= 8.1 && movieRating <= 9.0) {
					counter[8]++;
				}else {
					counter[9]++;
				}
			}
			
			for(int counterValue : counter) {
				System.out.println(counterValue);
			}
			
			
			long stopTime = System.nanoTime();
			
			long sortingTime = stopTime - startTime;
			
			System.out.println("Sorting The Execution Time of question 2.1 as a sorted list is(SORTING):" + "\t" + (stopTime - startTime) + "ns");
			bufferedRatingsFileReader.close();
			
			if(sortingTime>hashingTime) {
				System.out.println("hashing time is better");
			}
			else {
				System.out.println("sorting time is better");
			}
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		Question21 question21 = new Question21();
	}
}
