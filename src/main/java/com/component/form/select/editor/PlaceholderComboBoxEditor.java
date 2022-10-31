package com.component.form.select.editor;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.util.Optional;

/**
 * 为 JComboBox 的输入框添加占位符
 */
public class PlaceholderComboBoxEditor extends BasicComboBoxEditor {
	private Component editorComponent;
	private String placeholder;

	public PlaceholderComboBoxEditor(String placeholder) {
		this.placeholder = placeholder;
	}

	@Override
	public Component getEditorComponent() {
		editorComponent = Optional.ofNullable(editorComponent)
				.orElseGet(() -> {
					JTextComponent tc = (JTextComponent) super.getEditorComponent();
					return new JLayer<>(tc, new PlaceholderLayerUI<>(placeholder));
				});
		return editorComponent;
	}

	public String getPlaceholder() {
		return placeholder;
	}
}

class PlaceholderLayerUI<E extends JTextComponent> extends LayerUI<E> {
	private static final Color INACTIVE = UIManager.getColor("TextField.inactiveForeground");
	private final JLabel hint;

	protected PlaceholderLayerUI(String hintMessage) {
		super();
		this.hint = new JLabel(hintMessage);
		hint.setForeground(INACTIVE);
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);
		if (c instanceof JLayer) {
			JTextComponent tc = (JTextComponent) ((JLayer<?>) c).getView();
			if (tc.getText().length() == 0 && !tc.hasFocus()) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setPaint(INACTIVE);
				// System.out.println("getInsets: " + tc.getInsets());
				// System.out.println("getMargin: " + tc.getMargin());
				Insets i = tc.getMargin();
				Dimension d = hint.getPreferredSize();
				SwingUtilities.paintComponent(g2, hint, tc, i.left, i.top, d.width, d.height);
				g2.dispose();
			}
		}
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		if (c instanceof JLayer) {
			((JLayer<?>) c).setLayerEventMask(AWTEvent.FOCUS_EVENT_MASK);
		}
	}

	@Override
	public void uninstallUI(JComponent c) {
		super.uninstallUI(c);
		if (c instanceof JLayer) {
			((JLayer<?>) c).setLayerEventMask(0);
		}
	}

	@Override
	public void processFocusEvent(FocusEvent e, JLayer<? extends E> l) {
		l.getView().repaint();
	}
}
