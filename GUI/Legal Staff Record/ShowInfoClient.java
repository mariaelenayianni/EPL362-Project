package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ws.MySQLAccess;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ShowInfoClient {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowInfoClient window = new ShowInfoClient();
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
	public ShowInfoClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MySQLAccess conn = new MySQLAccess();

	
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblShowClientsInfo = new JLabel("Show Client's Info");
		lblShowClientsInfo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblShowClientsInfo.setBounds(10, 11, 157, 23);
		frame.getContentPane().add(lblShowClientsInfo);
		
		JLabel lblClientId = new JLabel("Client ID:");
		lblClientId.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblClientId.setBounds(10, 45, 66, 23);
		frame.getContentPane().add(lblClientId);
		
		textField = new JTextField();
		textField.setBounds(116, 45, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 93, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 132, 77, 14);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 168, 46, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPersonalInfo = new JLabel("Personal Info");
		lblPersonalInfo.setBounds(10, 210, 77, 14);
		frame.getContentPane().add(lblPersonalInfo);
		
		JLabel lblRisk = new JLabel("Risk");
		lblRisk.setBounds(10, 235, 46, 14);
		frame.getContentPane().add(lblRisk);
		
		JLabel lblIllegalActivity = new JLabel("Illegal Activity");
		lblIllegalActivity.setBounds(10, 274, 86, 14);
		frame.getContentPane().add(lblIllegalActivity);
		
		JLabel lblChanged = new JLabel("Changed");
		lblChanged.setBounds(10, 299, 66, 14);
		frame.getContentPane().add(lblChanged);
		
		JLabel lblReadOnly = new JLabel("Read Only");
		lblReadOnly.setBounds(10, 335, 77, 14);
		frame.getContentPane().add(lblReadOnly);
		
		JLabel lblRecommendations = new JLabel("Recommendations");
		lblRecommendations.setBounds(10, 371, 115, 17);
		frame.getContentPane().add(lblRecommendations);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id;
				int id_client;
				int wrong=0;
				id=textField.getText();
				if(id.equals("")) {
					wrong=1;
				}
				if(wrong==0) {
					id_client=Integer.parseInt(id);
					try {
						Statement pst=conn.readDataBaseTransac();
						String query="select * from client where clientID="+id_client;

						ResultSet rs=pst.executeQuery(query);
						ResultSetMetaData metaData = rs.getMetaData();
						
						while(rs.next())
						{

						    String a = rs.getString("name");
						    String b = rs.getString("lastname");
						    String c = rs.getString("email");
						    String d = rs.getString("personalinfo");
						    String l = rs.getString("risk");
						    String f = rs.getString("illegal");
						    String h = rs.getString("changed");
						    String j = rs.getString("readonly");
						    String k = rs.getString("recommendations");
						    
						    textField_1.setText(a);
						    textField_2.setText(b);
						    textField_3.setText(c);
						    textField_4.setText(d);
						    textField_5.setText(l);
						    textField_6.setText(f);
						    textField_7.setText(h);
						    textField_8.setText(j);
						    textField_9.setText(k);

						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSubmit.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSubmit.setBounds(236, 45, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		textField_1 = new JTextField();
		textField_1.setBounds(116, 90, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);

		
		textField_2 = new JTextField();
		textField_2.setBounds(116, 129, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setEditable(false);

		
		textField_3 = new JTextField();
		textField_3.setBounds(116, 165, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setEditable(false);

		
		textField_4 = new JTextField();
		textField_4.setBounds(116, 207, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		textField_4.setEditable(false);

		
		textField_5 = new JTextField();
		textField_5.setBounds(116, 232, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		textField_5.setEditable(false);

		
		textField_6 = new JTextField();
		textField_6.setBounds(116, 271, 86, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		textField_6.setEditable(false);

		
		textField_7 = new JTextField();
		textField_7.setBounds(116, 296, 86, 20);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		textField_7.setEditable(false);

		
		textField_8 = new JTextField();
		textField_8.setBounds(116, 332, 86, 20);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		textField_8.setEditable(false);

		
		textField_9 = new JTextField();
		textField_9.setBounds(116, 369, 86, 20);
		frame.getContentPane().add(textField_9);
		textField_9.setColumns(10);
		textField_9.setEditable(false);

	}

}
