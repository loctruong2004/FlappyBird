package view;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.acction_top;
import controller.action_login;
import controller.action_menu;
import model.DB;
import model.Person;

public class app {
	private static JFrame frame;
	private static login lg;
	private static FlappyBird flappybrid;
	private static topPlayer topplayer;
	public DB topPlay;
	private action_login ac;
	private acction_top actop;
    private action_menu ac_menu;
    
	public static void main(String[] args) {
		new app();
	}

	public app() {
		this.init();
		
	}

	public void init() {
		int broadWidth = 360;
		int broadHeight = 640;
		topPlay = new DB();
		topPlay.connectDB();
		lg = new login(topPlay);
		ac = new action_login(lg, this);
        ac_menu = new action_menu(this);
		topplayer= new topPlayer(topPlay);
		frame = new JFrame("Flappy Bird");

		frame.setSize(broadWidth, broadHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar jmenu = new JMenuBar();
		JMenu file = new JMenu("file");
		JMenuItem top = new JMenuItem("top");
		jmenu.add(file);
		file.add(top);
		top.addActionListener(ac_menu);
		
		frame.setJMenuBar(jmenu);
		frame.add(lg);
		lg.addLoginListener(ac);
		frame.setVisible(true);
	}

	public void startGame(String name) {
		Person ps = new Person(name, null);
		this.topPlay = new DB(ps);
		flappybrid = new FlappyBird(topPlay);
		lg.removeActionListener();
		frame.remove(lg);
		frame.add(flappybrid);
		flappybrid.requestFocus();
		frame.pack();
		frame.revalidate();
		frame.repaint();
	}

	public void showTopPlayer() {
		actop = new acction_top(this);
		topplayer.addTop(actop);
		frame.remove(lg);
		frame.add(topplayer);
		frame.revalidate();
		frame.repaint();
	}
	
    
	public void showLogin() {
		frame.remove(topplayer);
		frame.add(lg);
		lg.addLoginListener(ac);
		frame.revalidate();
		frame.repaint();
	}
	public void showTopPlayerMenu() {
		actop = new acction_top(this);
		topplayer.addTop(actop);
		frame.remove(flappybrid);
		frame.add(topplayer);
		frame.revalidate();
		frame.repaint();
	}
}
