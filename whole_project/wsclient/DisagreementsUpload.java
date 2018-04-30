package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ws.MySQLAccess;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * This class represents an UI for didagreements upload
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class DisagreementsUpload {

	private JFrame frame;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private LegalRecordStaffClient ls;
	JComboBox comboBox;
	JComboBox comboBox_1;
	/**
	 * Launch the application.
	 * @throws MalformedURLException 
	 */
	public DisagreementsUpload() throws MalformedURLException {
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
	 * @throws Exception 
	 */
	/**
	 * @throws Exception
	 */
	/**
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 341, 303);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUploadDisagreements = new JLabel("Insert Disagreements");
		lblUploadDisagreements.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUploadDisagreements.setBounds(12, 13, 264, 23);
		frame.getContentPane().add(lblUploadDisagreements);

		JLabel lblClientid = new JLabel("ClientID:");
		lblClientid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClientid.setBounds(12, 84, 72, 23);
		frame.getContentPane().add(lblClientid);

		JLabel lblStrategyid = new JLabel("StrategyID:");
		lblStrategyid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStrategyid.setBounds(12, 146, 82, 23);
		frame.getContentPane().add(lblStrategyid);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select S_ID"}));
		comboBox.setSelectedItem("Select StrategyID");
		
		viewStrategy();

		comboBox.setBounds(126, 147, 108, 22);
		frame.getContentPane().add(comboBox);
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(126, 85, 108, 22);
		
		viewClient();
		frame.getContentPane().add(comboBox_1);

		JButton btnSumbit = new JButton("Submit");

		btnSumbit.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				int strategyID =  Integer.parseInt( (String) comboBox.getSelectedItem());
				int c_ID = Integer.parseInt( (String) comboBox_1.getSelectedItem());
				
				
				try {
					insertDis(c_ID,strategyID);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSumbit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSumbit.setBounds(177, 198, 121, 25);
		frame.getContentPane().add(btnSumbit);
		
	
	}
	
	
	/**
	 * This function requests for view client
	 * @throws Exception
	 */
	private void viewClient() throws Exception {

		ws.xml c = this.ls.cclient();
		for (String[] row : c.t) {
			String pat1 = row[0];
			comboBox_1.addItem(pat1);
		}
	}
	/**
	 * This function requests for view strategies
	 * @throws Exception
	 */
	private void viewStrategy() throws Exception {

		ws.xml c = this.ls.viewStrategy();
		for (String[] row : c.t) {
			String pat1 = row[0];
			comboBox.addItem(pat1);
		}
	}
	/**
	 * This function requests for insert disagreements
	 * @param cID
	 * @param strategyID
	 * @throws Exception
	 */
	private void insertDis(int cID, int strategyID) throws Exception {
		this.ls.insertDis(cID,strategyID);
	}
}
