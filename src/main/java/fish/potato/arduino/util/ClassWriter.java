package fish.potato.arduino.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import fish.potato.arduino.Constants;

public class ClassWriter {
	public void createClass(String formattedMessage, int mode) throws IOException {
		String content = "#include \"" + Constants.CHAR_FILE + ".h\"\n\n" +
				"//[" + System.currentTimeMillis() / 1000L + "]\n\n" +
				"static int mode = " + mode + ";\n" +
				formattedMessage;
		
		File messageClass = new File(Constants.WORKING_DIR + Constants.MESSAGE_FILE + ".c");
		
		FileWriter classWriter = new FileWriter(messageClass, false);
		classWriter.write(content);
		classWriter.close();
	}
}
