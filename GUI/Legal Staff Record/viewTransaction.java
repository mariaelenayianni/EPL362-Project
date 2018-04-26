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
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;

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

	public viewTransaction() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MySQLAccess conn = new MySQLAccess();
		frame = new JFrame();
		frame.setBounds(100, 100, 551, 351);
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

		textField = new JTextField();
		textField.setBounds(53, 47, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblResults.setBounds(20, 88, 55, 20);
		frame.getContentPane().add(lblResults);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 119, 484, 170);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel(
				new String[] { "Case ID", "Strategy ID", "Disagree", "Disputes", "Updated", "Confirmation", "Details" },
				0);

		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String id;
					int id_client;
					int wrong = 0;
					id = textField.getText();
					if (id.equals("")) {
						wrong = 1;
					}
					if (wrong == 0) {
						id_client = Integer.parseInt(id);
						Statement pst = conn.readDataBase();
						String query = "select * from cases, appointment where appointment.appointmentID=cases.appointmentID AND appointment.clientID="
								+ id_client;

						ResultSet rs = pst.executeQuery(query);
						ResultSetMetaData metaData = rs.getMetaData();

						while (rs.next()) {

							String a = rs.getString("caseID");
							String b = rs.getString("strategyID");
							String c = rs.getString("appointmentID");
							String d = rs.getString("Disagree");
							String e = rs.getString("Disputes");
							String f = rs.getString("Updated");
							String h = rs.getString("Confirmation");
							String j = rs.getString("Details");
							String k = rs.getString("CaseType");
							model.addRow(new Object[] { a, b, c, d, e, f, h, j, k });
						}

						// while (rs.next()) {

						table.setModel(model);

						// table.setModel(null);
						// }
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnView.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnView.setBounds(169, 46, 78, 18);
		frame.getContentPane().add(btnView);

	}
}
