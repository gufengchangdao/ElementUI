package lab.other.xml;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

/**
 * 读取xml配置文件加载组件样式
 */
public class SynthLookAndFeelTest extends JPanel {
	private SynthLookAndFeelTest() {
		super();

		add(new JButton("JButton1"));
		add(new JButton("JButton2"));
		add(new MyButton("MyButton"));

		JButton button3 = new JButton("JButton3");
		button3.setName("green:3");
		add(button3);

		JButton button4 = new JButton("JButton4");
		button4.setName("green:4");
		add(button4);

		setPreferredSize(new Dimension(320, 240));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(SynthLookAndFeelTest::createAndShowGui);
	}

	private static void createAndShowGui() {
		Class<?> clz = SynthLookAndFeelTest.class;
		try (InputStream is = clz.getResourceAsStream("button.xml")) {
			javax.swing.plaf.synth.SynthLookAndFeel synth = new javax.swing.plaf.synth.SynthLookAndFeel();
			synth.load(is, clz);
			UIManager.setLookAndFeel(synth);
		} catch (IOException | ParseException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
			Toolkit.getDefaultToolkit().beep();
		}
		JFrame frame = new JFrame("@title@");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(new SynthLookAndFeelTest());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

class MyButton extends JButton {
	protected MyButton(String title) {
		super(title + ": class");
	}
}
