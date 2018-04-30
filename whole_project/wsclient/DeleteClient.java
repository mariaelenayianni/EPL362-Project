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
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * This class represents an UI for delete client
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class DeleteClient {

	private JFrame frame;
	private LegalRecordStaffClient ls;
	private JComboBox comboBox ;
	/**
	 * Launch the application.
	 * @throws MalformedURLException 
	 */
	public DeleteClient() throws MalformedURLException {
		
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
		MySQLAccess conn = new MySQLAccess();

		frame = new JFrame();
		frame.setBounds(100, 100, 254, 220);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDeleteClient = new JLabel("Delete Client:");
		lblDeleteClient.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDeleteClient.setBounds(10, 11, 107, 24);
		frame.getContentPane().add(lblDeleteClient);
		
		JLabel lblClientId = new JLabel("Client ID:");
		lblClientId.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblClientId.setBounds(10, 63, 66, 14);
		frame.getContentPane().add(lblClientId);
		comboBox = new JComboBox();
		cclient();
		comboBox.setBounds(88, 58, 84, 22);
		frame.getContentPane().add(comboBox);
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				String cID = (String) comboBox.getSelectedItem();
				int c_id=Integer.parseInt(cID);
				
				try {
					DeleteClient(c_id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				

			}
		});
		btnSubmit.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnSubmit.setBounds(122, 120, 89, 23);
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
	 * This function requests a deletion of a client
	 * @param cID
	 * @throws Exception
	 */
	private void DeleteClient(int cID) throws Exception {
		this.ls.DeleteClient(cID);
	}
}
