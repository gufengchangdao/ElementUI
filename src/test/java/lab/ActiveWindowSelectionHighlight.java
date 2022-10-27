package lab;// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// @homepage@

import javax.swing.*;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public final class ActiveWindowSelectionHighlight extends JPanel {
  private ActiveWindowSelectionHighlight() {
    super(new BorderLayout());
    JSplitPane splitPane = new JSplitPane();
    splitPane.setResizeWeight(.5);
    splitPane.setLeftComponent(makeTabbedPane());
    splitPane.setRightComponent(makeTabbedPane());
    add(splitPane);
    setPreferredSize(new Dimension(320, 240));
  }

  private static Component makeTabbedPane() {
    JTabbedPane tabs = new JTabbedPane();
    tabs.addChangeListener(e -> requestFocusForVisibleComponent(tabs));
    tabs.addFocusListener(new FocusAdapter() {
      @Override public void focusGained(FocusEvent e) {
        requestFocusForVisibleComponent(tabs);
      }
    });
    tabs.add("Custom", makeTextArea(true));
    tabs.addTab("Default", makeTextArea(false));
    return tabs;
  }

  public static void requestFocusForVisibleComponent(JTabbedPane tabs) {
    // https://ateraimemo.com/Swing/RequestFocusForVisibleComponent.html
    String cmd = "requestFocusForVisibleComponent";
    ActionEvent a = new ActionEvent(tabs, ActionEvent.ACTION_PERFORMED, cmd);
    EventQueue.invokeLater(() -> tabs.getActionMap().get(cmd).actionPerformed(a));
  }

  private static Component makeTextArea(boolean flg) {
    JTextArea textArea = new JTextArea() {
      @Override public void updateUI() {
        setCaret(null);
        super.updateUI();
        if (flg) {
          Caret oldCaret = getCaret();
          int blinkRate = oldCaret.getBlinkRate();
          Caret caret = new FocusOwnerCaret();
          caret.setBlinkRate(blinkRate);
          setCaret(caret);
          caret.setSelectionVisible(true);
        }
      }
    };
    textArea.setText("FocusOwnerCaret: " + flg + "\n111\n22222\n33333333\n");
    textArea.selectAll();
    return new JScrollPane(textArea);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(ActiveWindowSelectionHighlight::createAndShowGui);
  }

  private static void createAndShowGui() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
      ex.printStackTrace();
      Toolkit.getDefaultToolkit().beep();
    }
    JFrame frame = new JFrame("@title@");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.getContentPane().add(new ActiveWindowSelectionHighlight());
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}

class FocusOwnerCaret extends DefaultCaret {
  private static final Color COLOR = Color.GRAY.brighter();
  private static final HighlightPainter NO_FOCUS_PAINTER = new DefaultHighlightPainter(COLOR);

  @Override public void focusLost(FocusEvent e) {
    super.focusLost(e);
    updateSelectionHighlightPainter();
  }

  @Override public void focusGained(FocusEvent e) {
    super.focusGained(e);
    updateSelectionHighlightPainter();
  }

  private void updateSelectionHighlightPainter() {
    setSelectionVisible(false); // removeHighlight
    setSelectionVisible(true); // addHighlight
  }

  @Override protected HighlightPainter getSelectionPainter() {
    JTextComponent c = getComponent();
    // Container w = c.getTopLevelAncestor();
    // boolean isActive = w instanceof Window && ((Window) w).isActive();
    Window w = SwingUtilities.getWindowAncestor(c);
    boolean isActive = w != null && w.isActive();
    return c.hasFocus() && isActive ? super.getSelectionPainter() : NO_FOCUS_PAINTER;
    // https://ateraimemo.com/Swing/CaretSelectionHighlight.html
    // return c.hasFocus() ? super.getSelectionPainter() : NO_FOCUS_PAINTER;
  }
}
