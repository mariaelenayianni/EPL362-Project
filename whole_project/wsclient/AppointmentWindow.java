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

/**
 * This class presents the appointments 
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
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
	 * Constructor
	 * @throws Exception
	 */
	public AppointmentWindow() throws Exception {
		this.ls = new legalStaffClient();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial Black", Font.BOLD, 14));
		frame.setBounds(100, 100, 663, 460);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JLabel lblNewLabel = new JLabel("My Appointments");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel.setBounds(237, 31, 166, 25);
		frame.getContentPane().add(lblNewLabel);
		ws.xml result = this.ls.getAppointments(globals.lawyerID);
		
			int i = 0;
			int j = 0;
			Object[][] d;
			if (result.t!=null) {
				d = new Object[result.t.size()][6];
			for (String[] row : result.t) {
				d[i][0] = row[0];
				d[i][1] = row[3];
				if (row[5].equals("0"))
					d[i][2] = "no";
				if (row[5].equals("0"))
					d[i][2] = "yes";
				d[i][3] = row[7];
				d[i][4] = row[8];
				d[i][5] = "choose";
				i++;
			}
		} else {
			d = new Object[1][6];
			
			JLabel label = new JLabel("No appointments to show.");
			label.setBounds(50, 371, 538, 39);
			frame.getContentPane().add(label);
		}

		/**
		 * This class presents the table model
		 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
		 *
		 */
		class MyTableModel extends AbstractTableModel {

			private String[] columnNames = { "ID", "Branch", "drop-in", "Date","Time", "" };
			private Object[][] data = d;

			/* (non-Javadoc)
			 * @see javax.swing.table.TableModel#getColumnCount()
			 */
			public int getColumnCount() {
				return columnNames.length;
			}

			/* (non-Javadoc)
			 * @see javax.swing.table.TableModel#getRowCount()
			 */
			public int getRowCount() {
				return data.length;
			}

			/* (non-Javadoc)
			 * @see javax.swing.table.TableModel#getValueAt(int, int)
			 */
			public Object getValueAt(int row, int col) {
				return data[row][col];
			}

			/* (non-Javadoc)
			 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
			 */
			public String getColumnName(int col) {
				return columnNames[col];
			}

			/* (non-Javadoc)
			 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
			 */
			public boolean isCellEditable(int row, int col) {
				// Note that the data/cell address is constant,
				// no matter where the cell appears onscreen.
				if (col < 5) {
					return false;
				} else {
					return true;
				}
			}

		}
		;

		JTable table = new JTable(new MyTableModel());
		// DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 95, 589, 265);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		Action go = new AbstractAction() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {

				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				String a = (String) (table.getModel()).getValueAt(modelRow, 0);
				globals.appointment = Integer.parseInt(a);
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

		ButtonColumn buttonColumn = new ButtonColumn(table, go, 5);
		buttonColumn.setMnemonic(KeyEvent.VK_D);

	}
}
