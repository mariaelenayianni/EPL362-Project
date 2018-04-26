package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ws.MySQLAccess;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DeleteClient {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteClient window = new DeleteClient();
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
	public DeleteClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MySQLAccess conn = new MySQLAccess();

		frame = new JFrame();
		frame.setBounds(100, 100, 275, 261);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDeleteClient = new JLabel("Delete Client:");
		lblDeleteClient.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDeleteClient.setBounds(10, 11, 107, 24);
		frame.getContentPane().add(lblDeleteClient);
		
		JLabel lblClientId = new JLabel("Client ID:");
		lblClientId.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblClientId.setBounds(10, 63, 66, 14);
		frame.getContentPane().add(lblClientId);
		
		textField = new JTextField();
		textField.setBounds(86, 60, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField.getText();
				int c_id=Integer.parseInt(id);
				
				try {
					Statement pst=conn.readDataBaseTransac();
					String query="UPDATE `client` SET `readonly` = 1 where `clientID`="+ c_id;
					
					int rs2=pst.executeUpdate(query);


				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				

			}
		});
		btnSubmit.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnSubmit.setBounds(83, 120, 89, 23);
		frame.getContentPane().add(btnSubmit);
	}

}
