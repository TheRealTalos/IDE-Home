package main;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import sun.awt.shell.ShellFolder;

public class IDE extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static final int BUTTONSIZE = 200;
	private static final int BUTTONCONSTANT = BUTTONSIZE + 16;

	private static List<Button> buttons = new ArrayList<Button>();
	private static List<File> files = new ArrayList<File>();

	public IDE() {
		File[] allFiles = new File("D:/Dev/IDE/").listFiles();
		
		for (int i = 0; i < allFiles.length; i++){
			if (allFiles[i].getName().endsWith(".lnk")){
				files.add(allFiles[i]);
				buttons.add(new Button(allFiles[i].getName().substring(0, allFiles[i].getName().length() - 4)));
			}
		}

		int widthNum = (int)Math.ceil(Math.sqrt(files.size()));
		
		int width = widthNum * BUTTONCONSTANT;
		int heightOffset = (files.size() <= Math.pow(widthNum, 2) - widthNum) ? BUTTONSIZE : 0;
		
		int height = width - heightOffset;
		
		setTitle("IDE Home");
		setLayout(new FlowLayout());
		setResizable(true);
		setSize(width, height);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for (int i = 0; i < buttons.size(); i++) {
			add(buttons.get(i).b);
		}

		for (int i = 0; i < files.size(); i++) {
			Icon icon;
			try {
				icon = new ImageIcon(ShellFolder.getShellFolder(files.get(i)).getIcon(true).getScaledInstance(BUTTONSIZE, BUTTONSIZE, Image.SCALE_SMOOTH));
				buttons.get(i).b.setIcon(icon);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).b.addActionListener(this);
		}

	}

	public static void main(String[] a) {
		IDE ide = new IDE();
		ide.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Runtime runTime = Runtime.getRuntime();

		try {
			for (int i = 0; i < buttons.size(); i++) {
				if (e.getSource() == buttons.get(i).b) {
					Desktop.getDesktop().open(files.get(i));
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
