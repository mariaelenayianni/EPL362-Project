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

public class illegaluplaod {

	private JFrame frame;
	private Statement statement = null;
	private ResultSet resultSet = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					illegaluplaod window = new illegaluplaod();
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
	public illegaluplaod() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 369, 245);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIllegalMoneyLaundering = new JLabel("Illegal Money Laundering Upload");
		lblIllegalMoneyLaundering.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIllegalMoneyLaundering.setBounds(12, 13, 303, 32);
		frame.getContentPane().add(lblIllegalMoneyLaundering);
		
		JLabel lblClientid = new JLabel("ClientID:");
		lblClientid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClientid.setBounds(12, 80, 72, 16);
		frame.getContentPane().add(lblClientid);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Client ID"}));
		
		comboBox.setBounds(96, 78, 114, 22);
		frame.getContentPane().add(comboBox);
		
		MySQLAccess sqlcon = new MySQLAccess();
		try {
			statement = sqlcon.readDataBase();

			resultSet = statement.executeQuery("select * from client");
			while (resultSet.next()) {
				String pat = resultSet.getString("clientID");
				comboBox.addItem(pat);
			}
		//	comboBox.setSelectedIndex(int);

			comboBox.setSelectedItem("Select StrategyID");

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			sqlcon.close();
		}

		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MySQLAccess sqlcon = new MySQLAccess();
				try {
					int c_ID = comboBox.getSelectedIndex();
				
					statement=sqlcon.readDataBase();
					statement.executeUpdate(
							"UPDATE client c SET illegal=1 WHERE c.clientID='"+ c_ID + "'");

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					sqlcon.close();
				}
				
			}
		});
		
	
		btnSubmit.setBounds(218, 147, 97, 25);
		frame.getContentPane().add(btnSubmit);
	}

}
