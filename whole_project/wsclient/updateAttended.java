package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ws.MySQLAccess;

import javax.swing.JScrollPane;

public class updateAttended {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private ReceptionistClient ls;
	/**
	 * Launch the application.
	 * @throws MalformedURLException 
	 */
	public updateAttended() throws MalformedURLException {
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
		MySQLAccess conn = new MySQLAccess();

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNeedUpdate = new JLabel("Daily Not Update");
		lblNeedUpdate.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNeedUpdate.setBounds(10, 11, 180, 23);
		frame.getContentPane().add(lblNeedUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 59, 393, 179);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(new String[]{"ClientID","AppointmentID", "CaseID"}, 0);

		table.setModel(model);

		updateAppointment();
		scrollPane.setViewportView(table);
	}
	
	
	private void updateAppointment() throws Exception {

		ws.xml result = this.ls.updateAppointment();
		for (String[] row : result.t) {
			model.addRow(new Object[] { row[0], row[1], row[2] });
		}
		table.setModel(model);

	}
	
}
