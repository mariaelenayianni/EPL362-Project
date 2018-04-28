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

	private legalStaffClient ls;

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
	 * 
	 * @throws MalformedURLException
	 */
	public legalStaffUI() throws MalformedURLException {
		this.ls = new legalStaffClient();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblLegalStaff = new JLabel("Legal staff ");
		lblLegalStaff.setForeground(Color.BLUE);
		lblLegalStaff.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		lblLegalStaff.setBounds(153, 0, 93, 26);
		frame.getContentPane().add(lblLegalStaff);
		frame.setVisible(true);

		JLabel lblWarnings = new JLabel("Warnings:");
		lblWarnings.setBounds(333, 6, 63, 16);
		frame.getContentPane().add(lblWarnings);
		lblWarnings.setForeground(Color.RED);
		lblWarnings.setFont(new Font("Trebuchet MS", Font.BOLD, 13));

		JButton btnNewButton_2 = new JButton("Show Warnings");
		btnNewButton_2.setBounds(306, 236, 118, 23);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.setForeground(Color.RED);

		JLabel lblOptions = new JLabel("Options:");
		lblOptions.setBounds(10, 25, 52, 16);
		frame.getContentPane().add(lblOptions);
		lblOptions.setForeground(new Color(0, 128, 0));
		lblOptions.setFont(new Font("Trebuchet MS", Font.BOLD, 13));

		JButton btnNewButton = new JButton("Show Appoinments");
		btnNewButton.setBounds(0, 52, 123, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setForeground(new Color(0, 128, 0));

		JButton btnNewButton_1 = new JButton("Comment Request");
		btnNewButton_1.setBounds(0, 86, 121, 23);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setForeground(new Color(0, 128, 0));

		JButton btnShowEstimatedRisk = new JButton("Show Risk Indicator");
		btnShowEstimatedRisk.setBounds(0, 120, 127, 23);
		frame.getContentPane().add(btnShowEstimatedRisk);
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

		JButton btnRequestForChange = new JButton("Request for change");
		btnRequestForChange.setBounds(0, 164, 127, 23);
		frame.getContentPane().add(btnRequestForChange);
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
		
		JLabel label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(306, 25, 118, 200);
		label.setVisible(false);
		frame.getContentPane().add(label);

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contex="";
				try {
					contex=showWarnings();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!contex.equals("")) {
					label.setText(contex);
					label.setVisible(true);
				}

			}
		});

	}

	public String showWarnings() throws Exception {
		ws.xml warnings = this.ls.showWarnings(globals.lawyerID);

		String contex = null;
		if (!warnings.t.isEmpty()) {
			contex = "<html><ul>";
			for (String[] row : warnings.t) {
				contex += "<li>" + row[0] + " for client: " + row[1] + "</li>";
			}
			contex += "</ul></html>";

		} else {
			contex = "Nothing to show.";

		}

		return contex;
	}
}
