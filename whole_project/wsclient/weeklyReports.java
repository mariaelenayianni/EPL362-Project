package wsclient;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.joda.time.DateTime;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import ws.Management;
import ws.MySQLAccess;

public class weeklyReports {

	private JFrame frame;
	private JTable table;
	private ManagementClient ls;
	private DefaultTableModel model;
	private DefaultTableModel model_2;
	private DefaultTableModel model_3;
	private DefaultTableModel model_4;
	private DefaultTableModel model_5;
	private DefaultTableModel model_6;
	private DefaultTableModel model_7;
	private DefaultTableModel model_8;
	private DefaultTableModel model_9;
	private DefaultTableModel model_10;
	private DefaultTableModel model_11;
	private DefaultTableModel model_12;
	private DefaultTableModel model_13;
	private JTable table_1;
	private JTable table_5;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private final JLabel lblDailyReportsBy = new JLabel("Weekly Reports By Case Type");
	private JTable table_6;
	private JTable table_7;
	private JTable table_8;
	private JTable table_9;
	private JTable table_10;
	private JTable table_11;
	private JScrollPane scrollPane_11;
	private JTable table_12;
	private JScrollPane scrollPane_12;
	private JLabel lblWeeklyReportsLegal;

	/**
	 * Launch the application.
	 * 
	 * @throws MalformedURLException
	 */
	public weeklyReports() throws MalformedURLException {
		this.ls = new ManagementClient();
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
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 922, 756);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblWeeklyReport = new JLabel("Weekly Reports");
		lblWeeklyReport.setFont(new Font("Calibri", Font.BOLD, 18));
		lblWeeklyReport.setBounds(10, 11, 128, 23);
		frame.getContentPane().add(lblWeeklyReport);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 47, 232, 175);
		frame.getContentPane().add(scrollPane);

		model = new DefaultTableModel(new String[] { "BranchID", "TotalNumClients" }, 0);
		model_2 = new DefaultTableModel(new String[] { "BranchID", "Monday" }, 0);
		model_3 = new DefaultTableModel(new String[] { "BranchID", "Tuesday" }, 0);
		model_4 = new DefaultTableModel(new String[] { "BranchID", "Wednesday" }, 0);
		model_5 = new DefaultTableModel(new String[] { "BranchID", "Thursday" }, 0);
		model_6 = new DefaultTableModel(new String[] { "BranchID", "Friday" }, 0);
		model_7 = new DefaultTableModel(new String[] { "CaseType 1", "Sum" }, 0);
		model_8 = new DefaultTableModel(new String[] { "CaseType 2", "Sum" }, 0);
		model_9 = new DefaultTableModel(new String[] { "CaseType 3", "Sum" }, 0);
		model_10 = new DefaultTableModel(new String[] { "CaseType 4", "Sum" }, 0);
		model_11 = new DefaultTableModel(new String[] { "CaseType 5", "Sum" }, 0);
		model_13 = new DefaultTableModel(new String[] { "BranchID", "Legal Opinions" }, 0);

		table = new JTable();
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 504, 145, 137);
		frame.getContentPane().add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		JLabel lblDailyReports = new JLabel("Daily Reports By Branch");
		lblDailyReports.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDailyReports.setBounds(12, 456, 197, 23);
		frame.getContentPane().add(lblDailyReports);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(731, 504, 137, 137);
		frame.getContentPane().add(scrollPane_5);

		table_5 = new JTable();
		scrollPane_5.setViewportView(table_5);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(189, 504, 145, 137);
		frame.getContentPane().add(scrollPane_2);

		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(361, 504, 157, 137);
		frame.getContentPane().add(scrollPane_3);

		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(551, 504, 156, 137);
		frame.getContentPane().add(scrollPane_6);

		table_4 = new JTable();
		scrollPane_6.setViewportView(table_4);
		lblDailyReportsBy.setBounds(316, 13, 252, 36);
		frame.getContentPane().add(lblDailyReportsBy);
		lblDailyReportsBy.setFont(new Font("Calibri", Font.BOLD, 18));

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(316, 74, 200, 17);
		frame.getContentPane().add(scrollPane_4);

		table_6 = new JTable();
		scrollPane_4.setColumnHeaderView(table_6);

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(316, 90, 200, 17);
		frame.getContentPane().add(scrollPane_7);

		table_7 = new JTable();
		scrollPane_7.setColumnHeaderView(table_7);

		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(316, 104, 200, 17);
		frame.getContentPane().add(scrollPane_8);

		table_8 = new JTable();
		scrollPane_8.setColumnHeaderView(table_8);

		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(316, 120, 200, 17);
		frame.getContentPane().add(scrollPane_9);

		table_9 = new JTable();
		scrollPane_9.setColumnHeaderView(table_9);

		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(316, 134, 200, 17);
		frame.getContentPane().add(scrollPane_10);

		table_10 = new JTable();
		scrollPane_10.setColumnHeaderView(table_10);
		
		model_12=  new DefaultTableModel(new Object[][] {}, new String[] { "CaseType", "Sum" });
		
		scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(316, 47, 200, 33);
		frame.getContentPane().add(scrollPane_11);
		
				table_11 = new JTable();
				scrollPane_11.setViewportView(table_11);
		table_11.setModel(model_12);
		
		scrollPane_12 = new JScrollPane();
		scrollPane_12.setBounds(20, 289, 232, 130);
		frame.getContentPane().add(scrollPane_12);
		
		table_12 = new JTable();
		scrollPane_12.setViewportView(table_12);
		
		lblWeeklyReportsLegal = new JLabel("Weekly Reports Legal Opinions By Branch");
		lblWeeklyReportsLegal.setFont(new Font("Calibri", Font.BOLD, 18));
		lblWeeklyReportsLegal.setBounds(10, 248, 384, 23);
		frame.getContentPane().add(lblWeeklyReportsLegal);
		try {

			weeklyreport();
			dailyreportM();
			dailyreportTu();
			dailyreportW();
			dailyreportT();
			dailyreportF();
			dailyreport1();
			dailyreport2();
			dailyreport3();
			dailyreport4();
			dailyreport5();
			dailyreportLegalBranch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void weeklyreport() throws Exception {
		ws.xml result = this.ls.weeklyreportm();

		for (String[] row : result.t) {
			model.addRow(new Object[] { row[0], row[1] });
		}
		table.setModel(model);
	}

	private void dailyreportM() throws Exception {
		ws.xml result = this.ls.dailyreportMonday();

		for (String[] row : result.t) {
			model_2.addRow(new Object[] { row[0], row[1] });
		}
		table_1.setModel(model_2);
	}

	private void dailyreportTu() throws Exception {
		ws.xml result = this.ls.dailyreportTuesday();

		for (String[] row : result.t) {
			model_3.addRow(new Object[] { row[0], row[1] });
		}
		table_2.setModel(model_3);
	}

	private void dailyreportW() throws Exception {
		ws.xml result = this.ls.dailyreportWednesday();

		for (String[] row : result.t) {
			model_4.addRow(new Object[] { row[0], row[1] });
		}
		table_3.setModel(model_4);
	}

	private void dailyreportT() throws Exception {
		ws.xml result = this.ls.dailyreportThursday();

		for (String[] row : result.t) {
			model_5.addRow(new Object[] { row[0], row[1] });
		}
		table_4.setModel(model_5);
	}

	private void dailyreportF() throws Exception {
		ws.xml result = this.ls.dailyreportFriday();

		for (String[] row : result.t) {
			model_6.addRow(new Object[] { row[0], row[1] });
		}
		table_5.setModel(model_6);
	}

	private void dailyreport1() throws Exception {
		ws.xml result = this.ls.dailyreportCaseType1();

		for (String[] row : result.t) {
			model_7.addRow(new Object[] { "1", row[1] });
		}
		table_6.setModel(model_7);
	}

	private void dailyreport2() throws Exception {
		ws.xml result = this.ls.dailyreportCaseType2();

		for (String[] row : result.t) {
			model_8.addRow(new Object[] { "2", row[1] });
		}
		table_7.setModel(model_8);
	}

	private void dailyreport3() throws Exception {
		ws.xml result = this.ls.dailyreportCaseType3();

		for (String[] row : result.t) {
			model_9.addRow(new Object[] { "3", row[1] });
		}
		table_8.setModel(model_9);
	}

	private void dailyreport4() throws Exception {
		ws.xml result = this.ls.dailyreportCaseType4();

		for (String[] row : result.t) {
			model_10.addRow(new Object[] { "4", row[1] });
		}
		table_9.setModel(model_10);
	}

	private void dailyreport5() throws Exception {
		ws.xml result = this.ls.dailyreportCaseType5();

		for (String[] row : result.t) {
			model_11.addRow(new Object[] { "5", row[1] });
		}
		table_10.setModel(model_11);
	}
	
	private void dailyreportLegalBranch() throws Exception {
		ws.xml result = this.ls.dailyreportLegalBranch();

		for (String[] row : result.t) {
			model_13.addRow(new Object[] { row[0], row[1] });
		}
		table_12.setModel(model_13);
	}

}
