import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.plaf.DimensionUIResource;
public class TutorialPanel extends JPanel implements Runnable {
    int ogTileSize = 16;
    int scale = 3;
    int tileSize = ogTileSize*scale;
    int col = 22;
    int row = 10;
    int screenWidth = tileSize * col;
    int screenHeight = tileSize * row;
    KeyHandler kh = new KeyHandler();
    Thread gameThread;
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    int FPS = 60;
    Player player;
    Enemy enemy1;
    ArrayList<Bullet> bull = new ArrayList<Bullet>();
    ArrayList<Enemy> enems = new ArrayList<Enemy>();
    int last = 0;
    // constructor
    public TutorialPanel() {
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
        player = new Player(this, kh, 100, 200);
        enemy1 = new Enemy(this, player, 400, 200, 0, 100000);
        player.enems = enems;
        enems.add(enemy1);
        this.addMouseListener(player);
        this.addMouseMotionListener(player);
        startGameThread();
    }
    // starting game thread to run fps
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    // fps manager and updater
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta+= (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if (delta>=1) {
                update();
                repaint();
                delta--;

            }
        }
    }
    // update
    public void update() {
        player.update();
        for (int i = 0; i<bull.size(); i++) {
            if (!bull.get(i).hit) {bull.get(i).update();}
        }
        for (Enemy enemy : enems) {
            if (enemy.health!=0) enemy.update();
            enemy.health = enemy.maxHealth;
        }
        
    }
    // drawing player
    @Override
    public void paintComponent(Graphics g2) {
        // TODO Auto-generated method stub
        super.paintComponent(g2);

        Graphics2D g = (Graphics2D) g2;

        player.draw(g);
        for (Enemy enemy : enems) {
            if (enemy.health!=0) enemy.draw(g);
        }
        for (int i = 0; i<bull.size(); i++) {
            if (!bull.get(i).played) {bull.get(i).draw(g);}
        }
        g2.dispose();
    }
}
// class for inputs
class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;
    // needed method
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    // check when key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        else if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        else if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
    }
    // check when key is released
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stubs
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        else if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        else if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
}
