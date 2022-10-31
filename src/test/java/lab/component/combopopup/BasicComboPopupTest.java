package lab.component.combopopup;

import com.component.util.SwingTestUtil;
import com.component.util.TextEditorUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.Optional;

/**
 * 快捷键弹窗下拉列表，并将选择内容追加到输入的光标处
 */
public class BasicComboPopupTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("fill"));

			JTextPane textPane = new JTextPane();
			textPane.setText("Shift+Tab");

			JComboBox<Object> combo = new JComboBox<>(new String[]{
					"public", "protected", "private",
					"final", "transient", "super", "this", "return", "class"
			});

			BasicComboPopup popup = new BasicComboPopup(combo) {
				private transient MouseListener listener;

				@Override
				protected void installListListeners() {
					super.installListListeners();
					listener = new MouseAdapter() {
						// 点击就在光标处追加选择内容并隐藏列表
						@Override
						public void mouseClicked(MouseEvent e) {
							hide();
							TextEditorUtil.appendAtEnd(textPane, Objects.toString(comboBox.getSelectedItem()));
						}
					};
					if (Objects.nonNull(list)) {
						list.addMouseListener(listener);
					}
				}

				@Override
				public void uninstallingUI() {
					if (Objects.nonNull(listener)) {
						list.removeMouseListener(listener);
						listener = null;
					}
					super.uninstallingUI();
				}

				@Override
				public boolean isFocusable() {
					return true;
				}
			};

			// 注册列表上的快捷键
			ActionMap amc = popup.getActionMap();
			amc.put("myUp", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int i = combo.getSelectedIndex();
					combo.setSelectedIndex(i == 0 ? combo.getItemCount() - 1 : i - 1);
				}
			});
			amc.put("myDown", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int i = combo.getSelectedIndex();
					combo.setSelectedIndex(i == combo.getItemCount() - 1 ? 0 : i + 1);
				}
			});
			amc.put("myEnt", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int i = combo.getSelectedIndex();
					Optional.ofNullable(combo.getItemAt(i)).ifPresent(o -> {
						popup.hide();
						TextEditorUtil.appendAtEnd(textPane, Objects.toString(o, ""));
					});
				}
			});

			InputMap imc = popup.getInputMap();
			imc.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "myUp");
			imc.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "myDown");
			imc.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "myEnt");

			textPane.getActionMap().put("myPop", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						// 列表显示在鼠标光标的位置
						// Java 9:
						// Rectangle rect = textPane.modelToView2D(textPane.getCaretPosition()).getBounds();
						Rectangle rect = textPane.modelToView(textPane.getCaretPosition());
						popup.show(textPane, rect.x, (int) rect.getMaxY());
						// EventQueue.invokeLater(() -> {
						Container c = popup.getTopLevelAncestor();
						if (c instanceof Window) {
							((Window) c).toFront(); //将这个窗口移到所有其他窗口之上
						}
						popup.requestFocusInWindow(); //获取焦点
						// });
					} catch (BadLocationException ex) {
						RuntimeException wrap = new StringIndexOutOfBoundsException(ex.offsetRequested());
						wrap.initCause(ex);
						throw wrap;
					}
				}
			});
			// 组合快捷键
			KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK);
			// 设置快捷键对应的事件
			textPane.getInputMap().put(keyStroke, "myPop");
			p.add(textPane, "growx, growy");

			SwingTestUtil.test();
		});
	}
}
