package com.samkrug.spirograph;
import java.util.*;
import javax.swing.*;
import java.awt.*;
public class JSpirograph extends JPanel {
	private static final long serialVersionUID = 1L;
	public boolean shouldBreak = false;
	public int iteration = 0;
	private ArrayList<Dimension> array = new ArrayList<Dimension>();
	private double x;
	private double y;
	private double theta = 0;
	private double period = 540;
	private double inrad = -0.23;
	private double dist = 0.1;
	private double theta2 = theta/inrad;
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("spirograph v1.1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		JSpirograph spirograph = new JSpirograph();
		frame.getContentPane().add(BorderLayout.CENTER, spirograph);
		frame.setVisible(true);
		long start = System.currentTimeMillis();
		boolean hasPrinted = false;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Thread.sleep(1);
			frame.repaint();
			frame.revalidate();
			spirograph.iteration = i;
			if (i > 40000) {
				spirograph.shouldBreak = true;
				if (!hasPrinted) {
					System.out.println("Took " + (System.currentTimeMillis()-start)/(long)1000 + " seconds to complete!");
					hasPrinted = true;
				}
			}
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		if (!shouldBreak) {
			g.setColor(Color.RED);
			x = (200*Math.cos(theta)+400)+inrad*200*Math.cos(theta)+200*(inrad-dist)*Math.cos(theta2);
			y = (200*Math.sin(theta)+400)+inrad*200*Math.sin(theta)+200*(inrad-dist)*Math.sin(theta2);
			array.add(new Dimension((int)x, (int)y));
			g.fillRect((int)x, (int)y, 3, 3);
			for (int i = 0; i < array.size(); i++) {
				Dimension tmp = array.get(i);
				g.fillRect((int)tmp.getWidth(), (int)tmp.getHeight(), 3, 3);
			}
			theta += ((2*Math.PI)/period)/3;
			theta2 = -(theta/inrad);
			System.out.println("x: " + x + ", y: " + y + ", iteration: " + iteration);
		} else {
			g.setColor(Color.GREEN);
			for (int i = 0; i < array.size(); i++) {
				g.fillRect((int)array.get(i).getWidth(), (int)array.get(i).getHeight(), 3, 3);
			}
		}
	}
}