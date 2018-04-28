package wsclient;

import java.awt.EventQueue;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DropInUI {

	private JFrame frame;
	private ReceptionistClient ls;
	private JComboBox comboBox ;
	private JLabel label;
	/**
	 * Launch the application.
	 * 
	 * @throws MalformedURLException
	 */
	public DropInUI() throws MalformedURLException {
		this.ls = new ReceptionistClient();
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
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Drop In");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(147, 13, 112, 25);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblClientId = new JLabel("Client ID:");
		lblClientId.setBounds(46, 62, 71, 25);
		frame.getContentPane().add(lblClientId);

		comboBox = new JComboBox();
		comboBox.setBounds(31, 100, 112, 22);
		cclient();
		frame.getContentPane().add(comboBox);
		
		label=new JLabel("");
		label.setBounds(12, 191, 408, 25);
		frame.getContentPane().add(label);

		

	
		JButton btnShowPreviousAppointment = new JButton("Show Previous Appointment");
		btnShowPreviousAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cID = Integer.parseInt((String)comboBox.getSelectedItem());
				try {
					showPreviousAttendance(cID);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowPreviousAppointment.setBounds(90, 130, 237, 36);
		frame.getContentPane().add(btnShowPreviousAppointment);
	}

	
	private void cclient() throws Exception {

		ws.xml c = this.ls.cclient();
		for (String[] row : c.t) {
			String pat1 = row[0];
			comboBox.addItem(pat1);
		}
	}
	public void showPreviousAttendance(int cID) throws Exception {
		ws.xml c = this.ls.showPreviousAttendance(cID);
		int size=c.t.size()-2;
		String lblMsg= "Last attendance was: "+c.t.get(size)[0];
		label.setText(lblMsg);
		

	}
	

}
