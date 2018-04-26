package wsclient;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;

import javax.swing.JScrollBar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

public class Transactions {
	private legalStaffClient ls;
	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Transactions() throws Exception {
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
		
		JLabel lblNewLabel = new JLabel("Transactions");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel.setBounds(258, 11, 166, 25);
		frame.getContentPane().add(lblNewLabel);
		int a=globals.appointment;
		System.out.println(a);
		ws.xml result=this.ls.getTransactions(a);
		int i=0;
		int j=0;
		Object[][] d=new Object[result.t.size()][7];
		for( String[] row: result.t ){
		    d[i][0]=row[0];
		    d[i][1]=row[1];
		    d[i][2]=row[4];
		    d[i][3]=row[5];
		    if(row[2].equals("1")) {
		    	d[i][4]="Yes";
		    }else {
		    	d[i][4]="No";
		    }
		    if(row[6].isEmpty()) {
		    	d[i][5]="Not Created";
		    	d[i][6]="Create";
		    }
		    if(row[6].equals("1")) {
			    d[i][5]="Updated";
			    d[i][6]="See";
			}
		    if(row[6].equals("0")) {
			    d[i][5]="Not Updated";
			    d[i][6]="Update";
			}
		    
		    i++;
		}
		for( String[] row: result.t ){
		    for( String s: row ){
		        System.out.print( " " + s );
		    }
		    System.out.println();
		}
		
		class MyTableModel extends AbstractTableModel {

			private String[] columnNames = {"ID","Client Username", "Name of Client", "Lastname of Client","Attended","Updated","Action"};
			private Object[][] data =d;
				/*{
					    {"1", "Kwstakis", "Kokou","No","Update"},
					    {"2", "Pampos", "Mirivillis","Yes","See"},
					    {"3",  "Marikou", "Poutalefkara","Not Created","Create"},
					    
					};*/

			public int getColumnCount() {
				return columnNames.length;
			}

			public int getRowCount() {
				return data.length;
			}

			public Object getValueAt(int row, int col) {
				return data[row][col];
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
			public String getColumnName(int col) {
	            return columnNames[col];
	        }

		};
		
		
		
		 
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 95, 589, 298);
		frame.getContentPane().add(scrollPane);
		JTable table = new JTable(new MyTableModel());
		scrollPane.setViewportView(table);
		Action go = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        
		        
		    	JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        globals.action=(String) (table.getModel()).getValueAt(modelRow, 6);
		        String a=(String)(table.getModel()).getValueAt(modelRow, 0);
		        String b=(String)(table.getModel()).getValueAt(modelRow, 1);
				 globals.appointment1=Integer.parseInt(a);
				 globals.clientID=Integer.parseInt(b);
		        System.out.println(globals.action);
		        System.out.println("aID:"+globals.appointment1);
		        System.out.println("cID:"+globals.clientID);
		        System.out.println("lID:"+globals.lawyerID);
		        
		        if(globals.action.equals("Create")){try {
					new newCaseUI();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
		        if(globals.action.equals("See")){try {
					new newCaseUI();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
		        if(globals.action.equals("Update")){try {
					new newCaseUI();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
		       
		    }
		};
		
		ButtonColumn buttonColumn = new ButtonColumn(table, go, 6);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		frame.setVisible(true);
	}
}
