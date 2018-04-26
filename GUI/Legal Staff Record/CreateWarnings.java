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
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CreateWarnings {

	private JFrame frame;
	private JTextField textField_2;
	private Statement statement = null;
	private ResultSet resultSet = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateWarnings window = new CreateWarnings();
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
	public CreateWarnings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 435, 376);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCreateWarning = new JLabel("Create Warning");
		lblCreateWarning.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCreateWarning.setBounds(26, 13, 243, 35);
		frame.getContentPane().add(lblCreateWarning);
		
		JLabel lblLawyerid = new JLabel("LawyerID:");
		lblLawyerid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLawyerid.setBounds(26, 84, 96, 25);
		frame.getContentPane().add(lblLawyerid);
		
		JLabel lblClientid = new JLabel("ClientID:");
		lblClientid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClientid.setBounds(26, 128, 96, 25);
		frame.getContentPane().add(lblClientid);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(26, 166, 96, 25);
		frame.getContentPane().add(lblDescription);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(26, 204, 96, 25);
		frame.getContentPane().add(label);
		
		textField_2 = new JTextField();
		textField_2.setBounds(134, 168, 174, 72);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select LawyersID"}));
		comboBox.setBounds(134, 86, 135, 22);
		frame.getContentPane().add(comboBox);
		
		MySQLAccess sqlcon = new MySQLAccess();
		try {
			statement = sqlcon.readDataBase();

			resultSet = statement.executeQuery("select * from lawyer");
			while (resultSet.next()) {
				String pat = resultSet.getString("lawyerID");
				comboBox.addItem(pat);
			}
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			sqlcon.close();
		}

		
		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select ClientID"}));
		comboBox_1.setBounds(134, 130, 135, 22);
		frame.getContentPane().add(comboBox_1);
		
		MySQLAccess sqlcon_2 = new MySQLAccess();
		try {
			statement = sqlcon_2.readDataBase();

			resultSet = statement.executeQuery("select * from client");
			while (resultSet.next()) {
				String pat1 = resultSet.getString("clientID");
				comboBox_1.addItem(pat1);
			}
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			sqlcon_2.close();
		}

		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MySQLAccess sqlcon = new MySQLAccess();
				try {
					String c_ID = (String)comboBox_1.getSelectedItem();
					String l_ID = (String)comboBox.getSelectedItem();
					
					String description = textField_2.getText();
					
					System.out.println(c_ID + "        "+ l_ID);
					Statement pst=sqlcon.readDataBase();
					
					String query="INSERT INTO warnings VALUES (default,"+l_ID+", "+ c_ID+",'"+description+"',"+0+")";
					pst.executeUpdate(query);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					sqlcon.close();
				}
				
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(263, 266, 104, 35);
		frame.getContentPane().add(btnSubmit);

	}

}
