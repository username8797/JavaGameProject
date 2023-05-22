import java.awt.*;
// base entity class for any object
public class Entity {
    public int x, y;
    public int speed;
    public Image up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle hitbox;
    public boolean collisionOn = false;
    // getting bounds for entities
    public Rectangle getBounds() {

        int bx = x+16;
        int by = y+4;
        int bw = 16;
        int bh = 40;

        return new Rectangle(bx, by, bw, bh);
    }
}