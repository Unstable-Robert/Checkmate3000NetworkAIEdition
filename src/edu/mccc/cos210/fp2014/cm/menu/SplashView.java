package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Splash screen.
 */
public class SplashView extends SettingsView {
	private static final long serialVersionUID = 1L;
	private Image splashImage;
	public SplashView(Checkmate c) {
		super(c);
		try {
			this.splashImage = ImageIO.read(new File("res/splash.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(splashImage, 0, 0, this);
	}
}
