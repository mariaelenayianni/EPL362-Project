package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ws.MySQLAccess;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

/**
 * This class represents an UI for recommendation
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class recommendationUI {

	private JFrame frame;
	private JTextField textField;
	private ReceptionistClient ls;
	/**
	 * Launch the application.
	 * Constructor
	 * @throws MalformedURLException 
	 */
	public recommendationUI() throws MalformedURLException {
		this.ls=new ReceptionistClient();
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
		MySQLAccess conn = new MySQLAccess();

		frame = new JFrame();
		frame.setBounds(100, 100, 351, 300);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblrecommendationUI = new JLabel("Insert Recommendation");
		lblrecommendationUI.setFont(new Font("Calibri", Font.BOLD, 18));
		lblrecommendationUI.setBounds(85, 13, 119, 23);
		frame.getContentPane().add(lblrecommendationUI);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblName.setBounds(10, 72, 46, 14);
		frame.getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(104, 69, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				String name=textField.getText();
				
				try {
					insertRecommendation(name);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSubmit.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSubmit.setBounds(104, 157, 89, 23);
		frame.getContentPane().add(btnSubmit);
	}
	
	/**
	 * This function requests for insert recommendation
	 * @param name
	 * @throws Exception
	 */
	private void insertRecommendation(String name) throws Exception {
		this.ls.insertRecommendation(name);
	}
	
	
}