package b4934.systemutils.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameObject extends JPanel {
    
    private Image[] objectImages;
    protected int rotationAngle;
    private int currImg = 0;
    private boolean isMoving;
    
    public GameObject(int w, int h, String name, int imagesNumber) {
        setBackground(null);
        objectImages = new Image[imagesNumber];
        setup(w, h, name);
        rotationAngle = 0;
        setOpaque(false);
        isMoving = false;
        if (this instanceof GameObject) {
            startLooping();
        }
    }
    
    protected final void setup(int w, int h, String objectImageName) {
        setSize(w, h);
        setPreferredSize(new Dimension(w, h));
        try {
            for (int i = 0; i < objectImages.length; i++) {
                objectImages[i] = ImageIO.read(new File("assets/" + objectImageName + (i + 1) + ".png"));
            }
        } catch (IOException ex) {
        }
        setPreferredSize(new Dimension(w, h));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform backup = g2d.getTransform();
        g2d.rotate(Math.toRadians(rotationAngle), getWidth() / 2, getHeight() / 2);
        g2d.drawImage(objectImages[currImg], 0, 0, null);
        g2d.setTransform(backup);
        
    }
    
    public Rectangle getObjectRectangle() {
        return new Rectangle(getLocation().x, getLocation().y, getWidth(), getHeight());
    }
    
    public Image[] getObjectImages() {
        return objectImages;
    }
    
    public void setObjectImage(Image[] objectImages) {
        this.objectImages = objectImages;
    }
    
    public void startLooping() {
        new Thread(() -> {
            while (true) {
                System.out.println();
                if (isMoving) {
                    moveFrames();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameObject.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
    
    protected void moveFrames() {
        currImg = currImg + 1;
        currImg = currImg % objectImages.length;
        repaint();
        
    }
    
    public boolean isMoving() {
        return isMoving;
    }
    
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    
}
