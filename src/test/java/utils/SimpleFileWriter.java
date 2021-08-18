package utils;

import java.io.FileWriter;
import java.util.List;

public class SimpleFileWriter {
	
	String fileName;
	FileWriter fileWriter;
	
	public SimpleFileWriter() {
		
	}
	
	public void writeTextLine(String filePath, List<String> listString) {
		try {
			FileWriter fw = new FileWriter(filePath);
			
			for (int i = 0; i < listString.size(); i++) {
	            for (int j = 0; j < listString.get(i).length(); j++)
	                fw.write(listString.get(i).charAt(j));		
	            	fw.write(System.lineSeparator());
			}
            System.out.println("Successfully written");
            fw.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
