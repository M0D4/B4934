package b4934.testing;

import b4934.systemutils.Player;
import b4934.systemutils.core.GameObject;
import b4934.systemutils.core.Level;
import b4934.systemutils.core.Level1;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TestFrame extends JFrame {

    JButton play;
    JButton stop;
    Player player;
    Level1 level1;

    public TestFrame() {
        setSize(800, 600);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        play = new JButton("Play");
        play.setLocation(200, 450);
        play.setSize(100, 100);
        play.setPreferredSize(new Dimension(100, 100));
        play.addActionListener((al) -> {
            player.setIsMoving(true);
        });
        add(play);

        stop = new JButton("Stop");
        stop.setLocation(400, 450);
        stop.setSize(60, 60);
        stop.setPreferredSize(new Dimension(100, 100));
        stop.addActionListener((al) -> {
            player.setIsMoving(false);
        });
        add(stop);

        player = new Player(70, 70, "", 10);

        level1 = new Level1("level1", 1311, player);
        player.setLocation(0, 0);
        add(level1);
        repaint();
        level1.repaint();

    }

}
