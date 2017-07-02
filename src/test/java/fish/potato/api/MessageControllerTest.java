package fish.potato.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.io.IOException;

import fish.potato.arduino.MessageClass;
import fish.potato.arduino.util.ClassReader;

public class MessageControllerTest {
	private ClassReader mockReader = mock(ClassReader.class);
	private MessageController testController;
	private MessageClass messageOld;
	private MessageClass messageNew;
	
	@Before
	public void setup() {
		testController = new MessageController(mockReader);
		messageOld = new MessageClass("TEST", 1498595611L, 1);
		messageNew = new MessageClass("TEST", ((System.currentTimeMillis() / 1000L) - 30), 1);
	}
	
	@Test
	public void testCanWrite() throws IOException {
		when(mockReader.read()).thenReturn(messageOld);
		assertTrue("Write after a long time", testController.canWrite("testmessage"));
		
		when(mockReader.read()).thenReturn(messageNew);
		assertFalse("Write after 30s", testController.canWrite("testmessage"));
		assertTrue("Write same message after 30s", testController.canWrite("test"));
	}
}
