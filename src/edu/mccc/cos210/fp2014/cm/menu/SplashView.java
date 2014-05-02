package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.*;

/**
 * Splash screen.
 */
public class SplashView extends SettingsView {
	private static final long serialVersionUID = 1L;
	private Timer t;
	public SplashView(Checkmate c) {
		super(c);
		
		JLabel titleLabel = new JLabel("Checkmate 3000 Network AI Edition");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 35));
		titleLabel.setSize(635, 50);
		titleLabel.setLocation(
			c.getWidth() / 2 - titleLabel.getWidth() / 2, 
			(int)(c.getHeight() * 0.35)
		);
		add(titleLabel);
		
		t = new Timer(3000, this);
		t.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		t.stop();
		myCheckmate.setView(Checkmate.MAIN_MENU);
	}
}
