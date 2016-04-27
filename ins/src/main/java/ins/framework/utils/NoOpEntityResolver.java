package ins.framework.utils;

import java.io.ByteArrayInputStream;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

class NoOpEntityResolver implements EntityResolver {
	public InputSource resolveEntity(String publicId, String systemId) {
		return new InputSource(new ByteArrayInputStream(new byte[0]));
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.utils.NoOpEntityResolver JD-Core Version: 0.5.4
 */