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
    Player player;

    public Level(String levelName, int levelWidth, Player player) {
        this.player = player;
        setPreferredSize(new Dimension(levelWidth, Constants.LEVEL_HEIGHT));
        setSize(new Dimension(levelWidth, Constants.LEVEL_HEIGHT));
        setLocation(0, 0);
        try {
            img = ImageIO.read(new File("assets/levels/" + levelName + ".png"));
            background = new JLabel(new ImageIcon(img));
            background.setSize(1311, 800);
            background.setPreferredSize(new Dimension(1311, 800));
        } catch (IOException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        add(player);
        handleCollision();
        moveLevel();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }

    private void handleCollision() {
        new Thread(() -> {
            outer:
            while (true) {
                Component[] components = getComponents();
                for (int i = 0; i < components.length; i++) {
                    if (components[i] instanceof GameObject) {
                        GameObject first = (GameObject) components[i];
                        Rectangle firstRectangle = first.getObjectRectangle();
                        Rectangle secondRectangle = player.getObjectRectangle();
                        if (firstRectangle.intersects(secondRectangle) && !(first instanceof Player)) {
                            player.setIsMoving(false);
                            if (player instanceof Player) {
                                killPlayer(player);
                            }
                            JOptionPane.showMessageDialog(null, "gameOver");
                            break outer;
                        }
                    }
                }
            }
        }).start();
    }

    private void killPlayer(Player second) {
        second.rotate(90);
        second.movePlayerY();
    }

    private void moveLevel() {
        new Thread(() -> {
            while (true) {
                System.out.println();
                if (player.isMoving()) {
                    setLocation(getLocation().x - player.getMovmentSpeed(),getLocation().y);
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }).start();
    }
}
