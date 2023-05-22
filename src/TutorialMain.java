import javax.swing.*;
public class TutorialMain {
    public static void main(String[] args) {
        TutorialMain tm = new TutorialMain();
        tm.runner();    
    }
    public void runner() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JailTime");
        
        TutorialPanel tutorialPanel = new TutorialPanel();
        window.add(tutorialPanel);
        window.pack();
        window.setVisible(true);
    }
}