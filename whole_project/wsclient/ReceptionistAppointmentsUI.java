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

/**
 * This class represents an UI for Receptionist Appointment task
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class ReceptionistAppointmentsUI {

	private JFrame frame;
	private ReceptionistClient reseptionist;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;

	/**
	 * Launch the application.
	 * Constructor
	 * 
	 * @throws MalformedURLException
	 */
	public ReceptionistAppointmentsUI() throws MalformedURLException {
		this.reseptionist = new ReceptionistClient();
		EventQueue.invokeLater(new Runnable() {
			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				try {
					frame = new JFrame();
					frame.setBounds(100, 100, 381, 460);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

					comboBox_2 = new JComboBox();
					comboBox_2.setBounds(86, 168, 151, 22);
					frame.getContentPane().add(comboBox_2);
					cbranch();

					JLabel lblCreateAppointment = new JLabel("Create Appointment");
					lblCreateAppointment.setFont(new Font("Tahoma", Font.BOLD, 21));
					lblCreateAppointment.setBounds(23, 13, 226, 36);
					frame.getContentPane().add(lblCreateAppointment);
					
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
					JCheckBox chckbxDropin = new JCheckBox("Drop-In");
					chckbxDropin.setBounds(23, 301, 113, 25);
					frame.getContentPane().add(chckbxDropin);

					JButton btnSubmit = new JButton("Submit");
					btnSubmit.addActionListener(new ActionListener() {
						/* (non-Javadoc)
						 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
						 */
						public void actionPerformed(ActionEvent e) {
							String lawyerid = (String) comboBox.getSelectedItem();
							String clientid = (String) comboBox_1.getSelectedItem();
							String branch = (String) comboBox_2.getSelectedItem();
							String datee = textField.getText();
							String timee = textField_1.getText();
							boolean checked = chckbxDropin.isSelected();
							int dropin = 0;
							if (checked) {
								dropin = 1;
							}
							try {
								add(lawyerid, clientid, branch, datee, timee, dropin);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					});
					btnSubmit.setBounds(89, 349, 130, 37);
					frame.getContentPane().add(btnSubmit);

					JLabel lblEg = new JLabel("E.g. 2018-01-01");
					lblEg.setBounds(249, 215, 102, 21);
					frame.getContentPane().add(lblEg);

					JLabel lblEg_1 = new JLabel("E.g. 16:30:00");
					lblEg_1.setBounds(249, 255, 91, 16);
					frame.getContentPane().add(lblEg_1);

					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * This function requests to insert an appointment
	 * @param lawyerid
	 * @param clientid
	 * @param branch
	 * @param datee
	 * @param timee
	 * @param dropin
	 * @throws Exception
	 */
	public void add(String lawyerid, String clientid, String branch, String datee, String timee, int dropin)
			throws Exception {
		this.reseptionist.addAppointment(lawyerid, clientid, branch, datee, timee, dropin);
	}

	/**
	 * This function requests for retrieve the clients 
	 * @throws Exception
	 */
	private void cclient() throws Exception {

		ws.xml c = this.reseptionist.cclient();
		for (String[] row : c.t) {
			String pat1 = row[0];
			comboBox_1.addItem(pat1);
		}
	}

	/**
	 * This function requests for retrieve the lawyers
	 * @throws Exception
	 */
	private void clawyer() throws Exception {

		ws.xml c = this.reseptionist.clawyer();
		for (String[] row : c.t) {
			String pat1 = row[0];
			comboBox.addItem(pat1);
		}
	}

	/**
	 * This function requests to retrieve the branches
	 * @throws Exception
	 */
	private void cbranch() throws Exception {

		ws.xml c = this.reseptionist.cbranch();
		for (String[] row : c.t) {
			String pat1 = row[0];
			comboBox_2.addItem(pat1);
		}
	}
}
