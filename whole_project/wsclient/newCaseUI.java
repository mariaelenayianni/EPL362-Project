package wsclient;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

/**
 * This class represents an UI for new Case 
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class newCaseUI {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private static boolean caseSelected;
	private static boolean strategySelected;
	private static boolean legalOpinionIsSelected;
	private static boolean detailedIsEmpty;
	private static boolean distibutesIsEmpty;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private legalStaffClient ls;
	private String[] strEff;
	private JLabel lblSideEffects;
	private JLabel label;

	/**
	 * Launch the application.
	 * Constructor
	 * @throws MalformedURLException
	 */
	public newCaseUI() throws MalformedURLException {
		this.ls = new legalStaffClient();
		newCaseUI.caseSelected = false;
		newCaseUI.strategySelected = false;
		newCaseUI.legalOpinionIsSelected = false;
		newCaseUI.detailedIsEmpty = true;
		newCaseUI.distibutesIsEmpty = true;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JFrame();
					frame.setBounds(100, 100, 520, 412);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.getContentPane().setLayout(null);

					JButton btnSubmit = new JButton("Submit");
					btnSubmit.setForeground(new Color(0, 128, 0));
					btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
					btnSubmit.setBounds(213, 315, 95, 33);
					btnSubmit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								submitAction();
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					btnSubmit.setEnabled(true);
					frame.getContentPane().add(btnSubmit);

					JLabel lblNewCase = new JLabel("New Case");
					lblNewCase.setForeground(new Color(0, 128, 0));
					lblNewCase.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
					lblNewCase.setBounds(226, 0, 82, 24);
					frame.getContentPane().add(lblNewCase);

					JLabel lblCaseType = new JLabel("Case Type:");
					lblCaseType.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					lblCaseType.setForeground(new Color(0, 128, 0));
					lblCaseType.setBounds(20, 38, 66, 14);
					frame.getContentPane().add(lblCaseType);

					String[] cases = getCases();

					comboBox = new JComboBox(cases);
					comboBox.setForeground(new Color(0, 128, 0));
					comboBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					comboBox.setSelectedIndex(0);
					comboBox.setBounds(107, 35, 116, 23);
					comboBox.addActionListener(new ActionListener() {
						/* (non-Javadoc)
						 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
						 */
						public void actionPerformed(ActionEvent arg0) {
							if (comboBox.getSelectedIndex() != 0) {
								newCaseUI.caseSelected = true;
							} else {
								newCaseUI.caseSelected = false;
							}
						}
					});
					frame.getContentPane().add(comboBox);

					JLabel lblStrategy = new JLabel("Strategy:");
					lblStrategy.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					lblStrategy.setForeground(new Color(0, 128, 0));
					lblStrategy.setBounds(20, 63, 66, 14);
					frame.getContentPane().add(lblStrategy);

					String[] strategies = getStrategies();

					comboBox_1 = new JComboBox(strategies);
					comboBox_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					comboBox_1.setForeground(new Color(0, 128, 0));
					comboBox_1.setSelectedIndex(0);
					comboBox_1.setBounds(107, 60, 116, 24);

					lblSideEffects = new JLabel("Side Effects:");
					lblSideEffects.setForeground(new Color(0, 128, 0));
					lblSideEffects.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					lblSideEffects.setBounds(233, 63, 88, 14);
					lblSideEffects.setVisible(false);
					frame.getContentPane().add(lblSideEffects);

					label = new JLabel("");
					label.setForeground(new Color(0, 128, 0));
					label.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					label.setBounds(314, 63, 161, 51);
					label.setVisible(false);
					frame.getContentPane().add(label);

					comboBox_1.addActionListener(new ActionListener() {
						/* (non-Javadoc)
						 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
						 */
						public void actionPerformed(ActionEvent arg0) {
							if (comboBox_1.getSelectedIndex() != 0) {
								newCaseUI.strategySelected = true;
								lblSideEffects.setVisible(true);
								label.setText(getSideEffect(comboBox_1.getSelectedIndex()));
								label.setVisible(true);
							} else {
								newCaseUI.strategySelected = false;
								lblSideEffects.setVisible(false);
								label.setText("");
								label.setVisible(false);

							}
						}
					});

					frame.getContentPane().add(comboBox_1);

					JLabel lblLegalOpinion = new JLabel("Legal Opinion:");
					lblLegalOpinion.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					lblLegalOpinion.setForeground(new Color(0, 128, 0));
					lblLegalOpinion.setBounds(20, 92, 102, 14);
					frame.getContentPane().add(lblLegalOpinion);

					String[] legalOpinions = getLegalOpinions();

					comboBox_2 = new JComboBox(legalOpinions);
					comboBox_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					comboBox_2.setForeground(new Color(0, 128, 0));
					comboBox_2.setSelectedIndex(0);
					comboBox_2.setBounds(107, 91, 116, 23);
					comboBox_2.addActionListener(new ActionListener() {
						/* (non-Javadoc)
						 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
						 */
						public void actionPerformed(ActionEvent arg0) {
							if (comboBox_2.getSelectedIndex() != 0) {
								// HERE
								newCaseUI.legalOpinionIsSelected = true;
							} else {
								newCaseUI.legalOpinionIsSelected = false;

							}
						}
					});

					frame.getContentPane().add(comboBox_2);

					JLabel lblDetails = new JLabel("Details:");
					lblDetails.setForeground(new Color(0, 128, 0));
					lblDetails.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					lblDetails.setBounds(20, 117, 66, 25);
					frame.getContentPane().add(lblDetails);

					textField_1 = new JTextField();
					textField_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					textField_1.setColumns(10);
					textField_1.setBounds(20, 146, 178, 87);
					textField_1.getDocument().addDocumentListener(new DocumentListener() {

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
						 * This function handles the changed for the text field
						 */
						private void changed() {
							if (textField_1.getText().equals("")) {
								newCaseUI.detailedIsEmpty = true;

							} else {
								newCaseUI.detailedIsEmpty = false;

							}

						}

					});
					frame.getContentPane().add(textField_1);

					JButton btnShowPreviousDetails = new JButton("Show previous Details");
					btnShowPreviousDetails.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
					btnShowPreviousDetails.setForeground(new Color(0, 128, 0));
					btnShowPreviousDetails.setBounds(20, 244, 178, 24);
					btnShowPreviousDetails.addActionListener(new ActionListener() {
						/* (non-Javadoc)
						 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
						 */
						public void actionPerformed(ActionEvent e) {
							try {
								String[] details = getPreviousDetails();
								JDialog dialog = new JDialog();
								dialog.setTitle("Previous details");
								JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
								panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
								JLabel lbl = new JLabel();
								lbl.setBounds(242, 117, 66, 14);
								String msg = "<html><ul>";
								for (int i = 0; i < details.length; i++) {
									msg += "<li>" + details[i] + "</li>";
								}
								msg += "</ul></html>";
								lbl.setText(msg);
								panel.add(lbl);
								dialog.add(panel);
								dialog.setSize(500, 200);
								dialog.setResizable(false);
								dialog.setLocationRelativeTo(null);
								dialog.setModal(true);
								dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								// dialog.pack();

								dialog.setVisible(true);
								dialog.addWindowListener(new WindowAdapter() {
									@Override
									public void windowClosing(WindowEvent e) {
										super.windowClosing(e);
									}
								});

								// System.out.println(details[i]);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					frame.getContentPane().add(btnShowPreviousDetails);

					JLabel lblDisputes = new JLabel("Disputes:");
					lblDisputes.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					lblDisputes.setForeground(new Color(0, 128, 0));
					lblDisputes.setBounds(242, 117, 66, 14);
					frame.getContentPane().add(lblDisputes);

					textField_2 = new JTextField();
					textField_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
					textField_2.setColumns(10);
					textField_2.setBounds(242, 146, 182, 87);
					textField_2.getDocument().addDocumentListener(new DocumentListener() {

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
						 * This function handles the changed of the textfield 2
						 */
						private void changed() {
							if (textField_2.getText().equals("")) {
								newCaseUI.distibutesIsEmpty = true;
							} else {
								newCaseUI.distibutesIsEmpty = false;
							}

						}

					});
					frame.getContentPane().add(textField_2);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * This function request to get cases
	 * @return
	 */
	private String[] getCases() {
		String[] caseType = { "", "1", "2", "3", "4", "5" };
		return caseType;

	}

	/**
	 * This function requests to get strategies
	 * @return
	 * @throws Exception
	 */
	private String[] getStrategies() throws Exception {
		// call endpoint to get strategies
		ws.xml result = this.ls.retrieveStrategies();
		String[] str = new String[result.t.size() + 1];
		strEff = new String[result.t.size() + 1];
		int i = 0;
		str[i] = "";
		strEff[i] = "";
		for (String[] row : result.t) {
			i++;
			str[i] = row[1];
			strEff[i] = row[2];
		}
		return str;

	}

	/**
	 * This function requests for get legal opinion
	 * @return
	 * @throws Exception
	 */
	private String[] getLegalOpinions() throws Exception {
		// CALL ENDPOINT TO GET LEGALOPINIONS
		ws.xml result = this.ls.retrieveLegalOpinions();
		String[] str = new String[result.t.size() + 1];
		int i = 0;
		str[i] = "";
		for (String[] row : result.t) {
			i++;
			str[i] = row[1];
		}
		return str;
	}

	private String getSideEffect(int strategyID) {
		return !strEff[strategyID].equals("") ? strEff[strategyID] : "No side effects";

	}

	/**
	 * This function requests to get previous details
	 * @return
	 * @throws Exception
	 */
	private String[] getPreviousDetails() throws Exception {
		// call endpoint to get previous details and show pop-up
		String lawyerID = globals.lawyerID;
		int clientID = globals.clientID;
		try {
			if (this.ls.getPreviousDetails(lawyerID, clientID) != null) {
				ws.xml result = this.ls.getPreviousDetails(lawyerID, clientID);
				String[] str = new String[result.t.size()];
				int i = 0;
				for (String[] row : result.t) {
					str[i] = row[0];
					i++;
				}
				return str;
			}
		} catch (Exception e) {
			String[] str = new String[1];
			str[0] = "No previous Details found!";
			return str;
		}
		return null;
	}

	/**
	 * This function takes action when submit button pressed
	 * Espessialy, requests for insert new case 
	 * @throws Exception
	 */
	private void submitAction() throws Exception {
		// find if the form is completed and call endpoint to update db

		int complete = 0;
		if (newCaseUI.caseSelected && newCaseUI.strategySelected && newCaseUI.legalOpinionIsSelected
				&& !newCaseUI.detailedIsEmpty && !newCaseUI.distibutesIsEmpty)
			complete = 1;
		String selectedCase = String.valueOf(comboBox.getSelectedItem());
		int selectedStrategy = comboBox_1.getSelectedIndex();
		int selectedLegalOpinion = comboBox_2.getSelectedIndex();
		String details = textField_1.getText();
		String disputes = textField_2.getText();

		// get disagreements, if exist
		String continueWithNewCase = this.ls.getDisagreements(globals.clientID, selectedStrategy);
		if (continueWithNewCase.equals("notok")) {
			Object[] options = { "Select Another Strategy", "Dismiss" };
			JPanel panel1 = new JPanel();
			panel1.add(new JLabel("This Client has a disagreement with the selected strategy " + selectedStrategy));

			int option = JOptionPane.showOptionDialog(null, panel1, "Warning", JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.PLAIN_MESSAGE, null, options, null);
			if (option == JOptionPane.YES_OPTION) {
				comboBox_1.setSelectedIndex(0);
				newCaseUI.strategySelected = false;
				lblSideEffects.setVisible(false);
				label.setText("");
				label.setVisible(false);
				return;
			} else {
				this.ls.insertOverruledWarning(globals.lawyerID, globals.clientID,
						"disagreement for strategy " + selectedStrategy, 1);
			}
			
		}

		this.ls.setNewCase(selectedCase, selectedStrategy, selectedLegalOpinion, details, disputes, complete,
				globals.appointment1);
		frame.dispose();
	}

}
