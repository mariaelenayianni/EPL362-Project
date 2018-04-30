package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ws.MySQLAccess;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class viewAttendance {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private ReceptionistClient ls;

	/**
	 * Launch the application.
	 * 
	 * @throws MalformedURLException
	 */
	public viewAttendance() throws MalformedURLException {
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
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		MySQLAccess conn = new MySQLAccess();

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblAttendanceOfToday = new JLabel("Day Attendance Fail");
		lblAttendanceOfToday.setFont(new Font("Calibri", Font.BOLD, 18));
		lblAttendanceOfToday.setBounds(10, 11, 178, 22);
		frame.getContentPane().add(lblAttendanceOfToday);

		scrollPane = new JScrollPane();

		scrollPane.setBounds(20, 51, 365, 187);
		frame.getContentPane().add(scrollPane);
		model = new DefaultTableModel(new String[] { "AppointmentID", "ClientID", "LawyerID" }, 0);
		table = new JTable();
		scrollPane.setViewportView(table);
		viewAppointment();

	}

	private void viewAppointment() throws Exception {

		ws.xml result = this.ls.viewAppointment();
		for (String[] row : result.t) {
			model.addRow(new Object[] { row[0], row[1], row[2] });
		}
		table.setModel(model);

	}

}
