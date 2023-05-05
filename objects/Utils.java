package objects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Utils {

	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
		return formatter.format(date);  
	}
	
	public static LinkedList<String> spellCheck(String text) throws IOException {
		LinkedList<String> misspelled = new LinkedList<>();
		Set<String> dictionary = new HashSet<>(Files.readAllLines(Paths.get("dictionary.txt")));
		String[] words = text.split("[ .!?]");
		for (String word : words) {
			if (!dictionary.contains(word)) {
				misspelled.add(word);
			}
		}
		return misspelled;
	}
	
}
