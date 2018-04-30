package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

/**
 * This class add comments
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class addCommentUI {

	private JFrame frame;
	private JTextField textField;
	private JComboBox comboBox;
	private legalStaffClient ls;
	private int [] IDs;

	/**
	 * Create the application.
	 * Constructor
	 * @throws MalformedURLException 
	 */
	public addCommentUI() throws MalformedURLException {
		this.ls=new legalStaffClient();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JFrame();
					frame.setBounds(100, 100, 450, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(null);

					JLabel lblAddComment = new JLabel("Comment Request");
					lblAddComment.setForeground(new Color(0, 128, 0));
					lblAddComment.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
					lblAddComment.setBounds(149, 0, 160, 30);
					frame.getContentPane().add(lblAddComment);

					JLabel lblFor = new JLabel("Choose a client:");
					lblFor.setForeground(new Color(0, 128, 0));
					lblFor.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					lblFor.setBounds(159, 45, 122, 13);
					frame.getContentPane().add(lblFor);

					String[] clients = getClients("epapad04");
					comboBox = new JComboBox(clients);
					comboBox.setForeground(new Color(0, 128, 0));
					comboBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					comboBox.setSelectedIndex(0);
					comboBox.setBounds(148, 69, 109, 20);

					textField = new JTextField();
					textField.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
					textField.setBounds(133, 100, 148, 103);
					textField.setVisible(false);
					
					JButton btnSubmit = new JButton("Submit");
					btnSubmit.setForeground(new Color(0, 128, 0));
					btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
					btnSubmit.setBounds(162, 216, 89, 23);
					btnSubmit.setEnabled(false);
					btnSubmit.addActionListener(new ActionListener() {
						/* (non-Javadoc)
						 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
						 */
						public void actionPerformed(ActionEvent e) {
							try {
								submitAction();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});

					comboBox.addActionListener(new ActionListener() {
						/* (non-Javadoc)
						 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
						 */
						public void actionPerformed(ActionEvent arg0) {
							if (comboBox.getSelectedIndex() != 0) {
								textField.setText("");
								textField.setVisible(true);
							} else {
								textField.setText("");
								textField.setVisible(false);
								btnSubmit.setEnabled(false);
							}
						}
					});
					frame.getContentPane().add(comboBox);

					textField.getDocument().addDocumentListener(new DocumentListener() {

						/* (non-Javadoc)
						 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
						 */
						@Override
						public void changedUpdate(DocumentEvent e) {
							// TODO Auto-generated method stub
							changed();
						}

						/* (non-Javadoc)
						 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
						 */
						@Override
						public void insertUpdate(DocumentEvent e) {
							// TODO Auto-generated method stub
							changed();

						}

						/* (non-Javadoc)
						 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
						 */
						@Override
						public void removeUpdate(DocumentEvent e) {
							// TODO Auto-generated method stub
							changed();

						}

						/**
						 * This function checks if text is changed
						 */
						private void changed() {
							if (textField.getText().equals("")) {
								btnSubmit.setEnabled(false);
							} else {
								btnSubmit.setEnabled(true);
							}

						}

					});
					frame.getContentPane().add(textField);
					textField.setColumns(10);

					frame.getContentPane().add(btnSubmit);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	 
	/**
	 * This function requests for Clients
	 * @param lawyerID
	 * @return the values of the combobox
	 * @throws Exception
	 */
	private String[] getClients(String lawyerID) throws Exception {
		// call endpoint to get clients
		ws.xml result = this.ls.RetrieveClients(globals.lawyerID);
		String[] str= new String[result.t.size()+1];
		IDs=new int[result.t.size()+1];
		System.out.println(result.t.size()+1);
		int i = 0;
		str[i]="";

		ws.xml result1;
		for( String[] s: result.t ){
	    	result1=this.ls.risk(Integer.parseInt(s[0]));
			for( String[] row: result1.t ){
				 i++;
			    str[i]=row[0]+" "+row[1];
			    IDs[i]=Integer.parseInt(row[3]);
			}
	        
	    }
		
		return str;

	}
	
	/**
	 * This function takes action when the submit button pressed
	 * @throws Exception
	 */
	private void submitAction() throws Exception {
		int pos = comboBox.getSelectedIndex();
		//SOS LOYER ID
		String comment = textField.getText();
		this.ls.setNewComment(IDs[pos], globals.lawyerID, comment);
		frame.dispose();
	}
}
