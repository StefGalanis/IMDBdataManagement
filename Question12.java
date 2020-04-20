import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Question12 {

	private String line;
	private long startTime;
	private long stopTime;
	private int iterations;
	private String [] fieldValues;

	public Question12() throws IOException {
		try {

			HashMap<String,String> episodeHashMap = new HashMap<String, String>();
			
			BufferedReader bufferedEpisodeReader = new BufferedReader(new FileReader("sortedFiles/title.episode.tsv"));
			BufferedReader bufferedMoviesReader = new BufferedReader(new FileReader("sortedFiles/title.basics.tsv"));
			
			bufferedEpisodeReader.readLine();
			bufferedMoviesReader.readLine();
			
			FileWriter fileWriter = new FileWriter("output1.2.tsv");
			
			fileWriter.write("primaryTitle \t parentTconst \t seasonNumber \n");
			
			startTime = System.nanoTime();
			
			while(true) {
				
					
					
				iterations = 0;
				while((line = bufferedEpisodeReader.readLine())!=null && iterations < 10) {
					fieldValues = line.split("\t");
					if (fieldValues[3].equals("1")) {
						String valueString = fieldValues[1] + "\t" + fieldValues[2];
						episodeHashMap.put(fieldValues[0], valueString);
					}
					iterations++;
				}
				
				iterations = 0;
				
				while((line = bufferedMoviesReader.readLine())!=null && (episodeHashMap.isEmpty() == false)) {
					fieldValues = line.split("\t");
					if(episodeHashMap.containsKey(fieldValues[0]) ) {
						String outputLine = fieldValues[2] + "\t" + episodeHashMap.get(fieldValues[0]) + "\n";
						fileWriter.write(outputLine);
						episodeHashMap.remove(fieldValues[0]);
					}
					if(episodeHashMap.isEmpty()) break;
					iterations++;
				}
				
				if (line == null) {
					break;
				}
				episodeHashMap.clear();
			}
			
			fileWriter.close();
			bufferedEpisodeReader.close();
			bufferedMoviesReader.close();
			
			stopTime = System.nanoTime();
			System.out.println("for Question 1.2 the Execution time is:" +"\t" + (stopTime - startTime) + "ns");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args) throws IOException {
		Question12 question12 = new Question12();
	}
}
