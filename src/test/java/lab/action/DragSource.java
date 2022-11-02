package lab.action;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

/**
 * 拖拽按钮生成文件
 */
public class DragSource extends JPanel {
	private final JLabel label = new JLabel();
	private final transient Icon i1 = makeIcon("i03-04.gif");
	private final transient Icon i2 = makeIcon("i03-10.gif");
	private File file;

	private DragSource() {
		super(new BorderLayout());
		label.setVerticalTextPosition(SwingConstants.BOTTOM);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(BorderFactory.createTitledBorder("拖拽这个Label"));
		clearFile();

		label.setTransferHandler(new TransferHandler() {
			@Override
			public int getSourceActions(JComponent c) {
				return TransferHandler.COPY_OR_MOVE;
			}

			@Override
			protected Transferable createTransferable(JComponent c) {
				File tmpFile = getFile();
				if (Objects.nonNull(tmpFile)) {
					return new TempFileTransferable(tmpFile);
				} else {
					return null;
				}
			}

			@Override
			protected void exportDone(JComponent c, Transferable data, int action) {
				cleanup(c, action == TransferHandler.MOVE);
			}

			private void cleanup(JComponent c, boolean isMoved) {
				if (isMoved) {
					clearFile();
					c.repaint();
				}
			}
		});
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JComponent c = (JComponent) e.getComponent();
				c.getTransferHandler().exportAsDrag(c, e, TransferHandler.COPY);
			}
		});

		JButton button = new JButton("创建临时文件");
		button.addActionListener(e -> {
			File outfile;
			try {
				outfile = File.createTempFile("test", ".tmp");
				outfile.deleteOnExit();
			} catch (IOException ex) {
				JComponent c = (JComponent) e.getSource();
				UIManager.getLookAndFeel().provideErrorFeedback(c);
				String msg = "Could not create file.";
				JOptionPane.showMessageDialog(c.getRootPane(), msg, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			setFile(outfile);
		});

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(e -> {
			clearFile();
			label.repaint();
		});

		Box box = Box.createHorizontalBox();
		box.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		box.add(Box.createHorizontalGlue());
		box.add(button);
		box.add(Box.createHorizontalStrut(2));
		box.add(clearButton);
		add(label);
		add(box, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(320, 240));
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
		label.setIcon(i2);
		label.setText("tmpFile#exists(): true(draggable)");
	}

	public void clearFile() {
		file = null;
		label.setIcon(i1);
		label.setText("tmpFile#exists(): false");
	}

	private static Icon makeIcon(String path) {
		try {
			return new ImageIcon(ImageIO.read(DragSource.class.getResourceAsStream(path)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static Icon makeMissingIcon() {
		return UIManager.getIcon("OptionPane.errorIcon");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("fill"));
			p.add(new DragSource(), "center");
			SwingTestUtil.test();
		});
	}

}

class TempFileTransferable implements Transferable {
	private final File file;

	protected TempFileTransferable(File file) {
		this.file = file;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) {
		return Collections.singletonList(file);
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[]{DataFlavor.javaFileListFlavor};
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(DataFlavor.javaFileListFlavor);
	}
}
