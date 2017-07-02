package fish.potato.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.io.IOException;

import fish.potato.arduino.util.ClassReader;

public class MessageControllerTest {
	private final String testClassOld = "#include \"chars_8.h\"\n\n" +
			"//[1498595611]\n\n" +
			"static int mode = 1;\n" +
			"static byte pattern[8][8] = {_T,_E,_S,_T,space};";
	
	private final String testClassRecent = "#include \"chars_8.h\"\n\n" +
			"//[" + ((System.currentTimeMillis() / 1000L) - 30) + "]\n\n" +
			"static int mode = 1;\n" +
			"static byte pattern[8][8] = {_T,_E,_S,_T,space};";
	
	private ClassReader mockReader = mock(ClassReader.class);
	private MessageController testController;
	
	@Before
	public void setup() {
		testController = new MessageController(mockReader);
	}
	
	@Test
	public void testCanWrite() throws IOException {
		when(mockReader.read()).thenReturn(testClassOld);
		assertTrue("Write after a long time", testController.canWrite("testmessage"));
		
		when(mockReader.read()).thenReturn(testClassRecent);
		assertFalse("Write after 30s", testController.canWrite("testmessage"));
		assertTrue("Write same message after 30s", testController.canWrite("test"));
	}
}
