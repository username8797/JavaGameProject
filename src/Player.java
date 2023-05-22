import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
// PLayer class
import java.util.PriorityQueue;
public class Player extends Entity implements MouseMotionListener, MouseListener {
    
    GamePanel gp;
    KeyHandler kh;
    Queue pX;
    Queue pY;
    ArrayList<Enemy> enems;
    int count;
    int mouseX;
    int mouseY;
    int time = 0;
    int health = 100;
    int tileSize;
    TutorialPanel tp;
    // construtor
    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;
        x = 100;
        y = 100;
        setDefaultValues();
        getPlayerImage();
        hitbox = new Rectangle(x+8, y+16, 32, 32);
        tileSize = gp.tileSize;
    }
    public Player(TutorialPanel tp, KeyHandler kh, int x, int y) {
        this.tp = tp;
        this.kh = kh;
        this.x = x;
        this.y = y;
        setDefaultValues();
        getPlayerImage();
        hitbox = new Rectangle(this.x+8, this.y+16, 32, 32);
        tileSize = this.tp.tileSize;
    }
    // setting default values
    public void setDefaultValues() {
        speed = 4;
        direction = "down";
        pX = new PriorityQueue<Integer>();
        pY = new PriorityQueue<Integer>();
    }
    // gettign image
    public void getPlayerImage() {
        up1 = new ImageIcon("spritesUp1.png").getImage();
        up2 = new ImageIcon("spritesUp2.png").getImage();
        down1 = new ImageIcon("spritesDown1.png").getImage();
        down2 = new ImageIcon("spritesDown2.png").getImage();
        left1 = new ImageIcon("spritesLeft1.png").getImage();
        left2 = new ImageIcon("spritesLeft2.png").getImage();
        right1 = new ImageIcon("spritesRight1.png").getImage();
        right2 = new ImageIcon("spritesRight2.png").getImage();
    }
    // updating position
    public void update() {
        collisionCheck();
        time++;
        if (kh.upPressed) {
            direction = "up";
            y-=speed;
        }
        else if (kh.downPressed) {
            direction = "down";
            y+=speed;
        }
        else if (kh.leftPressed) {
            direction = "left";
            x-=speed;
        }
        else if (kh.rightPressed) {
            direction = "right";
            x+=speed;
        }

        collisionOn = false;

        if (kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed) {
            spriteCounter++;
            if (spriteCounter >= 8) {
                if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 1;
                spriteCounter = 0;
            }
        }
        else {
            spriteCounter = 0;
            spriteNum = 1;
        }
        if (kh.spacePressed) {
            if (time%30 == 0 || time == 1) {
                if (tp == null) {createBullet(mouseX, mouseY);}
                else createBullet2(mouseX, mouseY);
                System.out.println("time: " + time);
            }
        }
        else time = 0;
        for (int i = 0 ; i<enems.size(); i++) {
            pX.add(x);
            pY.add(y);
        }
    }
    public void createBullet(int mouseX, int mouseY) {
        int directionX = mouseX-x;
        int directionY = mouseY-y;
        System.out.println("new bullet going to " + directionX + "  " + directionY);
        gp.bull.add(new Bullet(x, y, directionX, directionY, enems));
    }
    public void createBullet2(int mouseX, int mouseY) {
        int directionX = mouseX-x;
        int directionY = mouseY-y;
        System.out.println("new bullet going to " + directionX + "  " + directionY);
        tp.bull.add(new Bullet(x, y, directionX, directionY, enems));
    }
    // check if collides with enemy
    public void collisionCheck() {
        for (Enemy em : enems) {
            if (getBounds().intersects(em.getBounds()) && em.health>0) {
                if (direction.equals("right")) {
                    kh.rightPressed = false;
                    x = em.x - 16;
                }
                if (direction.equals("left")) {
                    kh.leftPressed = false;
                    x = em.x + 16;
                }
                if (direction.equals("up")) {
                    kh.upPressed = false;
                    y = em.y + 40;
                }
                if (direction.equals("down")) {
                    kh.downPressed = false;
                    y = em.y - 40;
                }
                health--;
            }
        }
    }
    // drawing player
    public void draw(Graphics2D g) {
        // g.setColor(Color.white);
        
        // g.fillRect(x, y, gp.tileSize, gp.tileSize);

        Image image = null;

        if (direction.equals("up")) {
            if (spriteNum == 1) image = up1;
            if (spriteNum == 2) image = up2;
        }
        else if (direction.equals("down")) {
            if (spriteNum == 1) image = down1;
            if (spriteNum == 2) image = down2;
        }
        else if (direction.equals("left")) {
            if (spriteNum == 1) image = left1;
            if (spriteNum == 2) image = left2;
        }
        else if (direction.equals("right")) {
            if (spriteNum == 1) image = right1;
            if (spriteNum == 2) image = right2;
        }
        Image background = new ImageIcon("backgroundJailFr.png").getImage();
        g.drawImage(background, 0, 0, 616*2, 252*2, null);
        g.drawImage(image, x, y, tileSize, tileSize, null);
        Image healthbar = new ImageIcon("HealthBar.png").getImage();
        g.setColor(Color.red);
        g.fillRect(830, 5, (int)((double)(health)/100*(820/5-33)), 201/5-10);
        g.drawImage(healthbar, 800, 0, 820/5, 201/5, null);

        //g.drawString("" + count, 750, 800);
        //g.fill(getBounds());
    }
    // placeholder methods (next 6 methods)
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}

    // getting mouse position whenever it is moved
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
