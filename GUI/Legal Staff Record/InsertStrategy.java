package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ws.MySQLAccess;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class InsertStrategy {

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
					InsertStrategy window = new InsertStrategy();
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
	public InsertStrategy() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MySQLAccess conn = new MySQLAccess();

		frame = new JFrame();
		frame.setBounds(100, 100, 351, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInsertStrategy = new JLabel("Insert Strategy");
		lblInsertStrategy.setFont(new Font("Calibri", Font.BOLD, 18));
		lblInsertStrategy.setBounds(10, 11, 119, 23);
		frame.getContentPane().add(lblInsertStrategy);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblName.setBounds(10, 72, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblSideEffects = new JLabel("Side Effects:");
		lblSideEffects.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSideEffects.setBounds(10, 125, 84, 14);
		frame.getContentPane().add(lblSideEffects);
		
		textField = new JTextField();
		textField.setBounds(104, 69, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(104, 122, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name=textField.getText();
				String sideEffect=textField_1.getText();
				
				try {
					Statement pst=conn.readDataBaseTransac();
					
					String query="INSERT INTO strategy VALUES (default,'"+name+"', '"+ sideEffect+"')";
					pst.executeUpdate(query);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnSubmit.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSubmit.setBounds(181, 208, 89, 23);
		frame.getContentPane().add(btnSubmit);
	}
}
