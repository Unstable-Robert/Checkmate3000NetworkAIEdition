package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The class from which all menus extend from.
 * Includes common elements like closure behavior, background, etc.
 */
public abstract class SettingsView extends JPanel {
	private static final long serialVersionUID = 1L;
	Image backgroundImage;
	protected Checkmate myCheckmate;
	/**
	 * Constructor stores main JFrame so the View can be easily changed.
	 */
	public SettingsView(Checkmate c) {
		super();
		myCheckmate = c;
		setLayout(null);
		this.setDoubleBuffered(true);
		try {
			this.backgroundImage = ImageIO.read(new File("res/Chess-king.JPG"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}
}
