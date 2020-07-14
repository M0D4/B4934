/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b4934.systemutils.core;

import b4934.systemutils.Player;

/**
 *
 * @author Moustafa Mohamed
 */
public class Level1 extends Level {

    Obstacle ob1;

    public Level1(String levelName, int levelWidth, Player player) {
        super(levelName, levelWidth, player);
        ob1 = new Obstacle(119, 492, "obstacle");
        ob1.setLocation(500, 0);
        add(ob1);
        

    }

}
