package display.form;

import com.component.form.upload.FileUploadItem;
import com.component.form.upload.FileUploadPanel;
import com.component.form.upload.ImageUploadButton;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UploadDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("wrap 1,center"));


		JList<File> list = new JList<>(new File[]{new File("file1.png"), new File("file2.png"), new File("file3.png")});
		list.setCellRenderer(new FileUploadItem());
		p.add(list);

		p.add(new FileUploadPanel("上传文件", "文件大小不要超过20M"));

		p.add(new ImageUploadButton());

		SwingTestUtil.test();
	}
}
