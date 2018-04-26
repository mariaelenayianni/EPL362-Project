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
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class changePersonalInfo {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changePersonalInfo window = new changePersonalInfo();
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
	public changePersonalInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MySQLAccess conn = new MySQLAccess();
		frame = new JFrame();
		frame.setBounds(100, 100, 601, 386);
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
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				String k=model.getValueAt(selectedRow, 0).toString();
				textField.setText(model.getValueAt(selectedRow, 0).toString());

			}
		});
		scrollPane.setViewportView(table);

		DefaultTableModel model = new DefaultTableModel(new String[]{"RequestID", "clientID", "Field", "Info"}, 0);


		try {
			Statement pst=conn.readDataBaseTransac();

			String query="select * from Requests WHERE `done`=0";

			ResultSet rs=pst.executeQuery(query);
			ResultSetMetaData metaData = rs.getMetaData();



			while(rs.next())
			{

				String a = rs.getString("rID");
				String b = rs.getString("clientID");
				String c = rs.getString("field");
				String d = rs.getString("info");


				model.addRow(new Object[]{a, b, c, d});
			}

			table.setModel(model);

			JLabel lblRequestId = new JLabel("Request ID:");
			lblRequestId.setBounds(10, 264, 73, 14);
			frame.getContentPane().add(lblRequestId);




			JButton btnAccept = new JButton("Accept");
			btnAccept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int rID;
					String reqID;
					reqID=textField.getText();

					rID=Integer.parseInt(reqID);

					String query="SELECT * from `Requests` where `rID`="+ rID;


					try {
						ResultSet rs=pst.executeQuery(query);
						ResultSetMetaData metaData = rs.getMetaData();
						int wrong=0;
						String query3=null;
						int arith;

						while(rs.next())
						{

							String a = rs.getString("rID");
							String b = rs.getString("clientID");
							String c = rs.getString("field");
							String d = rs.getString("info");
							int cID=Integer.parseInt(b);

							if(c.equals("name")) {

								query3="UPDATE `client` SET name= `"+d+"` where `clientID`="+ cID;
							}
							else if(c.equals("lastname")) {
								query3="UPDATE `client` SET `lastname`= '"+d+"' where `clientID`="+ cID;
							}
							else if(c.equals("personalinfo")) {

								query3="UPDATE `client` SET personalinfo= `"+d+"` where `clientID`="+ cID;
							}
							else if(c.equals("risk")) {

								arith=Integer.parseInt(d);
								query3="UPDATE `client` SET risk= `"+arith+"` where `clientID`="+ cID;
							}
							else if(c.equals("illegal")) {
								arith=Integer.parseInt(d);
								query3="UPDATE `client` SET illegal= `"+arith+"` where `clientID`="+ cID;

							}
							else if(c.equals("changed")) {
								arith=Integer.parseInt(d);
								query3="UPDATE `client` SET changed= `"+arith+"` where `clientID`="+ cID;
							}
							else if(c.equals("recommendations")){
								arith=Integer.parseInt(d);
								query3="UPDATE `client` SET recommendations= `"+arith+"` where `clientID`="+ cID;
							}
							else {

								wrong=1;
							}
							System.out.println("efkika");

							if(wrong==0) {
								try {
									int rs2=pst.executeUpdate(query3);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						}

						//



					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}



					String query3="UPDATE `requests` SET `done`= 1 where `rID`="+ rID;
					try {
						int rs2=pst.executeUpdate(query3);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}
			});
			btnAccept.setBounds(10, 302, 89, 23);
			frame.getContentPane().add(btnAccept);

			JButton btnDecline = new JButton("Decline");
			btnDecline.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int rID;
					String reqID;
					reqID=textField.getText();

					rID=Integer.parseInt(reqID);

					String query="UPDATE `requests` SET `done`= 1 where `rID`="+ rID;

					try {
						int rs=pst.executeUpdate(query);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnDecline.setBounds(121, 302, 89, 23);
			frame.getContentPane().add(btnDecline);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}
}
