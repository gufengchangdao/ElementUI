package com.component.others.collapse.expandable;

import java.util.EventListener;

public interface ExpansionListener extends EventListener {
	void expansionStateChanged(ExpansionEvent e);
}
