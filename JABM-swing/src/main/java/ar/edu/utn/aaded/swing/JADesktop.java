package ar.edu.utn.aaded.swing;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import ar.edu.utn.aadeed.exception.JARuntimeException;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.session.JASessionFactory;

public class JADesktop extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private static final Object lock = new Object();
	
	private volatile static JADesktop instance = null;
	
	private static final String QUIT_ACTION = "quit";
	
	private JDesktopPane desktop;
	
	public static JADesktop getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new JADesktop();		
				}
			}
		}
		return instance;
	}
	
	private JADesktop() {
		
		super("JA Framework");

		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height	- inset * 2);

		desktop = new JDesktopPane();
		setContentPane(desktop);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		
		JFrame.setDefaultLookAndFeelDecorated(true);
	}

	protected JMenuBar createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();

		JMenu appMenu = new JMenu("Application");
		appMenu.setMnemonic(KeyEvent.VK_P);
		menuBar.add(appMenu);

		JMenuItem quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.setMnemonic(KeyEvent.VK_Q);
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		quitMenuItem.setActionCommand(QUIT_ACTION);
		quitMenuItem.addActionListener(this);
		appMenu.add(quitMenuItem);
		
		JMenu abmMenu = new JMenu("ABMs");
		abmMenu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(abmMenu);
		
		for (JASession<?> session : JASessionFactory.getInstance().getSessions()) {
			
			JMenuItem sessionMenuItem = new JMenuItem(session.getRepresentationFor().getSimpleName());
			sessionMenuItem.setActionCommand(session.getRepresentationFor().getName());
			sessionMenuItem.addActionListener(this);
			abmMenu.add(sessionMenuItem);
		}
		
		return menuBar;
	}

	public void actionPerformed(ActionEvent e) {
		if (QUIT_ACTION.equals(e.getActionCommand())) {
			quit();
		} else {
			createFrame(e.getActionCommand());
		}
	}
	
	public JDesktopPane getDesktop() {
		return desktop;
	}

	private void createFrame(final String className) {
		try {
			
			final Class<?> clazz = Class.forName(className);
			JASessionFactory.getInstance().getViewSession(clazz).renderMainPagePanel();
		} catch (Exception e) {
			throw new JARuntimeException("Could not show internal frame", e);
		}
	}

	private void quit() {
		System.exit(0);
	}

	public void showGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setJMenuBar(createMenuBar());
				setVisible(true);
			}
		});
	}
}
