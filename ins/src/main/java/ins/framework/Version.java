package ins.framework;

import ins.framework.utils.Swings;
import java.io.PrintStream;
import javax.swing.JFrame;

public class Version {
	public static final String versionNo = Constants.APP_VERSION;

	public static String getVersion() {
		return versionNo;
	}

	public static String getDescription() {
		return Constants.APP_DESC;
	}

	public static void main(String[] args) {
		try {
			JFrame frame = new VersionFrame();
			frame.setDefaultCloseOperation(3);
			Swings.center(frame);
			frame.setVisible(true);
		} catch (Exception e) {
			System.out.println(getDescription() + "(" + getVersion() + ")");
		}
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.Version JD-Core Version: 0.5.4
 */