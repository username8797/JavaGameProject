/*
- Amish Tyagi
- Game Project: Jailtime
- 4/30/23
*/
import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
// main class with the full project
public class GameProjectMain extends JPanel implements ActionListener {
	JFrame allFrame;
	JLayeredPane frontPanel;
	JPanel backPanel, secondPanel, mainPanel, textPanel, levelSelect;
	CardLayout cl;
	Font customFont, customFont2, customFont3, customFont4;
	Timer tm, fadeTm, labelTm;
	int time = 0;
	int time2 = 255;
	boolean switchPan, labelFade;
	int x = 255;
	int x2 = 0;
	JLabel anim;
	Image jailImage = new ImageIcon("inJail.gif").getImage();
	// Constructor that declares all the fonts for use
	public GameProjectMain() {
		switchPan = labelFade = false;
		try {
			//create the font to use. Specify the size!
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Jumpman-1m20.ttf")).deriveFont(80f);
			customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("Jumpman-1m20.ttf")).deriveFont(90f);
			customFont3 = Font.createFont(Font.TRUETYPE_FONT, new File("Jumpman-1m20.ttf")).deriveFont(130f);
			customFont4 = Font.createFont(Font.TRUETYPE_FONT, new File("Jumpman-1m20.ttf")).deriveFont(40f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(customFont);
			ge.registerFont(customFont2);
		} catch (IOException e) {
			e.printStackTrace();
		} catch(FontFormatException e) {
			e.printStackTrace();
		}
	}
	// main that runs the runner/constructor
	public static void main(String[] args) throws MalformedURLException{
		// TODO Auto-generated method stub
		GameProjectMain gpm = new GameProjectMain();
		gpm.runner();
	}
	// main method of the entire program, has animations and all frame components
	public void runner() throws MalformedURLException{
		allFrame = new JFrame();
		allFrame.getContentPane();
		allFrame.setSize(800, 830);
		allFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		cl = new CardLayout();
		mainPanel.setLayout(cl);
		backPanel = new JPanel();
		frontPanel = new JLayeredPane();
		frontPanel.setOpaque(true);
		frontPanel.setBackground(Color.white);
		secondPanel = new JPanel();
		mainPanel.add(backPanel);
		mainPanel.add(secondPanel);
		mainPanel.add(frontPanel);
		secondPanel.setBackground(Color.white);
		JLabel anim2 = new JLabel();
		Icon icon2 = new ImageIcon("explosion.gif");
		anim2.setBounds(10, 10, 780, 780);
		anim2.setIcon(icon2);
		secondPanel.add(anim2);
		JLabel anim3 = new JLabel();
		Icon icon = new ImageIcon("rocket.gif");
		anim3.setBounds(0, -200, 800, 800);
		anim3.setIcon(icon);
		backPanel.add(anim3);
		tm = new Timer(1, this);
		tm.start();
		TimerHandler th = new TimerHandler();
		ButtonHandler bh = new ButtonHandler();
		JButton start = new JButton("Play");
		JButton how = new JButton("How to Play");
		start.setBounds(300, 570+25, 200, 100);
		start.addActionListener(bh);
		how.setBounds(75, 650+25, 600, 100);
		start.addMouseListener(new java.awt.event.MouseAdapter() {
			// check if mouse entered button
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				start.setFont(customFont2);
				start.setBounds(275, 570+25, 250, 100);
			}
			// check if mouse exited button
			public void mouseExited(java.awt.event.MouseEvent evt) {
				start.setFont(customFont);
				start.setBounds(300, 570+25, 200, 100);
			}
		});
		start.setFont(customFont);
		how.addMouseListener(new java.awt.event.MouseAdapter() {
			// check if mouse entered button
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				how.setFont(customFont2);
				how.setBounds(50, 650+25, 700, 100);
			}	
			// check if mouse exited button
			public void mouseExited(java.awt.event.MouseEvent evt) {
				how.setFont(customFont);
				how.setBounds(75, 650+25, 650, 100);
			}
		});
		how.setFont(customFont);
		frontPanel.add(start, Integer.valueOf(2));
		frontPanel.add(how, Integer.valueOf(2));
		runBackgroundAnimation();
		start.setOpaque(false);
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		how.setOpaque(false);
		how.setContentAreaFilled(false);
		how.setBorderPainted(false);
		JLabel title = new JLabel("Jailtime", SwingConstants.CENTER);
		title.setFont(customFont3);
		title.setBounds(150, 0, 500, 100);
		fadeTm = new Timer(5,new ActionListener(){
			// fade for diff objects on start page
            public void actionPerformed(ActionEvent ae)
            {

                title.setForeground(new Color(0,0,0,x--));
                title.setBackground(new Color(255,255,255,x));
				// anim.setForeground(new Color(0,0,0,x--));
                // anim.setBackground(new Color(255,255,255,x));
				start.setForeground(new Color(0,0,0,x--));
                start.setBackground(new Color(255,255,255,x));
				how.setForeground(new Color(0,0,0,x--));
                how.setBackground(new Color(255,255,255,x));
                if(x==0) {
					//start.setEnabled(false);
					//how.setEnabled(false);
					fadeTm.stop();
					allFrame.setVisible(false);
					GameMain gm = new GameMain();
					gm.runner();
					// cl.next(mainPanel);
					// runText();
				}
            }
        });
		frontPanel.add(title, Integer.valueOf(2));
		textPanel = new JPanel(null);
		levelSelect = new JPanel();
		mainPanel.add(textPanel);
		mainPanel.add(levelSelect);
		levelSelectPage();
		allFrame.setBackground(Color.white);
		allFrame.add(mainPanel);
		allFrame.setVisible(true);
	}
	// level select
	public void levelSelectPage() {
		ImageIcon icon = new ImageIcon("brickWall.jpeg");
		Image img = icon.getImage();
		int width = (int)(icon.getIconWidth()/2.5);
		int height = (int)(icon.getIconHeight()/2.5);
		Image newImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		JButton level1 = new JButton("Break Out!", icon);
		level1.setFont(customFont);
		level1.setHorizontalTextPosition(JButton.CENTER);
		level1.setVerticalTextPosition(JButton.BOTTOM);
		levelSelect.setLayout(null);
		level1.setLocation(100, 250);
		levelSelect.add(level1);
	}
	// runs the jail animation that the guy is in	
	public void runBackgroundAnimation() throws MalformedURLException{
		Icon icon = new ImageIcon("jailtimeBackground.png");
		anim = new JLabel(icon);
		//anim.setIcon(icon);
		frontPanel.add(anim, Integer.valueOf(0));
	}
	// runs text after play button 
	public void runText() {
		JLabel l1 = new JLabel();
		l1.setText("Beware, for every choice you make, there are consequences that follow.");
		l1.setLocation(350, 300);
		labelTm = new Timer(5,new ActionListener(){
        
            public void actionPerformed(ActionEvent ae)
            {
				if (!labelFade) {
					l1.setForeground(new Color(0,0,0,x2++));
					l1.setBackground(new Color(255,255,255,x2));
					if(x2==254) {
						//start.setEnabled(false);
						//how.setEnabled(false);
						labelTm.stop();
						labelFade = !labelFade;
						labelTm.setInitialDelay(1000);
						labelTm.start();
					}
				}
				else {
					System.out.println(x2);
					l1.setForeground(new Color(0,0,0,x2--));
					l1.setBackground(new Color(255,255,255,x2));
					if(x2==0) {
						//start.setEnabled(false);
						//how.setEnabled(false);
						labelTm.stop();
						cl.next(mainPanel);
					}
				}
            }
        });
		textPanel.add(l1);
		l1.setForeground(new Color(0, 0, 0, 0));
		labelTm.start();
	}
	// deals with the timer for switching between panels
	public void actionPerformed(ActionEvent event) {
		if (time >= 2200) {
			tm.stop();
			cl.next(mainPanel);
		}
        if (time >= 1800 && !switchPan) {
			cl.next(mainPanel);
			switchPan = true;
		}
		time++;
		// System.out.println(time);	
    }
	// buttonhandler for when play is pressed, fades things out
	class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			if (cmd == "Play") {
				//allFrame.add(new GameProjectMain());
				fadeTm.start();
			}
		}

	}
	// not used, old fade method
	class TimerHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (time2 <= 0) {
				frontPanel.setBackground(new Color(255, 255, 255, time2));
				fadeTm.stop();
			}
			time2--;
			System.out.println(time2);
		}

	}
	// paint method to draw/fade out image
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

    	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		System.out.println("hi");
    	g2d.drawImage(jailImage, 10, 10, null);
	}
}