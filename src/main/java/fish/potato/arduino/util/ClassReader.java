package fish.potato.arduino.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import fish.potato.arduino.Constants;
import fish.potato.arduino.MessageClass;

public class ClassReader {
	public MessageClass read() throws IOException {
		File messageClass = new File(Constants.WORKING_DIR + Constants.MESSAGE_FILE + ".c");
		FileReader reader = new FileReader(messageClass);
		
		char[] chars = new char[(int) messageClass.length()];
		reader.read(chars);
		reader.close();
		
		String content = new String(chars);
		
		return new MessageClass(FormatString.unformatMessage(content), FormatString.getTimeWritten(content), FormatString.getMode(content));
	}
}
