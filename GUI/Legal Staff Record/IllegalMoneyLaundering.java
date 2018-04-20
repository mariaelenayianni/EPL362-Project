package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;

public class IllegalMoneyLaundering {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IllegalMoneyLaundering window = new IllegalMoneyLaundering();
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
	public IllegalMoneyLaundering() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 619, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIllegalMoneyLaundering = new JLabel("Illegal Money Laundering");
		lblIllegalMoneyLaundering.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIllegalMoneyLaundering.setBounds(23, 13, 293, 41);
		frame.getContentPane().add(lblIllegalMoneyLaundering);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblResults.setBounds(23, 87, 75, 32);
		frame.getContentPane().add(lblResults);
		
		table = new JTable();
		table.setBounds(22, 134, 402, 200);
		frame.getContentPane().add(table);
	}
}
