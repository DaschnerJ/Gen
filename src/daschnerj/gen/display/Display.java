package daschnerj.gen.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	// Game frame (window).
	private JFrame frame;

	// The image being displayed.
	private Canvas canvas;

	// Title of frame.
	private String title;

	// The width and height of the game window.
	private int width, height;

	// Constructor for the display
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		createDisplay();
	}

	// Creates the JFrame for the display with attributes.
	private void createDisplay() {
		// Basic attributes.
		frame = new JFrame(title);
		frame.setSize(width, height);
		// Ensures the game closes in background.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// We do not want the user to be able to resize, causes implications.
		frame.setResizable(false);
		// Set the frame to open in center of the screen.
		frame.setLocationRelativeTo(null);
		// To be able to see it.
		frame.setVisible(true);

		// Creates the canvas to draw on.
		canvas = new Canvas();

		// Sets the canvas dimensions.
		canvas.setPreferredSize(new Dimension(width, height));

		// Sets the minimum and maximum sizes to the dimensions as well to
		// ensure it is the correct size.
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));

		// To allow input to properly be inputed into the game. May work without
		// on some computers but not all.
		canvas.setFocusable(false);

		// Adds the canvas to the JFrame to be drawn on.
		frame.add(canvas);
		// Ensures the canvas is fitted to the JFrame.
		frame.pack();
	}

	/**
	 * Returns the canvas to draw to.
	 * 
	 * @return
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return frame;
	}

}
