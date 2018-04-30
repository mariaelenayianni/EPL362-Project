package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;


import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

/**
 * This class add a client
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class AddClient {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private Statement statement = null;
	private LegalRecordStaffClient ls;

	/**
	 * Launch the application.
	 * Constructor
	 * 
	 * @throws MalformedURLException
	 */
	public AddClient() throws MalformedURLException {
		this.ls = new LegalRecordStaffClient();

		EventQueue.invokeLater(new Runnable() {

			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
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
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 598, 690);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		JLabel lblAddClient = new JLabel("Add Client");
		lblAddClient.setFont(new Font("Calibri", Font.BOLD, 18));
		lblAddClient.setBounds(12, 13, 92, 23);
		frame.getContentPane().add(lblAddClient);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(12, 49, 56, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 92, 56, 16);
		frame.getContentPane().add(lblName);

		JLabel lblLastname = new JLabel("LastName:");
		lblLastname.setBounds(12, 133, 76, 16);
		frame.getContentPane().add(lblLastname);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(12, 172, 56, 16);
		frame.getContentPane().add(lblEmail);

		JLabel lblPersonalInfo = new JLabel("Personal Info:");
		lblPersonalInfo.setBounds(12, 219, 92, 16);
		frame.getContentPane().add(lblPersonalInfo);

		JLabel lblRisk = new JLabel("Risk:");
		lblRisk.setBounds(12, 322, 56, 16);
		frame.getContentPane().add(lblRisk);

		JLabel lblIllegal = new JLabel("Illegal:");
		lblIllegal.setBounds(12, 362, 56, 16);
		frame.getContentPane().add(lblIllegal);

		JLabel lblChanged = new JLabel("Changed:");
		lblChanged.setBounds(12, 403, 56, 16);
		frame.getContentPane().add(lblChanged);

		JLabel lblReadonly = new JLabel("Read-only:");
		lblReadonly.setBounds(12, 446, 76, 16);
		frame.getContentPane().add(lblReadonly);

		JLabel lblRecommendations = new JLabel("Recommendations:");
		lblRecommendations.setBounds(12, 496, 126, 16);
		frame.getContentPane().add(lblRecommendations);

		textField = new JTextField();
		textField.setBounds(164, 49, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(164, 86, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(164, 130, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(164, 169, 116, 22);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(164, 216, 232, 86);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(164, 319, 116, 22);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(164, 359, 116, 22);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setBounds(164, 400, 116, 22);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setBounds(164, 443, 116, 22);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);

		textField_9 = new JTextField();
		textField_9.setBounds(164, 487, 232, 86);
		frame.getContentPane().add(textField_9);
		textField_9.setColumns(10);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String clientID = textField.getText();
				String name = textField_1.getText();
				String lastname = textField_2.getText();
				String email = textField_3.getText();
				String personalinfo = textField_4.getText();
				String risk = textField_5.getText();
				String illegal = textField_6.getText();
				String changed = textField_7.getText();
				String readonly = textField_8.getText();
				String recommendations = textField_9.getText();
				int c_ID = Integer.parseInt(clientID);
				int riskint = Integer.parseInt(risk);
				int illegalint = Integer.parseInt(illegal);
				int changedint = Integer.parseInt(changed);
				int readonlyint = Integer.parseInt(readonly);
				int recommendationsint = Integer.parseInt(recommendations);
				try {
					makeaction(c_ID, name, lastname, email, personalinfo, riskint, illegalint, changedint, readonlyint,
							recommendationsint);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(418, 589, 97, 25);
		frame.getContentPane().add(btnNewButton);
	}

	/**
	 * This function makes action
	 * @param c_ID
	 * @param name
	 * @param lastname
	 * @param email
	 * @param personalinfo
	 * @param riskint
	 * @param illegalint
	 * @param changedint
	 * @param readonlyint
	 * @param recommendationsint
	 * @throws Exception
	 */
	private void makeaction(int c_ID, String name, String lastname, String email, String personalinfo, int riskint,
			int illegalint, int changedint, int readonlyint, int recommendationsint) throws Exception {
		this.ls.addclient(c_ID, name, lastname, email, personalinfo, riskint, illegalint, changedint, readonlyint,
				recommendationsint);

	}

}
