package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class legalStaffUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					legalStaffUI window = new legalStaffUI();
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
	public legalStaffUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0,0,255,30));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 0, 30));
		panel.setBounds(296, 0, 138, 261);
		frame.getContentPane().add(panel);
		
		
		JLabel lblWarnings = new JLabel("Warnings:");
		lblWarnings.setForeground(Color.RED);
		lblWarnings.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		panel.add(lblWarnings);
		
		JLabel lblLegalStaff = new JLabel("Legal staff ");
		lblLegalStaff.setForeground(Color.BLUE);
		lblLegalStaff.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		lblLegalStaff.setBounds(153, 0, 93, 26);
		frame.getContentPane().add(lblLegalStaff);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 0, 30));
		panel_1.setBounds(0, 25, 151, 236);
		frame.getContentPane().add(panel_1);
		frame.setVisible(true);
		
		JLabel lblOptions = new JLabel("Options:");
		lblOptions.setForeground(new Color(0, 128, 0));
		lblOptions.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		panel_1.add(lblOptions);
		
		JButton btnNewButton = new JButton("Show Appoinments");
		btnNewButton.setForeground(new Color(0, 128, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new AppointmentWindow();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Comment Request");
		btnNewButton_1.setForeground(new Color(0, 128, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new addCommentUI();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(btnNewButton_1);
		
		JButton btnShowEstimatedRisk = new JButton("Show Risk Indicator");
		btnShowEstimatedRisk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new RiskIndicator();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowEstimatedRisk.setForeground(new Color(0, 128, 0));
		panel_1.add(btnShowEstimatedRisk);
		
		JButton btnRequestForChange = new JButton("Request for change");
		btnRequestForChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new requestForChangeUI();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRequestForChange.setForeground(new Color(0, 128, 0));
		panel_1.add(btnRequestForChange);
	}
}
