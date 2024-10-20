package Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.Icon;

public class OvalButtonIcon implements Icon, Serializable {
	Color color;
	
	OvalButtonIcon(Color c) {
		color = c;
	}
	
	public Color getColor() {
		return color;
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(Color.white);
		g.fillOval(x-10, y-10, 40, 40);
		g.setColor(color);
		g.fillOval(x, y, getIconWidth(), getIconHeight());
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
