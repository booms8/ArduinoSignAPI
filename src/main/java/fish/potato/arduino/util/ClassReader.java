package fish.potato.arduino.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import fish.potato.arduino.Constants;

public class ClassReader {
	public String read() throws IOException {
		File messageClass = new File(Constants.WORKING_DIR + Constants.MESSAGE_FILE + ".c");
		FileReader reader = new FileReader(messageClass);
		
		char[] chars = new char[(int) messageClass.length()];
		reader.read(chars);
		reader.close();
			
		return new String(chars);
	}
}
