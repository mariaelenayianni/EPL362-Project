package wsclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

/**
 * This class represents an UI for the legal staff
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class legalStaffUI {

	private JFrame frame;

	private legalStaffClient ls;

	private int[] warningsIDs;

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
	 * Constructor
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
		frame.setBounds(100, 100, 972, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblLegalStaff = new JLabel("Legal staff ");
		lblLegalStaff.setForeground(Color.BLUE);
		lblLegalStaff.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		lblLegalStaff.setBounds(350, 0, 93, 26);
		frame.getContentPane().add(lblLegalStaff);
		frame.setVisible(true);

		JLabel lblWarnings = new JLabel("Warnings:");
		lblWarnings.setBounds(756, 6, 63, 16);
		frame.getContentPane().add(lblWarnings);
		lblWarnings.setForeground(Color.RED);
		lblWarnings.setFont(new Font("Trebuchet MS", Font.BOLD, 13));

		JButton btnNewButton_2 = new JButton("Show Warnings");
		btnNewButton_2.setBounds(719, 410, 135, 23);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.setForeground(Color.RED);

		JLabel lblOptions = new JLabel("Options:");
		lblOptions.setBounds(10, 25, 52, 16);
		frame.getContentPane().add(lblOptions);
		lblOptions.setForeground(new Color(0, 128, 0));
		lblOptions.setFont(new Font("Trebuchet MS", Font.BOLD, 13));

		JButton btnNewButton = new JButton("Show Appoinments");
		btnNewButton.setBounds(0, 52, 177, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setForeground(new Color(0, 128, 0));

		JButton btnNewButton_1 = new JButton("Comment Request");
		btnNewButton_1.setBounds(0, 86, 177, 23);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setForeground(new Color(0, 128, 0));

		JButton btnShowEstimatedRisk = new JButton("Show Risk Indicator");
		btnShowEstimatedRisk.setBounds(0, 120, 177, 26);
		frame.getContentPane().add(btnShowEstimatedRisk);
		btnShowEstimatedRisk.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
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
		btnRequestForChange.setBounds(0, 153, 177, 26);
		frame.getContentPane().add(btnRequestForChange);
		btnRequestForChange.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
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
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
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
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					new AppointmentWindow();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				String contex = "";
				try {
					showWarnings() ;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (!contex.equals("")) {
					//label.setText(contex);
					//label.setVisible(true);
				}

			}
		});

	}

	/**
	 * This function requests for retrieve warnings
	 * @throws Exception
	 */
	public void showWarnings() throws Exception {
		ws.xml warnings = this.ls.showWarnings(globals.lawyerID);
		warningsIDs = new int[warnings.t.size()];
		int i=0;
		int j=0;
		Object[][] d=new Object[warnings.t.size()][6];
		for( String[] row: warnings.t ){
		    d[i][0]=row[2];
		    d[i][1]=row[1];
		    d[i][2]=row[0];
		    d[i][3]="overrule";
		    i++;
		}
		/**
		 * This class represents an table model object
		 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
		 *
		 *
		 */
		class MyTableModel extends AbstractTableModel {

			private String[] columnNames = { "warnID","clientID","description","action" };
			private Object[][] data = d;

			public int getColumnCount() {
				return columnNames.length;
			}

			public int getRowCount() {
				return data.length;
			}

			public Object getValueAt(int row, int col) {
				return data[row][col];
			}
			public String getColumnName(int col) {
	            return columnNames[col];
	        }
			
			public boolean isCellEditable(int row, int col) {

	            if (col < 3) {
	                return false;
	            } else {
	                return true;
	            }
	        }

		};
		JTable table = new JTable(new MyTableModel());
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(450, 40, 500, 300);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		

		Action go = new AbstractAction() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {

				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				int w=Integer.parseInt((String)(table.getModel()).getValueAt(modelRow, 0));
				try {
					updateOverruledWarning(w);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(table, go, 3);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
	}

	/**
	 * This function requests to update overruled warning
	 * @param wID
	 * @throws Exception
	 */
	public void updateOverruledWarning(int wID) throws Exception {
		this.ls.updateOverruledWarning(wID);
	}
}
