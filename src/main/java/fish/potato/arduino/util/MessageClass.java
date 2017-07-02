package fish.potato.arduino.util;

import fish.potato.arduino.Constants;

public class MessageClass {
	public String message;
	public Long lastWritten;
	public int mode;
	
	public MessageClass(String message, Long lastWritten, int mode) {
		this.message = message;
		this.lastWritten = lastWritten;
		this.mode = mode;
	}
	
	public MessageClass(String message, int mode) {
		this.message = message;
		this.lastWritten = System.currentTimeMillis() / 1000L;
		this.mode = mode;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getLastWritten() {
		return lastWritten;
	}
	public void setLastWritten(Long lastWritten) {
		this.lastWritten = lastWritten;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public String expand() {
		return "#include \"" + Constants.CHAR_FILE + ".h\"\n\n" +
				"//[" + this.getLastWritten() + "]\n\n" +
				"static int mode = " + this.getMode() + ";\n" +
				this.getMessage();
	}
}
