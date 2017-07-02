package fish.potato.arduino.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import fish.potato.arduino.Constants;

public class ClassWriter {
	public void createClass(MessageClass message) throws IOException {
		File messageClass = new File(Constants.WORKING_DIR + Constants.MESSAGE_FILE + ".c");
		
		FileWriter classWriter = new FileWriter(messageClass, false);
		classWriter.write(message.expand());
		classWriter.close();
	}
}
