package com.component.others.collapse.expandable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class ExpansionPanel extends JPanel {
	// OvershadowingSubclassFields:
	// JComponent: private final EventListenerList listenerList = new EventListenerList();
	private ExpansionEvent expansionEvent;
	private boolean openFlag;
	private final JScrollPane scroll = new JScrollPane();
	private final Component title;
	private final Component panel;

	/**
	 * @param title 折叠面板的标题组件
	 * @param panel 折叠面板的内容组件，内容组件会放入 JScrollPane 中，因此panel不必再创建 JScrollPane
	 */
	protected ExpansionPanel(Component title, Component panel) {
		super(new BorderLayout());
		this.title = title;
		this.panel = panel;
		init();
	}

	private void init() {
		scroll.setViewportView(panel);
		scroll.getVerticalScrollBar().setUnitIncrement(25);
		title.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setExpanded(!isExpanded());
				fireExpansionEvent();
			}
		});
		add(title, BorderLayout.NORTH);
	}

	public boolean isExpanded() {
		return openFlag;
	}

	public void setExpanded(boolean flg) {
		openFlag = flg;
		if (openFlag) {
			add(scroll);
		} else {
			remove(scroll);
		}
	}

	public void addExpansionListener(ExpansionListener l) {
		listenerList.add(ExpansionListener.class, l);
	}

	// public void removeExpansionListener(ExpansionListener l) {
	//   listenerList.remove(ExpansionListener.class, l);
	// }

	// Notify all listeners that have registered interest in
	// notification on this event type.The event instance
	// is lazily created using the parameters passed into
	// the fire method.
	@SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
	protected void fireExpansionEvent() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ExpansionListener.class) {
				// Lazily create the event:
				if (Objects.isNull(expansionEvent)) {
					expansionEvent = new ExpansionEvent(this);
				}
				((ExpansionListener) listeners[i + 1]).expansionStateChanged(expansionEvent);
			}
		}
	}
}
