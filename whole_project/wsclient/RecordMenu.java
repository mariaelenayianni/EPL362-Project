package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;

/**
 * This class represents an UI for record menu
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class RecordMenu {

	private JFrame frmRecordMenu;
	private LegalRecordStaffClient ls;

	/**
	 * Launch the application.
	 * Constructor
	 * 
	 * @throws MalformedURLException
	 */
	public RecordMenu() throws MalformedURLException {

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
	 */
	private void initialize() {
		frmRecordMenu = new JFrame();
		frmRecordMenu.setTitle("Legal Staff Record Menu");
		frmRecordMenu.setBounds(100, 100, 562, 387);
		frmRecordMenu.setVisible(true);
		frmRecordMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRecordMenu.getContentPane().setLayout(null);

		JButton btnViewTransaction = new JButton("View Transaction");
		btnViewTransaction.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {

				try {
					new viewTransaction();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		btnViewTransaction.setBounds(12, 22, 151, 36);
		frmRecordMenu.getContentPane().add(btnViewTransaction);

		JButton btnIllegalMoneyLowndery = new JButton("Illegal Money Lowndery");
		btnIllegalMoneyLowndery.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					new IllegalMoneyLaundering();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnIllegalMoneyLowndery.setBounds(186, 22, 176, 36);
		frmRecordMenu.getContentPane().add(btnIllegalMoneyLowndery);

		JButton btnDisagreements = new JButton("Disagreements");
		btnDisagreements.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

				try {
					new DisagreementsUpload();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnDisagreements.setBounds(374, 22, 138, 36);
		frmRecordMenu.getContentPane().add(btnDisagreements);

		JButton btnTransmissionOfPersonal = new JButton("Transmission of Personal Indormation");
		btnTransmissionOfPersonal.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					new changePersonalInfo();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnTransmissionOfPersonal.setBounds(186, 71, 255, 36);
		frmRecordMenu.getContentPane().add(btnTransmissionOfPersonal);

		JButton btnAddClient = new JButton("Add Client");
		btnAddClient.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				try {
					new AddClient();

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// ac.main(null);

			}
		});
		btnAddClient.setBounds(12, 71, 151, 36);
		frmRecordMenu.getContentPane().add(btnAddClient);

		JButton btnIllegalMoneyLaundering = new JButton("Illegal Money Laundering Upload");
		btnIllegalMoneyLaundering.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					new illegaluplaod();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnIllegalMoneyLaundering.setBounds(12, 119, 243, 36);
		frmRecordMenu.getContentPane().add(btnIllegalMoneyLaundering);

		JButton btnNewButton = new JButton("Create Warning Letter");
		btnNewButton.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					CreateWarnings cw = new CreateWarnings();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(267, 120, 174, 36);
		frmRecordMenu.getContentPane().add(btnNewButton);

		JButton btnShowClientsInfo = new JButton("Show Client's Info");
		btnShowClientsInfo.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					new ShowInfoClient();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		btnShowClientsInfo.setBounds(12, 168, 163, 36);
		frmRecordMenu.getContentPane().add(btnShowClientsInfo);

		JButton btnDeleteClient = new JButton("Delete Client");
		btnDeleteClient.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					new DeleteClient();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnDeleteClient.setBounds(186, 168, 155, 36);
		frmRecordMenu.getContentPane().add(btnDeleteClient);

		JButton btnInsertStrategy = new JButton("Insert Strategy");
		btnInsertStrategy.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					new InsertStrategy();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnInsertStrategy.setBounds(353, 169, 138, 36);
		frmRecordMenu.getContentPane().add(btnInsertStrategy);
		
		JButton btnInsertLegalOpinion = new JButton("Insert Legal Opinion");
		btnInsertLegalOpinion.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					new InsertLegalOpinions();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnInsertLegalOpinion.setBounds(12, 222, 163, 36);
		frmRecordMenu.getContentPane().add(btnInsertLegalOpinion);

	}
}
