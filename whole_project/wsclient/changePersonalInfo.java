package wsclient;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import ws.MySQLAccess;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class presents an UI for change personal info
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class changePersonalInfo {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField;
	private LegalRecordStaffClient ls;
	private DefaultTableModel model = new DefaultTableModel(new String[] { "RequestID", "clientID", "Field", "Info" },
			0);

	/**
	 * Launch the application.
	 * 
	 * @throws MalformedURLException
	 */
	public changePersonalInfo() throws MalformedURLException {
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
	/**
	 * @throws Exception
	 */
	/**
	 * @throws Exception
	 */
	private void initialize() throws Exception {

		frame = new JFrame();
		frame.setBounds(100, 100, 601, 386);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblRequestForChanging = new JLabel("Request for changing Personal Info");
		lblRequestForChanging.setFont(new Font("Calibri", Font.BOLD, 16));
		lblRequestForChanging.setBounds(10, 11, 279, 25);
		frame.getContentPane().add(lblRequestForChanging);

		textField = new JTextField();
		textField.setBounds(100, 264, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 47, 511, 206);
		frame.getContentPane().add(scrollPane);
		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String k = model.getValueAt(selectedRow, 0).toString();
				textField.setText(model.getValueAt(selectedRow, 0).toString());

			}
		});
		scrollPane.setViewportView(table);

		takeAction();

		JLabel lblRequestId = new JLabel("Request ID:");
		lblRequestId.setBounds(10, 264, 73, 14);
		frame.getContentPane().add(lblRequestId);

		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				int rID;
				String reqID;
				reqID = textField.getText();

				rID = Integer.parseInt(reqID);
				try {
					UpdateRequest(rID);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		btnAccept.setBounds(10, 302, 89, 23);
		frame.getContentPane().add(btnAccept);

		JButton btnDecline = new JButton("Decline");
		btnDecline.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {

				int rID;
				String reqID;
				reqID = textField.getText();

				rID = Integer.parseInt(reqID);

				try {
					DeclineRequest(rID);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnDecline.setBounds(121, 302, 89, 23);
		frame.getContentPane().add(btnDecline);


	}

	/**
	 * This function takes action
	 * @throws Exception
	 */
	private void takeAction() throws Exception {

		ws.xml result = this.ls.takeAction();
		// int pol = 1;
		for (String[] row : result.t) {

		
			model.addRow(new Object[] { row[0], row[1], row[2], row[3] });

		}

		table.setModel(model);

		// model.addRow(new Object[] { a, b, c, d });

	}

	/**
	 * This function request for update
	 * @param rID
	 * @throws Exception
	 */
	private void UpdateRequest(int rID) throws Exception {

		this.ls.UpdateRequest(rID);

	}

	/**
	 * This action requests for decline
	 * @param rID
	 * @throws Exception
	 */
	private void DeclineRequest(int rID) throws Exception {
		
		this.ls.DeclineRequest(rID);

		
		

	}

}