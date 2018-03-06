package main;

import java.awt.Dimension;

import javax.swing.JButton;

public class Button {

	public JButton b;
	public String name;

	private Dimension d = new Dimension(IDE.BUTTONSIZE, IDE.BUTTONSIZE);

	public Button(String name) {
		this.name = name;
		
		b = new JButton(name);
		b.setPreferredSize(d);
		b.setVisible(true);
		
	}

}
