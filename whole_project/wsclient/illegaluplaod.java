package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ws.MySQLAccess;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * This class represents an UI for illlegal upload
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class illegaluplaod {

	private JFrame frame;
	private LegalRecordStaffClient ls;
	JComboBox comboBox;
	/**
	 * Launch the application.
	 * @throws MalformedURLException 
	 */
	public illegaluplaod() throws MalformedURLException {
		this.ls = new LegalRecordStaffClient();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 369, 245);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIllegalMoneyLaundering = new JLabel("Illegal Money Laundering Upload");
		lblIllegalMoneyLaundering.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIllegalMoneyLaundering.setBounds(12, 13, 303, 32);
		frame.getContentPane().add(lblIllegalMoneyLaundering);
		
		JLabel lblClientid = new JLabel("ClientID:");
		lblClientid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClientid.setBounds(12, 80, 72, 16);
		frame.getContentPane().add(lblClientid);
		
		comboBox = new JComboBox();
		
		comboBox.setBounds(96, 78, 114, 22);
		frame.getContentPane().add(comboBox);
		
		cclient();
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {

				int c_ID = Integer.parseInt((String)comboBox.getSelectedItem());
				try {
					makeIllegal(c_ID);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	
		btnSubmit.setBounds(218, 147, 97, 25);
		frame.getContentPane().add(btnSubmit);
	}
	
	
	/**
	 * This function requests for clients
	 * @throws Exception
	 */
	private void cclient() throws Exception {

		ws.xml c = this.ls.cclient();
		for (String[] row : c.t) {
			String pat1 = row[0];
			comboBox.addItem(pat1);
		}
	}
	
	/**
	 * This function requests to make illegal a client
	 * @param cID
	 * @throws Exception
	 */
	private void makeIllegal(int cID) throws Exception {
		this.ls.makeillegal(cID);
	}
	

}
