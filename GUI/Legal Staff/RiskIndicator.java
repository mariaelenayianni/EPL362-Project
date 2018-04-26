package wsclient;

import java.awt.EventQueue;
import java.awt.Font;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class RiskIndicator {

	private JFrame frame;
	private legalStaffClient ls;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RiskIndicator window = new RiskIndicator();
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
	public RiskIndicator() throws Exception {
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
		
		JLabel lblNewLabel = new JLabel("Risk Indicator");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel.setBounds(289, 11, 166, 25);
		frame.getContentPane().add(lblNewLabel);
		
		ws.xml result=this.ls.risk(2);
		
		int i=0;
		ws.xml result1;
		result1=this.ls.RetrieveClients("mangel03");
		Object[][] d=new Object[result1.t.size()][6];		
		for( String[] row1: result1.t ){
		    for( String s: row1 ){
		    	result=this.ls.risk(Integer.parseInt(s));
				for( String[] row: result.t ){
				    d[i][0]=row[0];
				    d[i][1]=row[1];
				    if(row[2].equals("1")) {
				    d[i][2]="low";
				    }
				    if(row[2].equals("2")) {
					    d[i][2]="medium";
					    }
				    if(row[2].equals("3")) {
					    d[i][2]="high";
					    }
				    i++;
				}
		        
		    }
		}
		
		class MyTableModel extends AbstractTableModel {

			private String[] columnNames = {"Client Name", "Client Lastname", "Risk Indicator"};
			private Object[][] data =d;
			

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
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 95, 589, 298);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
	}

}
