import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class StartPage extends JLayeredPane implements ActionListener {
    Font customFont, customFont2, customFont3, customFont4;
    Timer fadeTm, moveTm, moveTm2;
    int x = 0;
	int y = -360;
	JButton exit;
	JButton test;
	JLabel instruc;
	JFrame frame;
    public StartPage(JFrame frame) {
        this.setPreferredSize(new Dimension(960, 540));
		setBackground(Color.white);
        try {
			//create the font to use. Specify the size!
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("octin prison rg.ttf")).deriveFont(35f);
			customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("octin prison rg.ttf")).deriveFont(40f);
			customFont3 = Font.createFont(Font.TRUETYPE_FONT, new File("octin prison rg.ttf")).deriveFont(70f);
			customFont4 = Font.createFont(Font.TRUETYPE_FONT, new File("octin prison rg.ttf")).deriveFont(40f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(customFont);
			ge.registerFont(customFont2);
		} catch (IOException e) {
			e.printStackTrace();
		} catch(FontFormatException e) {
			e.printStackTrace();
		}
		this.frame = frame;
        runner();
    }
    public void runner() {
        JButton start = new JButton("Play");
		JButton how = new JButton("How to Play");
		start.setBounds(288-50, 450, 200, 60);
		// start.addActionListener(bh);
		how.setBounds(408-50, 450, 365, 60);
		start.addMouseListener(new java.awt.event.MouseAdapter() {
			// check if mouse entered button
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				start.setFont(customFont2);
			}
			// check if mouse exited button
			public void mouseExited(java.awt.event.MouseEvent evt) {
				start.setFont(customFont);
			}
		});
		start.setFont(customFont);
		how.addMouseListener(new java.awt.event.MouseAdapter() {
			// check if mouse entered button
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				how.setFont(customFont2);
			}	
			// check if mouse exited button
			public void mouseExited(java.awt.event.MouseEvent evt) {
				how.setFont(customFont);
			}
		});
		how.addActionListener(this);
		how.setFont(customFont);
        start.setOpaque(false);
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		how.setOpaque(false);
		how.setContentAreaFilled(false);
		how.setBorderPainted(false);
        add(start);
        start.addActionListener(this);
        add(how);
		JLabel title = new JLabel("JailTime", SwingConstants.CENTER);
		title.setFont(customFont3);
		title.setBounds(280, 0, 400, 100);
		add(title);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String cmd = e.getActionCommand();
		instruc = new JLabel(new ImageIcon("frame.png"));
		exit = new JButton();
		exit.setBounds(545+160, 51+100, 37, 37);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		add(exit, Integer.valueOf(3));
		test = new JButton();
		test.setBounds(403+160, 263+100, 158, 34);
		test.setOpaque(false);
		test.setContentAreaFilled(false);
		test.setBorderPainted(false);
		add(test, Integer.valueOf(3));
		ButtonHandler bh = new ButtonHandler();
		exit.addActionListener(bh);
		ButtonHandler2 bh2 = new ButtonHandler2();
		test.addActionListener(bh2);
        if (cmd.equals("Play")) {
			frame.setVisible(false);
			GameMain gm = new GameMain();
			gm.runner();
		}
		else if (cmd.equals("How to Play")) {
			System.out.println("pressed");
			instruc.setBounds(160, -360, 640, 360);
			add(instruc, Integer.valueOf(2));
			moveTm = new Timer(1, new ActionListener(){
				// fade for diff objects on start page
				public void actionPerformed(ActionEvent ae)
				{
					instruc.setBounds(160, y++, 640, 360);
					exit.setBounds(545+160, 51+y, 37, 37);
					test.setBounds(403+160, 263+y, 158, 34);
					if (y == 100) {
						moveTm.stop();
					}
				}
			});
			moveTm.start();
			
		}
    }
	public void removeButtons() {
		exit.setEnabled(false);
	}
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = new ImageIcon("jailtimeBackground.png").getImage();
        g.drawImage(background, 0, 0, 1920/2, 1080/2, null);
    }
	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			moveTm2 = new Timer(1, new ActionListener(){
				// fade for diff objects on start page
				public void actionPerformed(ActionEvent ae)
				{
					instruc.setBounds(160, y--, 640, 360);
					exit.setBounds(545+160, 51+y, 37, 37);
					test.setBounds(403+160, 263+y, 158, 34);
					if (y == -360) {
						moveTm2.stop();
					}
				}
			});
			moveTm2.start();
		}
	}
	class ButtonHandler2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			TutorialMain tm = new TutorialMain();
			tm.runner();
		}
	}
}
