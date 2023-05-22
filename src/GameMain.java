import javax.swing.*;
// where the panel is added
public class GameMain {
    // main
    public static void main(String[] args) {
        GameMain gm = new GameMain();
        gm.runner();
    }
    // runner where frame is
    public void runner() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JailTime");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setVisible(true);
    }
}