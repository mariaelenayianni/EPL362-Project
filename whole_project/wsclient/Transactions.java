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

/**
 * This class represents an UI for transactions
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class Transactions {
	private legalStaffClient ls;
	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * Constructor
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
		frame.setBounds(100, 100, 787, 460);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Transactions");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel.setBounds(322, 11, 166, 25);
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
			    d[i][6]="Edit";
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
		
		/**
		 * This class represents a table model object
		 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
		 *
		 */
		class MyTableModel extends AbstractTableModel {

			private String[] columnNames = {"ID","Client Username", "Name of Client", "Lastname of Client","Attended","Updated","Action"};
			private Object[][] data =d;

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
			 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
			 */
			public boolean isCellEditable(int row, int col) {
	            //Note that the data/cell address is constant,
	            //no matter where the cell appears onscreen.
	            
				if (col < 6) {
	                return false;
	            } else {
	                return true;
	            }
	        }
			/* (non-Javadoc)
			 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
			 */
			public String getColumnName(int col) {
	            return columnNames[col];
	        }

		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 751, 298);
		frame.getContentPane().add(scrollPane);
		JTable table = new JTable(new MyTableModel());
		scrollPane.setViewportView(table);
		Action go = new AbstractAction()
		{
		    /* (non-Javadoc)
		     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		     */
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
					frame.dispose();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
		        if(globals.action.equals("Update")){try {
					new Update();
					frame.dispose();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
		        if(globals.action.equals("Edit")){try {
					new Update();
					frame.dispose();
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
