/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b4934.systemutils.core;

/**
 *
 * @author Moustafa Mohamed
 */
public class Obstacle extends GameObject {

    public Obstacle(int w, int h, String name) {
        super(w, h, "obstacles/"+name, 1);
    }

    @Override
    public void startLooping() {
    }
}
