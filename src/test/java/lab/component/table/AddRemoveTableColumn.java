package lab.component.table;// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// @homepage@

import com.component.util.SwingTestUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 右键菜单控制表格列的展示与隐藏
 */
public final class AddRemoveTableColumn extends JPanel {
	private AddRemoveTableColumn() {
		super(new BorderLayout());
		UIManager.put("CheckBoxMenuItem.doNotCloseOnMouseClick", true);

		JTable table = new JTable(new DefaultTableModel(12, 8));
		table.getTableHeader().setComponentPopupMenu(new TableHeaderPopupMenu(table));

		add(new JScrollPane(table));
		setPreferredSize(new Dimension(320, 240));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new FlowLayout());
			p.add(new AddRemoveTableColumn());
			SwingTestUtil.test();
		});
	}

}

class TableHeaderPopupMenu extends JPopupMenu {
	protected TableHeaderPopupMenu(JTable table) {
		super();
		TableColumnModel columnModel = table.getColumnModel();
		// 拷贝一份所有列数据
		List<TableColumn> list = Collections.list(columnModel.getColumns());
		list.forEach(tableColumn -> {
			String name = Objects.toString(tableColumn.getHeaderValue());
			JCheckBoxMenuItem item = new JCheckBoxMenuItem(name, true);
			// 添加事件
			item.addItemListener(e -> {
				if (((AbstractButton) e.getItemSelectable()).isSelected()) { //选中
					columnModel.addColumn(tableColumn);
				} else { //未选中
					columnModel.removeColumn(tableColumn);
				}
				updateMenuItems(columnModel);
			});
			add(item);
		});
	}

	@Override
	public void show(Component c, int x, int y) {
		if (c instanceof JTableHeader) { //如果是在表头组件上右键
			JTableHeader header = (JTableHeader) c;
			header.setDraggedColumn(null);
			header.repaint();
			header.getTable().repaint();
			updateMenuItems(header.getColumnModel());
			super.show(c, x, y);
		}
	}

	// 更新菜单项复选框的状态
	private void updateMenuItems(TableColumnModel columnModel) {
		boolean isOnlyOneMenu = columnModel.getColumnCount() == 1;
		if (isOnlyOneMenu) { //复选中只选了一个
			// 只有没被选中的按钮可以选，被选中的(最后一个选中的)不可以选择
			descendants(this)
					.map(MenuElement::getComponent)
					.forEach(mi ->
							mi.setEnabled(!(mi instanceof AbstractButton) || !((AbstractButton) mi).isSelected()));
		} else {
			descendants(this).forEach(me -> me.getComponent().setEnabled(true));
		}
	}

	// 将子组件数组转换为流
	private static Stream<MenuElement> descendants(MenuElement me) {
		return Stream.of(me.getSubElements())
				.flatMap(m -> Stream.concat(Stream.of(m), descendants(m)));
	}
}
