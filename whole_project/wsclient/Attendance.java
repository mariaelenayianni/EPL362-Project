package wsclient;

import java.awt.EventQueue;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class presents the attendance of clients
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class Attendance {

	private JFrame frame;
	private ReceptionistClient rc;
	private JComboBox comboBox;
	/**
	 * Launch the application.
	 * Constructor
	 * @throws MalformedURLException 
	 */
	public Attendance() throws MalformedURLException {
		this.rc = new ReceptionistClient();
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
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 359, 269);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Attendance");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(28, 25, 151, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("AppointmentID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(28, 83, 115, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setBounds(155, 87, 93, 22);
		frame.getContentPane().add(comboBox);
		getAppointmentID();
		JButton btnAttended = new JButton("Attended");
		btnAttended.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				int a_ID = Integer.parseInt((String)comboBox.getSelectedItem());
				try {
					came(a_ID);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAttended.setBounds(28, 136, 115, 25);
		frame.getContentPane().add(btnAttended);
		
		JButton btnNotAttended = new JButton("Not Attended");
		btnNotAttended.setBounds(175, 136, 115, 25);
		frame.getContentPane().add(btnNotAttended);
	}
	
	/**
	 * This function requests for the appointments ids
	 * @throws Exception
	 */
	private void getAppointmentID() throws Exception {

		ws.xml c = this.rc.getAppointmentID();
		for (String[] row : c.t) {
			String pat1 = row[0];
			comboBox.addItem(pat1);
		}
		
	}
	
	/**
	 * This function requests if came
	 * @param a_ID
	 * @throws Exception
	 */
	public void came(int a_ID) throws Exception {
		this.rc.came(a_ID);
	}
}
