package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 * Splash screen.
 */
public class SplashView extends SettingsView {
	private static final long serialVersionUID = 1L;
	private Timer t;
	private Image splashImage;
	public SplashView(Checkmate c) {
		super(c);
		try {
			this.splashImage = ImageIO.read(new File("res/splash.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		t = new Timer(3000, this);
		t.start();
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	    g.drawImage(splashImage, 0, 0, this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		t.stop();
		myCheckmate.setView(Checkmate.MAIN_MENU);
	}
}
