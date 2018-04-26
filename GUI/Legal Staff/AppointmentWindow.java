package wsclient;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

public class AppointmentWindow {
	private legalStaffClient ls;
	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentWindow window = new AppointmentWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public AppointmentWindow() throws Exception {
		this.ls=new legalStaffClient();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial Black", Font.BOLD, 14));
		frame.setBounds(100, 100, 663, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JLabel lblNewLabel = new JLabel("My Appointments");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel.setBounds(231, 11, 166, 25);
		frame.getContentPane().add(lblNewLabel);
		ws.xml result=this.ls.getAppointments(globals.lawyerID);
		int i=0;
		int j=0;
		Object[][] d=new Object[result.t.size()][6];
		for( String[] row: result.t ){
		    d[i][0]=row[4];
		    d[i][1]=row[3];
		    if(row[5].equals("0"))
		    d[i][2]="no";
		    if(row[5].equals("0"))
			    d[i][2]="yes";
		    d[i][3]=row[7];
		    d[i][4]="choose";
		    i++;
		}
		
		class MyTableModel extends AbstractTableModel {

			private String[] columnNames = { "ID", "Branch","drop-in", "Date and Time", "" };
			private Object[][] data = d;
				/*{ { "1", "Nicosia", "Car incident", "10/10/2017 10:05", "Choose" },
					{ "2", "Paphos", "Taxes", "09/08/2015 11:35", "Choose" },
					{ "3", "Lemesos", "khkjh", "05/02/2012 09:52", "Choose" },
					{ "4", "Larnaka", "khkuhkh", "10/03/2013 12:00", "Choose" }, };*/

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
	            //Note that the data/cell address is constant,
	            //no matter where the cell appears onscreen.
	            if (col < 4) {
	                return false;
	            } else {
	                return true;
	            }
	        }

		};
		
		JTable table = new JTable(new MyTableModel());
		// DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 95, 589, 298);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		

		Action go = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				String a=(String)(table.getModel()).getValueAt(modelRow, 0);
				 globals.appointment=Integer.parseInt(a);
				System.out.println(globals.appointment);
				try {
					new Transactions();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// k.setVisible(true);
				frame.setVisible(false);
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(table, go, 4);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
	}
}
