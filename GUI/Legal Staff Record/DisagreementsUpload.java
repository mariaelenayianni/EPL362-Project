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

public class DisagreementsUpload {

	private JFrame frame;
	private JTextField textField;
	private Statement statement = null;
	private ResultSet resultSet = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisagreementsUpload window = new DisagreementsUpload();
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
	public DisagreementsUpload() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 341, 303);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUploadDisagreements = new JLabel("Insert Disagreements");
		lblUploadDisagreements.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUploadDisagreements.setBounds(12, 13, 264, 23);
		frame.getContentPane().add(lblUploadDisagreements);

		JLabel lblClientid = new JLabel("ClientID:");
		lblClientid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClientid.setBounds(12, 84, 72, 23);
		frame.getContentPane().add(lblClientid);

		textField = new JTextField();
		textField.setBounds(118, 85, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblStrategyid = new JLabel("StrategyID:");
		lblStrategyid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStrategyid.setBounds(12, 146, 82, 23);
		frame.getContentPane().add(lblStrategyid);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select S_ID"}));

		MySQLAccess sqlcon = new MySQLAccess();
		try {
			statement = sqlcon.readDataBase();

			resultSet = statement.executeQuery("select * from strategy");
			while (resultSet.next()) {
				String pat = resultSet.getString("strategyID");
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

		comboBox.setBounds(126, 147, 108, 22);
		frame.getContentPane().add(comboBox);

		JButton btnSumbit = new JButton("Submit");

		btnSumbit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				MySQLAccess sqlcon = new MySQLAccess();
				try {
					int strategyID = comboBox.getSelectedIndex();
					String clientID = textField.getText();
					int c_ID = Integer.parseInt(clientID);
					
					Statement pst = sqlcon.readDataBase();

					String query = "INSERT INTO disaggrements VALUES (default,'" + c_ID + "', '" + strategyID + "')";
					pst.executeUpdate(query);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					sqlcon.close();
				}
			}
		});
		btnSumbit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSumbit.setBounds(177, 198, 121, 25);
		frame.getContentPane().add(btnSumbit);

	}
}
