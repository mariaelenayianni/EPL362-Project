package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ws.MySQLAccess;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;
import javax.swing.JComboBox;

public class viewReceptionistTransaction {

	private JFrame frame;
	private JTable table;
	private ReceptionistClient ls;
	private JComboBox comboBox;
	DefaultTableModel model = new DefaultTableModel(
			new String[] { "Case ID", "Strategy ID", "AppointmentID", "Disagree", "Disputes", "Updated", "Confirmation", "Details", "Case Type" }, 0);

	/**
	 * Launch the application.
	 * @throws MalformedURLException 
	 */
	public viewReceptionistTransaction() throws MalformedURLException {

		this.ls = new ReceptionistClient();
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
		frame.setBounds(100, 100, 967, 552);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblViewTransaction = new JLabel("View Transaction");
		lblViewTransaction.setFont(new Font("Calibri", Font.BOLD, 16));
		lblViewTransaction.setBounds(10, 11, 129, 26);
		frame.getContentPane().add(lblViewTransaction);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblId.setBounds(20, 50, 27, 14);
		frame.getContentPane().add(lblId);

		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblResults.setBounds(20, 88, 55, 20);
		frame.getContentPane().add(lblResults);

		comboBox = new JComboBox();
		comboBox.setBounds(68, 50, 78, 22);
		frame.getContentPane().add(comboBox);
		cclient();
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 119, 917, 330);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String id;
					int id_client = 0;
					int wrong = 0;
					id = (String)comboBox.getSelectedItem();
					
					
					if (id.equals("")) {
						wrong = 1;
						return;
					}
					if (wrong == 0) {
						id_client = Integer.parseInt(id);
				
					
					}
					
					viewTransactions(id_client);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnView.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnView.setBounds(169, 46, 78, 26);
		frame.getContentPane().add(btnView);
		
		
	}
	
	

	private void viewTransactions(int cID) throws Exception {

		ws.xml result = this.ls.viewTransactions(cID);
		// int pol = 1;
		for (String[] row : result.t) {

		
			model.addRow(new Object[] { row[0], row[1], row[2], row[3],row[4], row[5], row[6], row[7],row[8]});
		
			
		}

		table.setModel(model);

		// model.addRow(new Object[] { a, b, c, d });

	}

	
	
	private void cclient() throws Exception {

		ws.xml c = this.ls.cclient();
		for (String[] row : c.t) {
			String pat1 = row[0];
			comboBox.addItem(pat1);
		}
	}
	
	
}
