package lab.component.editorpane;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.*;
import javax.swing.text.html.BlockView;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

/**
 * JEditorPane 监听链接点击事件
 */
public class ElementAttribute extends JPanel {
	private String tooltip;

	private ElementAttribute() {
		super(new BorderLayout());
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		String htmlText = String.join("\n",
				"<html><body>",
				"span tag: <span style='background:#88ff88;' title='tooltip: span[@title]'>span</span>",
				"<br />",
				"<div title='tooltip: div[@title]'>div tag: div div div div</div>",
				"<div style='padding: 2 24;'><img src='",
				getClass().getResource("favicon.png").toString(),
				"' alt='16x16 favicon' />&nbsp;",
				"<a href='https://ateraimemo.com/' title='Title: JST'>Java Swing Tips</a></div>",
				"</body></html>"
		);

		JEditorPane editor1 = new CustomTooltipEditorPane();
		editor1.setEditorKit(new HTMLEditorKit());
		editor1.setText(htmlText);
		editor1.setEditable(false);
		ToolTipManager.sharedInstance().registerComponent(editor1);

		JEditorPane editor2 = new JEditorPane();
		editor2.setEditorKit(new TooltipEditorKit());
		editor2.setText(htmlText);
		editor2.setEditable(false);
		editor2.addHyperlinkListener(e -> {
			JEditorPane editorPane = (JEditorPane) e.getSource();
			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				String message = "You click the link with the URL " + e.getURL();
				JOptionPane.showMessageDialog(editorPane, message);
			} else if (e.getEventType() == HyperlinkEvent.EventType.ENTERED) {
				tooltip = editorPane.getToolTipText();
				String text = Optional.ofNullable(e.getURL()).map(URL::toExternalForm).orElse(null);
				editorPane.setToolTipText(text);
				// URL url = e.getURL();
				// editorPane.setToolTipText(Objects.nonNull(url) ? url.toExternalForm() : null);
			} else if (e.getEventType() == HyperlinkEvent.EventType.EXITED) {
				editorPane.setToolTipText(tooltip);
			}
		});
		ToolTipManager.sharedInstance().registerComponent(editor2);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sp.setResizeWeight(.5);
		sp.setTopComponent(new JScrollPane(editor1));
		sp.setBottomComponent(new JScrollPane(editor2));
		add(sp);
		setPreferredSize(new Dimension(320, 240));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(ElementAttribute::createAndShowGui);
	}

	private static void createAndShowGui() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
		         UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
			Toolkit.getDefaultToolkit().beep();
		}
		JFrame frame = new JFrame("@title@");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(new ElementAttribute());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

class CustomTooltipEditorPane extends JEditorPane {
	private final transient Position.Bias[] bias = new Position.Bias[1];
	private transient HyperlinkListener listener;
	// private boolean doesElementContainLocation(
	//     JEditorPane editor, Element e, int offset, int x, int y) {
	//   if (e != null && offset > 0 && e.getStartOffset() == offset) {
	//     try {
	//       TextUI ui = editor.getUI();
	//       Shape s1 = ui.modelToView(editor, offset, Position.Bias.Forward);
	//       if (s1 == null) {
	//         return false;
	//       }
	//       Rectangle r1 = s1 instanceof Rectangle ? (Rectangle) s1 : s1.getBounds();
	//       Shape s2 = ui.modelToView(editor, e.getEndOffset(), Position.Bias.Backward);
	//       if (s2 != null) {
	//         Rectangle r2 = s2 instanceof Rectangle ? (Rectangle) s2 : s2.getBounds(); r1.add(r2);
	//       }
	//       return r1.contains(x, y);
	//     } catch (BadLocationException ex) {
	//       throw new RuntimeException(ex); // should never happen
	//     }
	//   }
	//   return true;
	// }

	@Override
	public void updateUI() {
		removeHyperlinkListener(listener);
		super.updateUI();
		listener = new HyperlinkListener() {
			private String tooltip;

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				JEditorPane editor = (JEditorPane) e.getSource();
				if (Objects.equals(e.getEventType(), HyperlinkEvent.EventType.ACTIVATED)) {
					JOptionPane.showMessageDialog(editor, e.getURL());
				} else if (Objects.equals(e.getEventType(), HyperlinkEvent.EventType.ENTERED)) {
					tooltip = editor.getToolTipText();
					Object o = Optional.ofNullable(e.getSourceElement())
							.map(elem -> (AttributeSet) elem.getAttributes().getAttribute(HTML.Tag.A))
							.map(attr -> attr.getAttribute(HTML.Attribute.TITLE))
							.orElse(null);
					editor.setToolTipText((String) o);
					// Element elem = e.getSourceElement();
					// if (Objects.nonNull(elem)) {
					//   AttributeSet attr = elem.getAttributes();
					//   AttributeSet a = (AttributeSet) attr.getAttribute(HTML.Tag.A);
					//   if (Objects.nonNull(a)) {
					//     editor.setToolTipText((String) a.getAttribute(HTML.Attribute.TITLE));
					//   }
					// }
				} else if (Objects.equals(e.getEventType(), HyperlinkEvent.EventType.EXITED)) {
					editor.setToolTipText(tooltip);
				}
			}
		};
		addHyperlinkListener(listener);
	}

	@Override
	public String getToolTipText(MouseEvent e) {
		String title = super.getToolTipText(e);
		JEditorPane editor = (JEditorPane) e.getComponent();
		if (!editor.isEditable()) {
			int pos = editor.getUI().viewToModel(editor, e.getPoint(), bias);
			// Java 9: int pos = editor.getUI().viewToModel2D(editor, e.getPoint(), bias);
			if (bias[0] == Position.Bias.Backward && pos > 0) {
				pos--;
			}
			Document doc = editor.getDocument();
			if (pos >= 0 && doc instanceof HTMLDocument) {
				return getSpanTitleAttribute((HTMLDocument) doc, pos).orElse(title);
			}
		}
		return title;
	}

	private Optional<String> getSpanTitleAttribute(HTMLDocument doc, int pos) {
		// HTMLDocument doc = (HTMLDocument) editor.getDocument();
		Element elem = doc.getCharacterElement(pos);
		// if (!doesElementContainLocation(editor, elem, pos, e.getX(), e.getY())) {
		//   elem = null;
		// }
		// if (elem != null) {
		AttributeSet a = elem.getAttributes();
		AttributeSet span = (AttributeSet) a.getAttribute(HTML.Tag.SPAN);
		return Optional.ofNullable(span)
				.map(s -> Objects.toString(s.getAttribute(HTML.Attribute.TITLE)));
	}
}

class TooltipEditorKit extends HTMLEditorKit {
	@Override
	public ViewFactory getViewFactory() {
		return new HTMLFactory() {
			@Override
			public View create(Element elem) {
				AttributeSet attrs = elem.getAttributes();
				Object name = attrs.getAttribute(AbstractDocument.ElementNameAttribute);
				Object o = name == null ? attrs.getAttribute(StyleConstants.NameAttribute) : null;
				if (o instanceof HTML.Tag) {
					HTML.Tag kind = (HTML.Tag) o;
					if (kind == HTML.Tag.DIV) {
						return new BlockView(elem, View.Y_AXIS) {
							@Override
							public String getToolTipText(float x, float y, Shape allocation) {
								Object o = getElement().getAttributes().getAttribute(HTML.Attribute.TITLE);
								return o == null ? super.getToolTipText(x, y, allocation) : Objects.toString(o);
							}
						};
					}
				}
				return super.create(elem);
			}
		};
	}
}
