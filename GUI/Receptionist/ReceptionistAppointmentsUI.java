package wsclient;

import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JCheckBox;

public class ReceptionistAppointmentsUI {

	private JFrame frame;
	private ReceptionistClient reseptionist;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 * @throws MalformedURLException 
	 */
	public ReceptionistAppointmentsUI() throws MalformedURLException {
		this.reseptionist= new ReceptionistClient();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JFrame();
					frame.setBounds(100, 100, 306, 460);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(null);
					
					JLabel lblNewLabel = new JLabel("Lawyer:");
					lblNewLabel.setBounds(23, 58, 130, 44);
					frame.getContentPane().add(lblNewLabel);
					
					JLabel lblNewLabel_1 = new JLabel("Client/s:");
					lblNewLabel_1.setBounds(23, 101, 136, 52);
					frame.getContentPane().add(lblNewLabel_1);
					
					JLabel lblNewLabel_2 = new JLabel("Branch:");
					lblNewLabel_2.setBounds(23, 157, 136, 44);
					frame.getContentPane().add(lblNewLabel_2);
					
					comboBox = new JComboBox();
					comboBox.setBounds(86, 70, 151, 20);
					frame.getContentPane().add(comboBox);
					clawyer();
					
					comboBox_1 = new JComboBox();
					comboBox_1.setBounds(86, 117, 151, 20);
					frame.getContentPane().add(comboBox_1);
					cclient();
					
					
					
					//rdbtnDropin.get
					
					JLabel lblCreateAppointment = new JLabel("Create Appointment");
					lblCreateAppointment.setFont(new Font("Tahoma", Font.BOLD, 21));
					lblCreateAppointment.setBounds(23, 13, 226, 36);
					frame.getContentPane().add(lblCreateAppointment);
					if(globals.date!=null) {
					}
					if(globals.time!=null) {
					}
					JLabel lblDate_1 = new JLabel("Date:");
					lblDate_1.setBounds(23, 215, 56, 16);
					frame.getContentPane().add(lblDate_1);
					
					JLabel lblTime = new JLabel("Time:");
					lblTime.setBounds(23, 255, 56, 16);
					frame.getContentPane().add(lblTime);
					
					textField = new JTextField();
					textField.setBounds(86, 214, 151, 22);
					frame.getContentPane().add(textField);
					textField.setColumns(10);
					
					textField_1 = new JTextField();
					textField_1.setBounds(86, 252, 151, 22);
					frame.getContentPane().add(textField_1);
					textField_1.setColumns(10);
					
					textField_2 = new JTextField();
					textField_2.setBounds(86, 166, 151, 22);
					frame.getContentPane().add(textField_2);
					textField_2.setColumns(10);
					JCheckBox chckbxDropin = new JCheckBox("Drop-In");
					chckbxDropin.setBounds(23, 301, 113, 25);
					frame.getContentPane().add(chckbxDropin);
					
					
					JButton btnSubmit = new JButton("Submit");
					btnSubmit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String lawyerid = (String)comboBox.getSelectedItem();
							String clientid = (String)comboBox_1.getSelectedItem();
							String branch = textField_2.getText();
							String datee = textField.getText();
							String timee = textField_1.getText();
							boolean checked = chckbxDropin.isSelected();
							int dropin=0;
							if(checked) {
								dropin=1;
							}
							try {
								add(lawyerid,clientid,branch,datee,timee,dropin);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
						}
					});
					btnSubmit.setBounds(89, 349, 130, 37);
					frame.getContentPane().add(btnSubmit);
					
					
					
					//}
					//frame.pack();
		            frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	
		
	}
	public void add(String lawyerid,String clientid,String branch,String datee,String timee,int dropin) throws Exception {
		this.reseptionist.addAppointment(lawyerid,clientid,branch,datee,timee,dropin);
	}
	
	private void cclient() throws Exception {

		ws.xml c = this.reseptionist.cclient();
		for (String[] row : c.t) {
			String pat1 = row[0];
			comboBox_1.addItem(pat1);
		}
	}
	
	
	private void clawyer() throws Exception {

		ws.xml c = this.reseptionist.clawyer();
		for (String[] row : c.t) {
			String pat1 = row[0];
			comboBox.addItem(pat1);
		}
	}
}
