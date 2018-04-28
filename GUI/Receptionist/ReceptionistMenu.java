package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;

public class ReceptionistMenu {

	private JFrame frmReceptionist;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceptionistMenu window = new ReceptionistMenu();
					window.frmReceptionist.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReceptionistMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReceptionist = new JFrame();
		frmReceptionist.setTitle("Receptionist");
		frmReceptionist.setBounds(100, 100, 450, 300);
		frmReceptionist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReceptionist.getContentPane().setLayout(null);
		
		JButton btnAddAppointment = new JButton("Add Appointment");
		btnAddAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ReceptionistAppointmentsUI();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAddAppointment.setBounds(12, 13, 139, 35);
		frmReceptionist.getContentPane().add(btnAddAppointment);
		
		JButton btnNewButton = new JButton("Insert Attendance");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					new Attendance();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(163, 15, 158, 30);
		frmReceptionist.getContentPane().add(btnNewButton);
		
		JButton btnAddRecommendation = new JButton("Add recommendation");
		btnAddRecommendation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new recommendationUI();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAddRecommendation.setBounds(188, 59, 158, 30);
		frmReceptionist.getContentPane().add(btnAddRecommendation);
		
		JButton btnViewTransactions = new JButton("View Transactions");
		btnViewTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new viewReceptionistTransaction();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnViewTransactions.setBounds(12, 59, 158, 30);
		frmReceptionist.getContentPane().add(btnViewTransactions);
		
		JButton btnDropin = new JButton("Drop-In");
		btnDropin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					new DropInUI();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDropin.setBounds(12, 102, 97, 25);
		frmReceptionist.getContentPane().add(btnDropin);
		
		JButton btnViewAttendance = new JButton("View Attendance");
		btnViewAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new viewAttendance();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnViewAttendance.setBounds(121, 102, 139, 25);
		frmReceptionist.getContentPane().add(btnViewAttendance);
		
		JButton btnShowNotUpdated = new JButton("Show not updated appointments");
		btnShowNotUpdated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new updateAttended();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowNotUpdated.setBounds(12, 140, 248, 25);
		frmReceptionist.getContentPane().add(btnShowNotUpdated);
	}
}
