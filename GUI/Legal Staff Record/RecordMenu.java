package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecordMenu {

	private JFrame frmRecordMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecordMenu window = new RecordMenu();
					window.frmRecordMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RecordMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRecordMenu = new JFrame();
		frmRecordMenu.setTitle("Record Menu");
		frmRecordMenu.setBounds(100, 100, 562, 387);
		frmRecordMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRecordMenu.getContentPane().setLayout(null);

		JButton btnViewTransaction = new JButton("View Transaction");
		btnViewTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ViewTransaction vt = new ViewTransaction(); vt.invokeSomeMethod();
				 */
			}
		});
		btnViewTransaction.setBounds(12, 13, 151, 45);
		frmRecordMenu.getContentPane().add(btnViewTransaction);

		JButton btnIllegalMoneyLowndery = new JButton("Illegal Money Lowndery");
		btnIllegalMoneyLowndery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IllegalMoneyLaundering.main(null);

			}
		});
		btnIllegalMoneyLowndery.setBounds(186, 13, 176, 45);
		frmRecordMenu.getContentPane().add(btnIllegalMoneyLowndery);

		JButton btnDisagreements = new JButton("Disagreements");
		btnDisagreements.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Disagreements diss = new Disagreements(); diss.invokeSomeMethod();
				 */
			}
		});
		btnDisagreements.setBounds(374, 13, 138, 45);
		frmRecordMenu.getContentPane().add(btnDisagreements);

		JButton btnTransmissionOfPersonal = new JButton("Transmission of Personal Indormation");
		btnTransmissionOfPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * TransmissionPersonalInformation tpi = new TransmissionPersonalInformation();
				 * tpi.invokeSomeMethod();
				 */
			}
		});
		btnTransmissionOfPersonal.setBounds(147, 71, 255, 36);
		frmRecordMenu.getContentPane().add(btnTransmissionOfPersonal);
	}
}
