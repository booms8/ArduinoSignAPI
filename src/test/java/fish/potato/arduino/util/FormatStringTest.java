package fish.potato.arduino.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FormatStringTest {
	
	@Test
	public void testFormatString() {
		assertEquals("Basic format", "{_T,_E,_S,_T,space}", FormatString.formatString("test"));
		assertEquals("Format with spaces", "{_T,_E,_S,_T,space,_T,_E,_S,_T,space}", FormatString.formatString("test test"));
		assertEquals("Format with plus signs", "{_T,_E,_S,_T,plus,_T,_E,_S,_T,space}", FormatString.formatString("test+test"));
		assertEquals("Format with equal signs", "{_T,_E,_S,_T,equal,_T,_E,_S,_T,space}", FormatString.formatString("test=test"));
		assertEquals("Format with undefined characters", "{_T,_E,_S,_T,space}", FormatString.formatString("test!?"));
	}
	
	@Test
	public void testFormatMessage() {
		assertEquals("Formatting integration test", "static byte pattern[5][8] = {_T,_E,_S,_T,space};", FormatString.formatMessage("test"));
	}
	
	@Test
	public void testUnformatMessage() {
		assertEquals("Basic unformat", "TEST", FormatString.unformatString("static byte pattern[5][8] = {_T,_E,_S,_T,space};"));
		assertEquals("Unformat with plus sign", "TEST+", FormatString.unformatString("static byte pattern[6][8] = {_T,_E,_S,_T,plus,space};"));
		assertEquals("Unformat with equal sign", "TEST=", FormatString.unformatString("static byte pattern[6][8] = {_T,_E,_S,_T,equal,space};"));
	}
}
