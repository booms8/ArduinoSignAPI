package fish.potato.arduino;

import fish.potato.arduino.util.MessageClass;

import java.io.File;
import java.io.IOException;
import fish.potato.arduino.util.ClassWriter;

public class ArduinoLoader {
	private static Runtime shell = Runtime.getRuntime();
	private static ClassWriter writer = new ClassWriter();
	
	public static void reprogram(MessageClass message) throws IOException, InterruptedException {
		System.out.println("Running in " + System.getProperty("user.dir"));
		loadNewMessage(message);
		build();
		load();
	}
	
	public static void loadNewMessage(MessageClass message) throws IOException {
		System.out.println("Loading message: " + message.getMessage());
		writer.createClass(message);
	}
	
	public static void build() throws IOException, InterruptedException {
		System.out.print("Building main class... ");
		Process build_main = shell.exec(Commands.BUILD_MAIN, null, new File(Constants.WORKING_DIR));
		build_main.waitFor();
		System.out.println("Done.");
		
		System.out.print("Building message class... ");
		Process build_message = shell.exec(Commands.BUILD_MESSAGE, null, new File(Constants.WORKING_DIR));
		build_message.waitFor();
		System.out.println("Done.");
		
		System.out.print("Linking... ");
		Process link_all = shell.exec(Commands.LINK_ALL, null, new File(Constants.WORKING_DIR));
		link_all.waitFor();
		System.out.println("Done.");
	}
	
	public static void load() throws IOException, InterruptedException {
		System.out.print("Generating EEPROM... ");
		Process gen_eeprom = shell.exec(Commands.GEN_EEPROM, null, new File(Constants.WORKING_DIR));
		gen_eeprom.waitFor();
		System.out.println("Done.");
		
		System.out.print("Generating hexfile... ");
		Process gen_hex = shell.exec(Commands.GEN_HEX, null, new File(Constants.WORKING_DIR));
		gen_hex.waitFor();
		System.out.println("Done.");
		
		System.out.print("Loading... ");
		Process load = shell.exec(Commands.LOAD, null, new File(Constants.WORKING_DIR));
		load.waitFor();
		System.out.println("Done.");
	}
}
