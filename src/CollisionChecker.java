public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {
        int entityLeft = entity.hitbox.x;
        int entityRight = entity.hitbox.x + entity.hitbox.width;
        int entityUp = entity.hitbox.y;
        int entityDown = entity.hitbox.y + entity.hitbox.height;
        
        int entityLeftCol = entityLeft/gp.tileSize;
        int entityRightCol = entityRight/gp.tileSize;
        int entityUpRow = entityUp/gp.tileSize;
        int entityDownRow = entityDown/gp.tileSize;

        int tileNum1, tileNum2;

        if (entity.direction == "up") {
            entityUpRow = (entityUp-entity.speed)/gp.tileSize;
            // tileNum1 = gp.tileM
        }
        else if (entity.direction == "down") {
            
        }
        if (entity.direction == "left") {
            
        }
        if (entity.direction == "right") {
            
        }
    }
}