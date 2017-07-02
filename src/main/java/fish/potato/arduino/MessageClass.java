package fish.potato.arduino;

import fish.potato.arduino.util.FormatString;

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
	
	@Override
	public String toString() {
		return "#include \"" + Constants.CHAR_FILE + ".h\"\n\n" +
				"//[" + this.lastWritten + "]\n\n" +
				"static int mode = " + this.mode + ";\n" +
				FormatString.formatMessage(message);
	}
}
