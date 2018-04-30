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

/**
 * This class represents an UI to insert legal opinions
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class InsertLegalOpinions {

	private JFrame frame;
	private JTextField textField_1;
	private LegalRecordStaffClient ls;

	/**
	 * Launch the application.
	 * 
	 * @throws MalformedURLException
	 */
	public InsertLegalOpinions() throws MalformedURLException {
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
		frame.setBounds(100, 100, 351, 288);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblInsertStrategy = new JLabel("Insert Legal Opinion");
		lblInsertStrategy.setFont(new Font("Calibri", Font.BOLD, 18));
		lblInsertStrategy.setBounds(10, 13, 190, 23);
		frame.getContentPane().add(lblInsertStrategy);

		JLabel lblSideEffects = new JLabel("Description:");
		lblSideEffects.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSideEffects.setBounds(10, 61, 84, 14);
		frame.getContentPane().add(lblSideEffects);

		textField_1 = new JTextField();
		textField_1.setBounds(106, 61, 190, 105);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				String description = textField_1.getText();

				try {
					insertLegalOpinion(description);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSubmit.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSubmit.setBounds(111, 196, 89, 23);
		frame.getContentPane().add(btnSubmit);
	}

	/**
	 * This function requests for insertion of legal opinion
	 * @param description
	 * @throws Exception
	 */
	private void insertLegalOpinion(String description) throws Exception {
		this.ls.insertLegalOpinion(description);
	}

}