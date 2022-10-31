import com.component.form.select.editor.PlaceholderComboBoxEditor;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			TrayIcon[] icons = SystemTray.getSystemTray().getTrayIcons();
			System.out.println(icons.length);

			SwingTestUtil.test();
		});
	}
}

