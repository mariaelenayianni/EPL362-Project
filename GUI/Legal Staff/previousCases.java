package wsclient;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class previousCases {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					previousCases window = new previousCases();
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
	public previousCases() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial Black", Font.BOLD, 14));
		frame.setBounds(100, 100, 663, 460);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JLabel lblNewLabel = new JLabel("Previous Cases");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel.setBounds(231, 11, 166, 25);
		frame.getContentPane().add(lblNewLabel);

		class MyTableModel extends AbstractTableModel {

			private String[] columnNames = { "Case Type", "Strategy","Side-Effects" ,"Legal Opinion", "Details", "Disputes","Action"};
			private Object[][] data = { { "Car incident", "Koinise agwgi", "Mporei na apotixei", "Na ise kalos","....","...", "Confirm" },
					 };

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
	            if (col < 6) {
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

				//JTable table = (JTable) e.getSource();
				//int modelRow = Integer.valueOf(e.getActionCommand());
				//globals.appointment = (String) ( table.getModel()).getValueAt(modelRow, 0);
				//System.out.println(globals.appointment);
				//new Transactions();
				// k.setVisible(true);
				frame.setVisible(false);
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(table, go, 6);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}

}
