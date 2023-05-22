import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;
// class for bullet object
public class Bullet{
    int x;
    int y;
    int directionX;
    int directionY;
    ArrayList<Enemy> enems = new ArrayList<Enemy>();
    boolean hit = false;
    boolean played = false;
    // constructor
    public Bullet(int x, int y, int directionX, int directionY, ArrayList<Enemy> em) {
        this.x = x;
        this.y = y;
        this.directionX = directionX;
        this.directionY = directionY;
        this.enems = em;   
    }
    //updating position
    public void update() {
        // double angle = Math.atan(directionY/directionX);
        // System.out.println("angle of mouse: " + angle);
        // x += 5*Math.cos(angle);
        // y += 5*Math.sin(angle);
        x+=directionX/10;
        y+=directionY/10;
        collisionCheck();
    }
    public void collisionCheck() {
        for (Enemy em : enems) {
            if (getBounds().intersects(em.getBounds()) && em.health!=0) {
                System.out.println("hit Enemy");
                em.health--;
                hit = true;
            }
        }
    }
    // drawing bullet in new position
    public void draw(Graphics2D g) {
        if (!hit && !played) {
            g.setColor(Color.black);
            g.fillOval(x, y, 10, 10);
        }
        else {
            Image splatter = new ImageIcon("splatterNonLoop.gif").getImage();
            g.drawImage(splatter, x, y, null);
            played = true;
        }
    }
    public Rectangle getBounds() {
        int bx = x;
        int by = y;
        int bw = 10;
        int bh = 10;
        
        return new Rectangle(bx, by, bw, bh);
    }
}
