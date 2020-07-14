package b4934.systemutils.core;

import b4934.systemutils.Player;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Level extends JPanel {

    BufferedImage img;
    JLabel background;

    public Level(String levelName, int levelWidth) {
        setPreferredSize(new Dimension(levelWidth, Constants.LEVEL_HEIGHT));
        setSize(new Dimension(levelWidth, Constants.LEVEL_HEIGHT));
        setLocation(0, 0);
        try {
            img = ImageIO.read(new File("assets/levels/" + levelName + ".png"));
            background = new JLabel(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        handleCollision();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }

    private void handleCollision() {
        new Thread(() -> {
            outer:while (true) {
                Component[] components = getComponents();
                for (int i = 0; i < components.length; i++) {
                    for (int j = 0; j < components.length; j++) {
                        if (components[i] instanceof GameObject && components[j] instanceof GameObject&&i!=j) {
                            GameObject first = (GameObject) components[i];
                            GameObject second = (GameObject) components[j];
                            Rectangle firstRectangle = first.getObjectRectangle();
                            Rectangle secondRectangle = second.getObjectRectangle();
                            if (firstRectangle.intersects(secondRectangle)) {
                                second.setIsMoving(false);
                                JOptionPane.showMessageDialog(null, "gameOver");
                                break outer;
                            }
                        }
                    }
                }
            }
        }).start();
    }
}
