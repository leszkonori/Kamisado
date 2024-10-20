package Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.Icon;

//A négyzetes bábukhoz használt osztály
public class RectButtonIcon implements Icon, Serializable {
	Color color;
	
	RectButtonIcon(Color c) {
		color = c;
	}
	
	public Color getColor() {
		return color;
	}
	
	//A bábu megrajzolása
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(Color.white);
		g.clearRect(x-10, y-10, 40, 40);
		g.setColor(color);
		g.fillRect(x, y, getIconWidth(), getIconHeight());
	}

	@Override
	public int getIconWidth() {
		return 20;
	}

	@Override
	public int getIconHeight() {
		return 20;
	}
}
