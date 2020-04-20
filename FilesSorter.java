import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;




public class FilesSorter {

	public FilesSorter(String filename) throws IOException {
	
		try {
			BufferedReader fileBufferedReader = new BufferedReader(new FileReader(filename));
			
			File directory = new File("runs");
			File sortedFilesDirectory = new File("sortedFiles");
			sortedFilesDirectory.mkdir();
			directory.mkdir();
			int externalFileSize = 1000000;
			String fieldNames = fileBufferedReader.readLine();
			HashMap<Integer, String> lines = new HashMap<Integer, String>();
			String line;
			String fields = "";
			String [] lineValues;
			int iterations;
			int runs = 1;
			
			while(true) {
				
				FileWriter fileWriter = new FileWriter("runs/runFile" + runs + ".tsv");
				BufferedWriter out = new BufferedWriter(fileWriter);

				iterations = 0;
				while((line = fileBufferedReader.readLine()) != null && iterations < externalFileSize) {
					lineValues = line.split("\t");					
					lines.put(Integer.valueOf(lineValues[0].replace("tt", "")),line);
					iterations++;
				}

				ArrayList<Integer> sortedKeys = new ArrayList<Integer>(lines.keySet());
				Collections.sort(sortedKeys);
				
				fileWriter.write(fieldNames + "\n");
				for(Integer sortedKey : sortedKeys) {
					out.write(lines.get(sortedKey) + "\n");
				}
				
				out.close();
				lines.clear();
				sortedKeys.clear();
				fileWriter.close();
				
				if (line == null) {
					break;
				}
				
				runs++;
			}
			fileBufferedReader.close();
			
			
			String runFIlesDirectoryPath = "runs";
			File runFilesDirectory = new File(runFIlesDirectoryPath);
			File[] directoryFiles = runFilesDirectory.listFiles();
			BufferedReader [] fileBufferedReaderList = new BufferedReader[directoryFiles.length];
			System.out.println(directoryFiles.length);
			String [][] buckets = new String [directoryFiles.length][2];
			HashMap<Integer, String> sortedBucket= new HashMap<Integer, String>();
			
			int i = 0;
			for(File runFile : directoryFiles) {
				fileBufferedReaderList[i] = new BufferedReader(new FileReader("runs/" + runFile.getName()));	
				line = fileBufferedReaderList[i].readLine();
				i++;
			}
			
			fillArrayWithString(buckets, "");

			Boolean [] emptyFiles = new Boolean [directoryFiles.length];
			Arrays.fill(emptyFiles, Boolean.FALSE);
			
			FileWriter fileWriter2 = new FileWriter("sortedFiles/"+ filename);
			fileWriter2.write(fieldNames + "\n");
			
			while(true) {
				for(int k = 0; k < directoryFiles.length; k++) {
					for(int j = 0; j < 2; j++) {
						if(buckets[k][j] == "") {
							if((line = fileBufferedReaderList[k].readLine()) != null ) {
								lineValues = line.split("\t");
								buckets[k][j] = line;
								String positionOfBucket = "\t" + k + "\t" + j;
								
								sortedBucket.put(Integer.valueOf(lineValues[0].replace("tt", "")), line + positionOfBucket);
							}
							else {
								emptyFiles[k] = true;
							}
						}
					}
				}
				
				ArrayList<Integer> sortedKeys = new ArrayList<Integer>(sortedBucket.keySet());
				Collections.sort(sortedKeys);

				iterations = 0;
				for(Integer sortedKey : sortedKeys) {
					
					if(iterations < 2) {
						line = sortedBucket.get(sortedKey);
						lineValues = line.split("\t");
						
						fields = "";
						for (i=0; i<lineValues.length; i++) {
							if(i >= 0 && i < lineValues.length - 3) {
								fields += (lineValues[i] + "\t");
							}
							else if( i == lineValues.length - 3){
								fields += lineValues[i];
							}
						}
						
						fileWriter2.write(fields + "\n");
						sortedBucket.remove(sortedKey);
						
						int k = Integer.valueOf(lineValues[lineValues.length-2]);
						int j = Integer.valueOf(lineValues[lineValues.length-1]);
						
						buckets[k][j] = "";
						iterations++;
					}
					
				}
				sortedKeys.clear();
				
				int numberOfEmptyFiles = 0;
				for(int k = 0; k < emptyFiles.length; k++ ) {
					if(emptyFiles[k] == true) {
						numberOfEmptyFiles++;
					}
				}
				
				if(numberOfEmptyFiles == directoryFiles.length) {
					File runsFolder = new File("runs");
					
					File[] files = runsFolder.listFiles();
					
				    if(files!=null) {
				        for(File f: files) {
				             f.delete();
				        }
				    }
				    
				    runsFolder.delete();
				    fileWriter2.close();
				    break;
				}
				
			}
			for(int k = 0; k < emptyFiles.length; k++ ) {
				if(emptyFiles[k] == true) {
					fileBufferedReaderList[k].close();
				}
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private String[][] fillArrayWithString(String [][] array, String value) {
		System.out.println(array.length );
		for(int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = value;
			}
		}
		return array;
	}
	
	
	public static void main(String [] args) throws IOException {
		long startTime = System.nanoTime();
		
		File unsortedFilesDirectory = new File("unsortedFiles");
		File[] directoryFiles = unsortedFilesDirectory.listFiles();
		for (File file : directoryFiles) {
			FilesSorter filesSorter = new FilesSorter(file.getName());
		}
		
		long stopTime = System.nanoTime();
		
		System.out.println("total sorting time is:" + "\t" + (stopTime - startTime));
	}
}
