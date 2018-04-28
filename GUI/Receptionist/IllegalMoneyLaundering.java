package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.net.MalformedURLException;
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
	private Statement statement = null;
	private ResultSet resultSet = null;
	private LegalRecordStaffClient ls;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 * 
	 * @throws MalformedURLException
	 */
	public IllegalMoneyLaundering() throws MalformedURLException {
		this.ls = new LegalRecordStaffClient();
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
		frame = new JFrame();
		frame.setBounds(100, 100, 347, 434);
		frame.setVisible(true);

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

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(23, 135, 235, 193);
	

		getClients();
		frame.getContentPane().add(lblNewLabel);

	}

	private void getClients() throws Exception {

		ws.xml result = this.ls.getClients();
		// int pol = 1;
		// model.addRow(new Object[] {"<ul>"});
		if (result.t.isEmpty()) {
			System.out.println("GAMOOOOOTO");
		}
		String msg = "<html><ul>";
		for (String[] row : result.t) {
			System.out.println("BENNWWW");
			msg += "<li>" + row[0] + "</li>";

		}
		msg += "</ul></html>";
		// model.addRow(new Object[] {"</ul>"});
		lblNewLabel.setText(msg);
	}
}
