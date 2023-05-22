import javax.swing.*;
import java.awt.*;
public class Enemy extends Entity{
    GamePanel gp;
    TutorialPanel tp;
    Player pl;
    boolean moving = false;
    int health = 3;
    int maxHealth = 3;
    int num;
    int tileSize;
    // construtor
    public Enemy(GamePanel gp, Player pl, int x, int y, int num) {
        this.gp = gp;
        this.pl = pl;
        this.x = x;
        this.y = y;
        setDefaultValues();
        getPlayerImage();
        this.num = num;
        tileSize = gp.tileSize;
    }
    public Enemy(TutorialPanel tp, Player pl, int x, int y, int num, int health) {
        this.tp = tp;
        this.pl = pl;
        this.x = x;
        this.y = y;
        setDefaultValues();
        getPlayerImage();
        this.num = num;
        tileSize = this.tp.tileSize;
        this.health = health;
        this.maxHealth = health;
    }
    // setting default values
    public void setDefaultValues() {
        speed = 2;
        direction = "down";
    }
    // getting image
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
    // npc movement
    public void update() {
        // System.out.println(num + " is updating, size of thing: " + pl.pX.size());
        if (pl.pX.size()>=(149-num) && tp == null) {
            int plx = (int)(pl.pX.poll());
            int ply = (int)(pl.pY.poll());
            if (plx == x && ply == y) moving = false;
            else if (Math.abs(ply-y)>6) {
                moving = true;
                if (ply > y) {y+=speed; direction = "down";}
                else {y-=speed; direction = "up";}
            }
            else if (plx!=x) {
                moving = true;
                if (plx > x) {x+=speed; direction = "right";}
                else {x-=speed; direction = "left";}
            }
            if (moving) {
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
        }
        if (health == 0) {
            System.out.println("bro deda");
        }
    }
    // drawing enemy
    public void draw(Graphics2D g) {

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
        g.drawImage(image, x, y, tileSize, tileSize, null);
        g.setColor(Color.black);
        g.drawRect(x, y-20, 48, 10);
        g.setColor(Color.red);
        g.fillRect(x+2, y-20+2, (int)((double)(health)/maxHealth*(48)-4), 10-4);
        //g.fill(getBounds());
    }
}
