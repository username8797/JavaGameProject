import java.awt.*;
import javax.swing.*;
public class StartMain {
    public static void main(String[] args) {
        StartMain sm = new StartMain();
        sm.runner();
    }
    // runner where frame is
    public void runner() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JailTime");
        
        StartPage startPanel = new StartPage(window);
        window.add(startPanel);
        window.pack();
        window.setVisible(true);
    }
}
