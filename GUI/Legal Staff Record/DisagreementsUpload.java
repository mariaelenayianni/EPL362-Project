package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ws.MySQLAccess;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DisagreementsUpload {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisagreementsUpload window = new DisagreementsUpload();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DisagreementsUpload() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 341, 303);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUploadDisagreements = new JLabel("Upload Disagreements");
		lblUploadDisagreements.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUploadDisagreements.setBounds(12, 13, 264, 23);
		frame.getContentPane().add(lblUploadDisagreements);

		JLabel lblClientid = new JLabel("ClientID:");
		lblClientid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClientid.setBounds(12, 84, 72, 23);
		frame.getContentPane().add(lblClientid);

		textField = new JTextField();
		textField.setBounds(118, 85, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblStrategyid = new JLabel("StrategyID:");
		lblStrategyid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStrategyid.setBounds(12, 146, 82, 23);
		frame.getContentPane().add(lblStrategyid);

		textField_1 = new JTextField();
		textField_1.setBounds(118, 147, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	
		JButton btnSumbit = new JButton("Submit");

		btnSumbit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				MySQLAccess sqlcon = new MySQLAccess();
				try {
					String strategyID = textField_1.getText();
					String clientID = textField.getText();
		
					sqlcon.DisagreementUploadSQL(clientID, strategyID);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSumbit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSumbit.setBounds(177, 198, 121, 25);
		frame.getContentPane().add(btnSumbit);
	}
}
