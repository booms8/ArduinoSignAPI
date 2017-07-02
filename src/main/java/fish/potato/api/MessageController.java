package fish.potato.api;

import java.io.IOException;

import fish.potato.arduino.ArduinoLoader;
import fish.potato.arduino.Constants;
import fish.potato.arduino.MessageClass;
import fish.potato.arduino.util.ClassReader;
import fish.potato.arduino.util.FormatString;

public class MessageController {
	private ClassReader reader;
	
	public MessageController() {
		this.reader = new ClassReader();
	}
	
	MessageController(ClassReader reader) {
		this.reader = reader;
	}
	
	public String getMessage() throws IOException {
		return reader.read().getMessage();
	}
	
	public Long getLastWriten() throws IOException {
		return reader.read().getLastWritten();
	}
	
	public int getMode() throws IOException {
		return reader.read().getMode();
	}
	
	public String setMessage(String newMessage) throws IOException, InterruptedException {
		if (canWrite(newMessage)) {
			ArduinoLoader.reprogram(new MessageClass(newMessage, reader.read().getMode()));
			return "OK";
		}
		else {
			return "Message was recently rewritten. Wait your turn!";
		}
	}
	
	public void setMode(int mode) throws IOException, InterruptedException {
		ArduinoLoader.reprogram(new MessageClass(reader.read().getMessage(), mode));
	}
	
	boolean canWrite(String newMessage) throws IOException {
		MessageClass current = reader.read();
		if (FormatString.cleanMessage(newMessage).equals(current.getMessage())) {
			return true;
		}
		Long timeWritten = current.getLastWritten();
		Long timeNow = System.currentTimeMillis() / 1000L;
		return (timeNow - timeWritten) > Constants.MESSAGE_TIMEOUT;
	}
}
