package fish.potato.arduino.util;

public class FormatString {
	public static String formatMessage(String message) {
		String arrayDec = "static byte pattern[" + (message.length() + 1) + "][8] = ";
		
		return arrayDec + formatString(message) + ";";
	}
	
	static String formatString(String message) {
		String stringArray = "{";
		String[] messageArr = stripPunct(message).toUpperCase().split("(?!^)");
		
		for (int i = 0; i < messageArr.length; i++) {
			if (messageArr[i].equals(" ")) {
				stringArray += "space,";
			}
			else if (messageArr[i].equals("=")) {
				stringArray += "equal,";
			}
			else if (messageArr[i].equals("+")) {
				stringArray += "plus,";
			}
			else {
				stringArray += ("_" + messageArr[i] + ",");
			}
		}
		
		return stringArray + "space}";
	}
	
	static String unformatString(String content) {
		String rawMessage = content.substring(content.indexOf('{') + 1, content.indexOf('}'));
		String strippedMessage = rawMessage.replace("plus", "+").replace("equal", "=").replace("space", " ");
		String message = strippedMessage.replace("_", "").replace(",", "");
		return message.trim();
	}
	
	private static String stripPunct(String message) {
		return message.replaceAll("\\p{P}", "");
	}
}
