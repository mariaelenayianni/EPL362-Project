package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import com.mysql.jdbc.MySQLConnection;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class viewTransaction {

	private JFrame frame;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewTransaction window = new viewTransaction();
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
	Connection connection = null;
	
	public viewTransaction() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblViewTransaction = new JLabel("View Transaction");
		lblViewTransaction.setFont(new Font("Calibri", Font.BOLD, 16));
		lblViewTransaction.setBounds(10, 11, 129, 26);
		frame.getContentPane().add(lblViewTransaction);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblId.setBounds(20, 50, 27, 14);
		frame.getContentPane().add(lblId);
		
		textField = new JTextField();
		textField.setBounds(53, 47, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblResults.setBounds(20, 88, 55, 20);
		frame.getContentPane().add(lblResults);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 119, 317, 131);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="select * from cases";
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnView.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnView.setBounds(169, 46, 59, 23);
		frame.getContentPane().add(btnView);
		
	
	}
}
