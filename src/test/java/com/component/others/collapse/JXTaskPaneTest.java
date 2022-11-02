package com.component.others.collapse;

import com.component.util.SwingTestUtil;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.*;
import java.awt.*;

/**
 * swingx的折叠面板，测试
 */
public class JXTaskPaneTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			//一个将所有JXTaskPane放在一起的容器
			JXTaskPaneContainer taskPaneContainer = new JXTaskPaneContainer();

			//创建一个包含常用操作的第一个taskPane
			JXTaskPane actionPane = new JXTaskPane();
			actionPane.setTitle("Files and Folders");
			// actionPane.setSpecial(true);
			//可以添加动作，将创建一个超链接 动作
			actionPane.add(new JLabel("测试一"));
			actionPane.add(new JLabel("测试二"));
			//添加taskPane到taskPaneContainer
			taskPaneContainer.add(actionPane);

			//创建另一个taskPane，它将显示所选文件的详细信息
			JXTaskPane details = new JXTaskPane();
			details.setTitle("Details");
			//添加标准组件到细节taskPane
			JLabel searchLabel = new JLabel("Search:");
			JTextField searchField = new JTextField("");
			details.add(searchLabel);
			details.add(searchField);
			taskPaneContainer.add(details);

			SwingTestUtil.test(taskPaneContainer);
		});
	}
}
