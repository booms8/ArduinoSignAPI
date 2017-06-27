package fish.potato.arduino.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import fish.potato.arduino.Constants;

public class ClassReader {
	public static String read() throws IOException {
		String content = "";
		File messageClass = new File(Constants.WORKING_DIR + Constants.MESSAGE_FILE + ".c");
		FileReader reader = null;
		
		reader = new FileReader(messageClass);
		char[] chars = new char[(int) messageClass.length()];
		reader.read(chars);
		content = new String(chars);
		reader.close();
			
		return content;
	}
}
