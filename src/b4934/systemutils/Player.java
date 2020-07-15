package b4934.systemutils;

import b4934.systemutils.core.GameObject;

public class Player extends GameObject {

    private boolean movingX;
    private boolean movingY;
    private int movmentSpeed = 10;

    public int getMovmentSpeed() {
        return movmentSpeed;
    }

    public Player(int w, int h, String name, int imagesNumber) {
        super(w, h, "player/" + name, imagesNumber);
        movePlayerX();
    }

    /*@Override
    public void moveFrames() {
        super.moveFrames();
        setLocation(getLocation().x + movmentSpeed, getLocation().y);
    }*/
    public void movePlayerX() {
        new Thread(() -> {
            while (true) {
                System.out.println();
                if (isMoving()) {
                    setLocation(getLocation().x + movmentSpeed, getLocation().y);
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }).start();
    }

    public void movePlayerY() {
        new Thread(() -> {
            while (true) {
                if (!movingY) {
                    setLocation(getLocation().x, getLocation().y + movmentSpeed);
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }).start();
    }

    public void setMovmentSpeed(int movmentSpeed) {
        this.movmentSpeed = movmentSpeed;
    }

}
