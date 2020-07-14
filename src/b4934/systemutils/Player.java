package b4934.systemutils;

import b4934.systemutils.core.GameObject;

public class Player extends GameObject {

    private int movmentSpeed = 10;

    public Player(int w, int h, String name, int imagesNumber) {
        super(w, h, "player/" + name, imagesNumber);

    }

    @Override
    public void moveFrames() {
        super.moveFrames();
        setLocation(getLocation().x + movmentSpeed, getLocation().y);
    }

    public void setMovmentSpeed(int movmentSpeed) {
        this.movmentSpeed = movmentSpeed;
    }

}
