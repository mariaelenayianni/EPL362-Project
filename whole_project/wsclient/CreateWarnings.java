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
 * This class create warnings
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class CreateWarnings {

	private JFrame frame;
	private JTextField textField_2;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private LegalRecordStaffClient ls;
	private JComboBox comboBox_1;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 * 
	 * @throws MalformedURLException
	 */
	public CreateWarnings() throws MalformedURLException {
		this.ls = new LegalRecordStaffClient();
		this.comboBox_1 = new JComboBox();
		this.comboBox = new JComboBox();
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
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 435, 376);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblCreateWarning = new JLabel("Create Warning");
		lblCreateWarning.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCreateWarning.setBounds(26, 13, 243, 35);
		frame.getContentPane().add(lblCreateWarning);

		JLabel lblLawyerid = new JLabel("LawyerID:");
		lblLawyerid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLawyerid.setBounds(26, 84, 96, 25);
		frame.getContentPane().add(lblLawyerid);

		JLabel lblClientid = new JLabel("ClientID:");
		lblClientid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClientid.setBounds(26, 128, 96, 25);
		frame.getContentPane().add(lblClientid);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(26, 166, 96, 25);
		frame.getContentPane().add(lblDescription);

		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(26, 204, 96, 25);
		frame.getContentPane().add(label);

		textField_2 = new JTextField();
		textField_2.setBounds(134, 168, 174, 72);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Select LawyersID" }));
		comboBox.setBounds(134, 86, 135, 22);
		frame.getContentPane().add(comboBox);

		clawyer();

		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Select ClientID" }));
		comboBox_1.setBounds(134, 130, 135, 22);
		frame.getContentPane().add(comboBox_1);
		
		cclient();
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {

				try {
					insertWarning();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
						});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(263, 266, 104, 35);
		frame.getContentPane().add(btnSubmit);

	
	}
	/**
	 * This function requests for lawyer
	 * @throws Exception
	 */
	private void clawyer() throws Exception {

		ws.xml l = this.ls.clawyer();
		for (String[] row : l.t) {
			String pat1 = row[0];
			comboBox.addItem(pat1);
		}
	}

	/**
	 * This function requests for clients
	 * @throws Exception
	 */
	private void cclient() throws Exception {

		ws.xml c = this.ls.cclient();
		for (String[] row : c.t) {
			String pat1 = row[0];
			comboBox_1.addItem(pat1);
		}
	}
	
	/**
	 * This function requests for insert warnings
	 * @throws Exception
	 */
	private void insertWarning() throws Exception {
		String cID = (String) comboBox_1.getSelectedItem();
		String lID = (String) comboBox.getSelectedItem();

		String description = textField_2.getText();
		this.ls.insertWarning(cID,lID,description);

	}

	

}
