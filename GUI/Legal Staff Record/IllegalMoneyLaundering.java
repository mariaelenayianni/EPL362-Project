package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ws.MySQLAccess;
import javax.swing.JScrollPane;

public class IllegalMoneyLaundering {

	private JFrame frame;
	private JTable table;
	private Statement statement = null;
	private ResultSet resultSet = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IllegalMoneyLaundering window = new IllegalMoneyLaundering();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public IllegalMoneyLaundering() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 347, 434);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblIllegalMoneyLaundering = new JLabel("Illegal Money Laundering");
		lblIllegalMoneyLaundering.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIllegalMoneyLaundering.setBounds(23, 13, 293, 41);
		frame.getContentPane().add(lblIllegalMoneyLaundering);

		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblResults.setBounds(23, 87, 75, 32);
		frame.getContentPane().add(lblResults);

		MySQLAccess sqlcon = new MySQLAccess();
		try {
			statement = sqlcon.readDataBase();
			resultSet = statement.executeQuery("select client.clientID from client where client.illegal=1");

		

			DefaultTableModel model = new DefaultTableModel(new String[] { "ClientID" }, 0);

			while (resultSet.next()) {

				String a = resultSet.getString("clientID");

				model.addRow(new Object[] { a });
			}
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(22, 134, 253, 200);
			frame.getContentPane().add(scrollPane);
			table = new JTable();
			scrollPane.setViewportView(table);

			// while (rs.next()) {

			table.setModel(model);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			sqlcon.close();
		}
	}
}
