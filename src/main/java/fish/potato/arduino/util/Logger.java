package fish.potato.arduino.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fish.potato.arduino.Constants;

public class Logger {
	
	public void addLine(String line) throws IOException {
		String formattedLine = createLine(line);
		FileWriter logWriter = new FileWriter(new File(Constants.LOG_FILE), true);
		logWriter.write(formattedLine);
		logWriter.close();
	}
	
	protected String createLine(String line) {
		SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		String time = format.format(new Date());
		return "[" + time + "] " + line + "\n";
	}
}
