package wsclient;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;

public class requestForChangeUI {

	private JFrame frame;
	private static boolean clientSelected;
	private static boolean fieldSelected;
	private static boolean fieldIsEmpty;
	private String[] fields = { "Choose field", "username", "name", "lastname", "personal info", "email" };
	private legalStaffClient ls;
	private JComboBox comboBox;
	private JComboBox comboBox1;
	private JTextField textField;

	/**
	 * Launch the application.
	 * @throws MalformedURLException 
	 */

	public requestForChangeUI() throws MalformedURLException {
		this.ls= new legalStaffClient();
		requestForChangeUI.clientSelected = false;
		requestForChangeUI.fieldSelected = false;
		requestForChangeUI.fieldIsEmpty = true;

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JFrame();
					frame.setBounds(100, 100, 450, 300);
					JButton btnSubmit = new JButton("Submit");
					btnSubmit.setForeground(new Color(0, 128, 0));
					btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
					btnSubmit.setBounds(156, 137, 89, 26);
					btnSubmit.setEnabled(false);
					btnSubmit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								submitAction();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					String[] clients = getClients("epapad04");
					comboBox = new JComboBox(clients);
					comboBox.setForeground(new Color(0, 128, 0));
					comboBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					comboBox.setSelectedIndex(0);
					comboBox.setBounds(148, 42, 109, 20);
					

					textField = new JTextField();
					textField.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
					textField.setBounds(136, 100, 130, 26);
					textField.setVisible(false);


					comboBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if (comboBox.getSelectedIndex() != 0) {
								textField.setText("");
								requestForChangeUI.clientSelected = true;
							} else {
								textField.setText("");
								requestForChangeUI.clientSelected = false;
							}
							if (requestForChangeUI.clientSelected && requestForChangeUI.fieldSelected
									&& !requestForChangeUI.fieldIsEmpty) {
								btnSubmit.setEnabled(true);
							} else {
								btnSubmit.setEnabled(false);
							}
						}
					});
					frame.getContentPane().setLayout(null);
					frame.getContentPane().add(comboBox);

					comboBox1 = new JComboBox(fields);
					comboBox1.setForeground(new Color(0, 128, 0));
					comboBox1.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					comboBox1.setSelectedIndex(0);
					comboBox1.setBounds(148, 69, 109, 20);

					comboBox1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if (comboBox1.getSelectedIndex() != 0) {
								requestForChangeUI.fieldSelected = true;
								textField.setText("");
								textField.setVisible(true);
							} else {
								requestForChangeUI.fieldSelected = false;
								textField.setText("");
								textField.setVisible(false);
							}
							if (requestForChangeUI.clientSelected && requestForChangeUI.fieldSelected
									&& !requestForChangeUI.fieldIsEmpty) {
								btnSubmit.setEnabled(true);
							} else {
								btnSubmit.setEnabled(false);
							}

						}
					});
					frame.getContentPane().add(comboBox1);

					textField.getDocument().addDocumentListener(new DocumentListener() {

						@Override
						public void changedUpdate(DocumentEvent e) {
							// TODO Auto-generated method stub
							changed();
						}

						@Override
						public void insertUpdate(DocumentEvent e) {
							// TODO Auto-generated method stub
							changed();

						}

						@Override
						public void removeUpdate(DocumentEvent e) {
							// TODO Auto-generated method stub
							changed();

						}

						private void changed() {
							if (textField.getText().equals("")) {
								requestForChangeUI.fieldIsEmpty = true;
								btnSubmit.setEnabled(false);
							} else {
								requestForChangeUI.fieldIsEmpty = false;
								if (requestForChangeUI.clientSelected && requestForChangeUI.fieldSelected
										&& !requestForChangeUI.fieldIsEmpty) {
									btnSubmit.setEnabled(true);
								}
							}

						}

					});
					frame.getContentPane().add(textField);
					textField.setColumns(10);

					frame.getContentPane().add(btnSubmit);

					JLabel lblRequest = new JLabel("Request for change");
					lblRequest.setForeground(new Color(0, 128, 0));
					lblRequest.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
					lblRequest.setBounds(148, 11, 147, 20);
					frame.getContentPane().add(lblRequest);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String[] getClients(String lawyerID) throws Exception {
		// call endpoint to get clients
		ws.xml result = this.ls.RetrieveClients(lawyerID);
		String[] str= new String[result.t.size()+1];
		int i = 0;
		str[i]="Choose client";
//		for (String[] row : result.t) {
//			i++;
//			str[i]=row[0];
//		}
		ws.xml result1;
		for( String[] s: result.t ){
	    	result1=this.ls.risk(Integer.parseInt(s[0]));
			for( String[] row: result1.t ){
				 i++;
			    str[i]=row[0]+" "+row[1];
			}
	        
	    }
		return str;

	}
	
	private void submitAction() throws Exception {
		
		int clientID = comboBox.getSelectedIndex();
		String field= String.valueOf(comboBox1.getSelectedItem());
		String value= textField.getText();
		
		this.ls.setNewChangeRequest(clientID, field, value);
	}

}
