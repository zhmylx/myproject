package ins.framework.utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.text.JTextComponent;

public class Swings {
	public static Frame getFrame(Component c) {
		for (Container p = c.getParent(); p != null; p = p.getParent()) {
			if (p instanceof Frame) {
				return (Frame) p;
			}
		}
		return null;
	}

	public static Dialog getDialog(Component c) {
		for (Container p = c.getParent(); p != null; p = p.getParent()) {
			if (p instanceof Dialog) {
				return (Dialog) p;
			}
		}
		return null;
	}

	public static void center(Window frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		frame.setLocation(screenSize.width / 2 - frameSize.width / 2,
				screenSize.height / 2 - frameSize.height / 2);
	}

	public static boolean isEmpty(JTextComponent textComponent) {
		return (textComponent.getText() == null)
				|| (textComponent.getText().length() == 0);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.utils.Swings JD-Core Version: 0.5.4
 */