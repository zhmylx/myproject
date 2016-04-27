package ins.framework;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.commons.io.IOUtils;

class VersionFrame extends JFrame {
	private JTextArea textArea;
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VersionFrame frame = new VersionFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VersionFrame() {
		getContentPane().setLayout(new BorderLayout());
		setTitle("关于" + Constants.APP_TITLE);
		setBounds(100, 100, 681, 368);
		setDefaultCloseOperation(2);
		String history;
		try {
			history = IOUtils
					.toString(ClassLoader
							.getSystemResourceAsStream("history.txt"), "GBK");
		} catch (Exception e1) {
			history = "Can't find history.txt";
		}
		JLabel label = new JLabel();
		label.setText("更新历史：");
		getContentPane().add(label, "North");
		JPanel panel = new JPanel();
		getContentPane().add(panel, "South");
		JButton okButton = new JButton();
		okButton.setSelected(true);
		okButton.setMnemonic(79);
		panel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VersionFrame.this.dispose();
			}
		});
		okButton.setText("确定(O)");
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		this.textArea = new JTextArea();
		scrollPane.setViewportView(this.textArea);
		this.textArea.setEditable(false);
		this.textArea.setText(history);
		this.textArea.setLineWrap(true);
		this.textArea.select(0, 0);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.VersionFrame JD-Core Version: 0.5.4
 */