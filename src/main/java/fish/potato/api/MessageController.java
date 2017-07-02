package fish.potato.api;

import java.io.IOException;

import fish.potato.arduino.ArduinoLoader;
import fish.potato.arduino.Constants;
import fish.potato.arduino.util.ClassReader;
import fish.potato.arduino.util.FormatString;
import fish.potato.arduino.util.MessageClass;

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
		if (FormatString.cleanMessage(newMessage).equals(reader.read().getMessage())) {
			return true;
		}
		Long timeWritten = reader.read().getLastWritten();
		Long timeNow = System.currentTimeMillis() / 1000L;
		return (timeNow - timeWritten) > Constants.MESSAGE_TIMEOUT;
	}
	
	private void changeMessage(String newMessage) throws IOException, InterruptedException {
		ArduinoLoader.reprogram(new MessageClass(FormatString.formatMessage(newMessage), reader.read().getMode()));
	}
	
	public void changeMode(int mode) throws IOException, InterruptedException {
		ArduinoLoader.reprogram(new MessageClass(reader.read().getMessage(), mode));
	}
}
