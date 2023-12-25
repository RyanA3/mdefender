package com.felnstaren.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window {

	private JFrame frame;
	private BufferedImage image;
	private Canvas canvas;
	private BufferStrategy bs;
	private Graphics graphics;
	
	public Window(AppContainer ac) {
		Dimension dim = new Dimension((int) (ac.getWidth() * ac.getScale()), (int) (ac.getHeight() * ac.getScale()));
		
		image = new BufferedImage(ac.getWidth(), ac.getHeight(), BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		canvas.setPreferredSize(dim);
		canvas.setMaximumSize(dim);
		canvas.setMinimumSize(dim);
		
		frame = new JFrame(ac.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		graphics = bs.getDrawGraphics();
	}
	
	
	public void update() {
		graphics.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bs.show();
	}
	
	
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	
	public JFrame getFrame() {
		return this.frame;
	}
}