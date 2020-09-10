package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainMenu extends JFrame {

	private JPanel contentPane;
	JDesktopPane deskInterFrm=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenu frame = new mainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainMenu() {
		setBackground(SystemColor.windowBorder);
		setTitle("Library Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 915, 659);
		setExtendedState( this.MAXIMIZED_BOTH );
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.menu);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Operation");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Check Out");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchInterFrm bookSrh = new bookSearchInterFrm();
				deskInterFrm.add(bookSrh);
				bookSrh.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Check In");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ckInInterFrm ckIn = new ckInInterFrm();
				deskInterFrm.add(ckIn);
				ckIn.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Add User");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    brwerAddInterFrm brwAdd = new brwerAddInterFrm();   
				deskInterFrm.add(brwAdd);
				brwAdd.setVisible(true);
			}
		});
		
		JMenuItem mntmFineManagement = new JMenuItem("Fine Management");
		mntmFineManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FineInterFrm fineMng = new FineInterFrm();
				deskInterFrm.add(fineMng);
				fineMng.setVisible(true);
			}
		});
		mnNewMenu.add(mntmFineManagement);
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Exit"); 
		mnNewMenu_2.setIcon(null);
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Safe Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Do you want to exit the system?");
				if(result ==0) {
					dispose();
				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		deskInterFrm = new JDesktopPane();
		deskInterFrm.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		deskInterFrm.setBackground(SystemColor.window);
		contentPane.add(deskInterFrm, BorderLayout.CENTER);
	}
}
