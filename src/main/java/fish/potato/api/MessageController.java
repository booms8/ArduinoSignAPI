package fish.potato.api;

import java.io.IOException;

import fish.potato.arduino.ArduinoLoader;
import fish.potato.arduino.Constants;
import fish.potato.arduino.util.ClassReader;
import fish.potato.arduino.util.FormatString;

public class MessageController {
	private ClassReader reader;
	
	public MessageController() {
		this.reader = new ClassReader();
	}
	
	public MessageController(ClassReader reader) {
		this.reader = reader;
	}
	
	public String getMessage() throws IOException {
		return FormatString.unformatMessage(reader.read());
	}
	
	public Long getLastWriten() throws IOException {
		return FormatString.getTimeWritten(reader.read());
	}
	
	public String update(String newMessage) throws IOException, InterruptedException {
		if (canWrite(newMessage)) {
			changeMessage(newMessage);
			return "OK";
		}
		else {
			return "Message was recently rewritten. Wait your turn!";
		}
	}
	
	boolean canWrite(String newMessage) throws IOException {
		if (newMessage.toUpperCase().equals(FormatString.unformatMessage(reader.read()))) {
			return true;
		}
		Long timeWritten = FormatString.getTimeWritten(reader.read());
		Long timeNow = System.currentTimeMillis() / 1000L;
		return (timeNow - timeWritten) > Constants.MESSAGE_TIMEOUT;
	}
	
	private void changeMessage(String newMessage) throws IOException, InterruptedException {
		ArduinoLoader.reprogram(newMessage, 1);
	}
	
	public void changeMode(int mode) throws IOException, InterruptedException {
		ArduinoLoader.reprogram(FormatString.unformatMessage(reader.read()), mode);
	}
}
