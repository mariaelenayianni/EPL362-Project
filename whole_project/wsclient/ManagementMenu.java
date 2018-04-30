package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;

/**
 * This class represents an UI for Management Menu
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class ManagementMenu {

	private JFrame frmManagement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws MalformedURLException {
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagementMenu window = new ManagementMenu();
					window.frmManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Constructor
	 */
	public ManagementMenu() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManagement = new JFrame();
		frmManagement.setTitle("Management");
		frmManagement.setBounds(100, 100, 450, 300);
		frmManagement.setVisible(true);

		frmManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManagement.getContentPane().setLayout(null);

		JButton btnWeeklyReports = new JButton("Weekly Reports");
		btnWeeklyReports.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					new weeklyReports();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnWeeklyReports.setBounds(24, 28, 147, 36);
		frmManagement.getContentPane().add(btnWeeklyReports);
	}

}
