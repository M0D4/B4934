package b4934.systemutils.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
    
}
